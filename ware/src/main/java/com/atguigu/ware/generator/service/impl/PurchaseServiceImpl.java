package com.atguigu.ware.generator.service.impl;

import com.atguigu.constant.WareConstant;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.atguigu.ware.generator.domain.Purchase;
import com.atguigu.ware.generator.domain.PurchaseDetail;
import com.atguigu.ware.generator.mapper.PurchaseMapper;
import com.atguigu.ware.generator.service.PurchaseDetailService;
import com.atguigu.ware.generator.service.PurchaseService;
import com.atguigu.ware.generator.service.WareSkuService;
import com.atguigu.ware.vo.MergeVo;
import com.atguigu.ware.vo.PurchaseDoneVo;
import com.atguigu.ware.vo.PurchaseItemDoneVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author zhenglee
* @description 针对表【wms_purchase(采购信息)】的数据库操作Service实现
* @createDate 2025-12-13 21:59:22
*/
@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase>
    implements PurchaseService{
    @Autowired
    PurchaseDetailService detailService;

    @Autowired
    WareSkuService wareSkuService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Purchase> page = this.page(
                new Query<Purchase>().getPage(params),
                new QueryWrapper<Purchase>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageUnreceivePurchase(Map<String, Object> params) {
        IPage<Purchase> page = this.page(
                new Query<Purchase>().getPage(params),
                new QueryWrapper<Purchase>().eq("status",0).or().eq("status",1)
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void mergePurchase(MergeVo mergeVo) {
        Long purchaseId = mergeVo.getPurchaseId();
        if (purchaseId == null) {
            Purchase purchase = new Purchase();
            purchase.setStatus(0);
            purchase.setCreateTime(new Date());
            this.baseMapper.insert(purchase);
            purchaseId = purchase.getId();
        }
        List<Long> items = mergeVo.getItems();
        Long finishedId = purchaseId;
        List<PurchaseDetail> purchaseDetails = items.stream().map(item -> {
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            purchaseDetail.setId(item);
            purchaseDetail.setPurchaseId(finishedId);
            return purchaseDetail;
        }).collect(Collectors.toList());
        detailService.updateBatchById(purchaseDetails);

        Purchase purchase = new Purchase();
        purchase.setId(finishedId);
        this.updateById(purchase);
    }

    @Override
    public void received(List<Long> ids) {
        List<Purchase> collect = ids.stream().map(id -> {
            Purchase byId = this.getById(id);
            return byId;
        }).filter(item -> {
            if (item.getStatus() == WareConstant.PurchaseStatusEnum.CREATED.getCode() ||
                    item.getStatus() == WareConstant.PurchaseStatusEnum.ASSIGNED.getCode()) {
                return true;
            }
            return false;
        }).map(item->{
            item.setStatus(WareConstant.PurchaseStatusEnum.RECEIVE.getCode());
            item.setUpdateTime(new Date());
            return item;
        }).collect(Collectors.toList());

        //2、改变采购单的状态
        this.updateBatchById(collect);



        //3、改变采购项的状态
        collect.forEach((item)->{
            List<PurchaseDetail> entities = detailService.listDetailByPurchaseId(item.getId());
            List<PurchaseDetail> detailEntities = entities.stream().map(entity -> {
                PurchaseDetail entity1 = new PurchaseDetail();
                entity1.setId(entity.getId());
                entity1.setStatus(WareConstant.PurchaseDetailStatusEnum.BUYING.getCode());
                return entity1;
            }).collect(Collectors.toList());
            detailService.updateBatchById(detailEntities);
        });
    }

    @Transactional
    @Override
    public void done(PurchaseDoneVo doneVo) {
        Long id = doneVo.getId();


        //2、改变采购项的状态
        Boolean flag = true;
        List<PurchaseItemDoneVo> items = doneVo.getItems();

        List<PurchaseDetail> updates = new ArrayList<>();
        for (PurchaseItemDoneVo item : items) {
            PurchaseDetail detailEntity = new PurchaseDetail();
            if(item.getStatus() == WareConstant.PurchaseDetailStatusEnum.HASERROR.getCode()){
                flag = false;
                detailEntity.setStatus(item.getStatus());
            }else{
                detailEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.FINISH.getCode());
                ////3、将成功采购的进行入库
                PurchaseDetail entity = detailService.getById(item.getItemId());
                wareSkuService.addStock(entity.getSkuId(),entity.getWareId(),entity.getSkuNum());

            }
            detailEntity.setId(item.getItemId());
            updates.add(detailEntity);
        }

        detailService.updateBatchById(updates);

        //1、改变采购单状态
        Purchase purchaseEntity = new Purchase();
        purchaseEntity.setId(id);
        purchaseEntity.setStatus(flag?WareConstant.PurchaseStatusEnum.FINISH.getCode():WareConstant.PurchaseStatusEnum.HASERROR.getCode());
        purchaseEntity.setUpdateTime(new Date());
        this.updateById(purchaseEntity);

    }
}





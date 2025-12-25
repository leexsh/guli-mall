package com.atguigu.ware.generator.service.impl;

import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.atguigu.ware.generator.domain.Purchase;
import com.atguigu.ware.generator.mapper.PurchaseMapper;
import com.atguigu.ware.generator.service.PurchaseService;
import com.atguigu.ware.vo.MergeVo;
import com.atguigu.ware.vo.PurchaseDoneVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author zhenglee
* @description 针对表【wms_purchase(采购信息)】的数据库操作Service实现
* @createDate 2025-12-13 21:59:22
*/
@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase>
    implements PurchaseService{

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

    @Override
    public void mergePurchase(MergeVo mergeVo) {
//        todo lizheng
    }

    @Override
    public void received(List<Long> ids) {

    }

    @Override
    public void done(PurchaseDoneVo doneVo) {

    }
}





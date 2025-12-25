package com.atguigu.ware.generator.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.atguigu.ware.generator.domain.PurchaseDetail;
import com.atguigu.ware.generator.mapper.PurchaseDetailMapper;
import com.atguigu.ware.generator.service.PurchaseDetailService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
* @author zhenglee
* @description 针对表【wms_purchase_detail】的数据库操作Service实现
* @createDate 2025-12-13 21:59:22
*/
@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailMapper, PurchaseDetail>
    implements PurchaseDetailService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<PurchaseDetail> queryWrapper = new QueryWrapper<PurchaseDetail>();

        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            //purchase_id  sku_id
            queryWrapper.and(w->{
                w.eq("purchase_id",key).or().eq("sku_id",key);
            });
        }

        String status = (String) params.get("status");
        if(!StringUtils.isEmpty(status)){
            //purchase_id  sku_id
            queryWrapper.eq("status",status);
        }

        String wareId = (String) params.get("wareId");
        if(!StringUtils.isEmpty(wareId)){
            //purchase_id  sku_id
            queryWrapper.eq("ware_id",wareId);
        }

        IPage<PurchaseDetail> page = this.page(
                new Query<PurchaseDetail>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<PurchaseDetail> listDetailByPurchaseId(Long id) {
        List<PurchaseDetail> purchaseDetails = this.list(new QueryWrapper<PurchaseDetail>().eq("purchase_id", id));
        return purchaseDetails;
    }
}





package com.atguigu.coupon.generator.service;

import com.atguigu.coupon.generator.domain.SkuFullReduction;
import com.atguigu.to.SkuReductionTo;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【sms_sku_full_reduction(商品满减信息)】的数据库操作Service
* @createDate 2025-12-13 21:35:42
*/
public interface SkuFullReductionService extends IService<SkuFullReduction> {
    PageUtils queryPage(Map<String, Object> params);
    void saveSkuReduction(SkuReductionTo reductionTo);
}

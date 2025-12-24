package com.atguigu.coupon.generator.service;

import com.atguigu.coupon.generator.domain.CouponSpuCategoryRelation;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【sms_coupon_spu_category_relation(优惠券分类关联)】的数据库操作Service
* @createDate 2025-12-13 21:35:42
*/
public interface CouponSpuCategoryRelationService extends IService<CouponSpuCategoryRelation> {
    PageUtils queryPage(Map<String, Object> params);
}

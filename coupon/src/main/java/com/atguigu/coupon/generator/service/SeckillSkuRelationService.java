package com.atguigu.coupon.generator.service;

import com.atguigu.coupon.generator.domain.SeckillSkuRelation;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【sms_seckill_sku_relation(秒杀活动商品关联)】的数据库操作Service
* @createDate 2025-12-13 21:35:42
*/
public interface SeckillSkuRelationService extends IService<SeckillSkuRelation> {
    PageUtils queryPage(Map<String, Object> params);

}

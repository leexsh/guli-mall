package com.atguigu.coupon.generator.service;

import com.atguigu.coupon.generator.domain.SeckillSkuNotice;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【sms_seckill_sku_notice(秒杀商品通知订阅)】的数据库操作Service
* @createDate 2025-12-13 21:35:42
*/
public interface SeckillSkuNoticeService extends IService<SeckillSkuNotice> {
    PageUtils queryPage(Map<String, Object> params);
}

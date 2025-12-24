package com.atguigu.coupon.generator.service;

import com.atguigu.coupon.generator.domain.SkuLadder;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【sms_sku_ladder(商品阶梯价格)】的数据库操作Service
* @createDate 2025-12-13 21:35:42
*/
public interface SkuLadderService extends IService<SkuLadder> {
    PageUtils queryPage(Map<String, Object> params);
}

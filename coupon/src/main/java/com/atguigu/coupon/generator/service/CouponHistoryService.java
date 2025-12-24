package com.atguigu.coupon.generator.service;

import com.atguigu.coupon.generator.domain.CouponHistory;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【sms_coupon_history(优惠券领取历史记录)】的数据库操作Service
* @createDate 2025-12-13 21:35:42
*/
public interface CouponHistoryService extends IService<CouponHistory> {
    PageUtils queryPage(Map<String, Object> params);

}

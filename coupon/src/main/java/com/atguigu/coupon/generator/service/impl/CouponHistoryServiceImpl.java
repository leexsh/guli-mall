package com.atguigu.coupon.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.coupon.generator.domain.CouponHistory;
import com.atguigu.coupon.generator.service.CouponHistoryService;
import com.atguigu.coupon.generator.mapper.CouponHistoryMapper;
import org.springframework.stereotype.Service;

/**
* @author zhenglee
* @description 针对表【sms_coupon_history(优惠券领取历史记录)】的数据库操作Service实现
* @createDate 2025-12-13 21:35:42
*/
@Service
public class CouponHistoryServiceImpl extends ServiceImpl<CouponHistoryMapper, CouponHistory>
    implements CouponHistoryService{

}





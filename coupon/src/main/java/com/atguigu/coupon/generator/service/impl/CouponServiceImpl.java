package com.atguigu.coupon.generator.service.impl;

import com.atguigu.coupon.generator.domain.CouponHistory;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.coupon.generator.domain.Coupon;
import com.atguigu.coupon.generator.service.CouponService;
import com.atguigu.coupon.generator.mapper.CouponMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【sms_coupon(优惠券信息)】的数据库操作Service实现
* @createDate 2025-12-13 21:35:42
*/
@Service("couponService")
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon>
    implements CouponService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Coupon> page = this.page(
                new Query<Coupon>().getPage(params),
                new QueryWrapper<Coupon>()
        );

        return new PageUtils(page);
    }
}





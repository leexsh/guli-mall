package com.atguigu.coupon.controller;

import com.atguigu.coupon.generator.domain.Coupon;
import com.atguigu.utils.R;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RequestMapping("coupon/coupon")
@RestController
@RefreshScope
public class CouponController {

//    @Value("${coupon.user.name}")
//    private String name;
//    @Value("${coupon.user.age}")
//    private Integer age;
//    @RequestMapping("/test")
//    public R test() {
//        return R.ok().put("name", name).put("age", age);
//    }

    @RequestMapping("/member/list")
    public R memberList() {
        Coupon coupon = new Coupon();
        coupon.setCouponName("满 100减 10元");
        return R.ok().put("coupons", Arrays.asList(coupon));
    }
}

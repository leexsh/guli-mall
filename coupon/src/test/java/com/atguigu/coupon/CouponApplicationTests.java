package com.atguigu.coupon;

import com.atguigu.coupon.generator.service.CouponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CouponApplicationTests {

    @Autowired
    CouponService couponService;
    @Test
    void contextLoads() {
        couponService.list().forEach(System.out::println);
    }

}

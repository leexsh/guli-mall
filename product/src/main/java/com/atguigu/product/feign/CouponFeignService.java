package com.atguigu.product.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("coupon")
public class CouponFeignService {
}

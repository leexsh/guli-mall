package com.atguigu.seckill.feign;

import com.atguigu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("coupon")
public interface CouponFeignService {
    @GetMapping("/coupon/seckillsession/lates3DaySession")
    R getLates3DaySession();
}

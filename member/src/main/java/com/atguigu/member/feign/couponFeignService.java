package com.atguigu.member.feign;


import com.atguigu.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("coupon")
public interface couponFeignService {

    @RequestMapping("/coupon/coupon/member/list")
    public R memberList();
}

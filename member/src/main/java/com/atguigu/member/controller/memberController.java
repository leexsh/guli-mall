package com.atguigu.member.controller;

import com.atguigu.member.feign.couponFeignService;
import com.atguigu.member.generator.domain.Member;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member/member")
public class memberController {
    @Autowired
    couponFeignService cfService;

    @RequestMapping("/coupons")
    public R coupons() {
        Member member = new Member();
        member.setNickname("liz");
        R memberCoupons = cfService.memberList();
        return R.ok().put("member", member).put("coupons", memberCoupons.get("coupons"));
    }
}

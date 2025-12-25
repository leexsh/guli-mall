package com.atguigu.member.controller;

import com.atguigu.member.feign.couponFeignService;
import com.atguigu.member.generator.domain.Member;
import com.atguigu.member.generator.service.MemberService;
import com.atguigu.member.vo.MemberUserLoginVo;
import com.atguigu.member.vo.MemberUserRegisterVo;
import com.atguigu.member.vo.SocialUser;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("member/member")
public class memberController {
    @Autowired
    couponFeignService cfService;

    @Autowired
    MemberService memberService;

    @RequestMapping("/coupons")
    public R coupons() {
        Member member = new Member();
        member.setNickname("liz");
        R memberCoupons = cfService.memberList();
        return R.ok().put("member", member).put("coupons", memberCoupons.get("coupons"));
    }

    @PostMapping(value = "/register")
    public R register(@RequestBody MemberUserRegisterVo vo) {

        return R.ok();
    }


    @PostMapping(value = "/login")
    public R login(@RequestBody MemberUserLoginVo vo) {
        return R.ok();
    }


    @PostMapping(value = "/oauth2/login")
    public R oauthLogin(@RequestBody SocialUser socialUser) throws Exception {
        return R.ok();
    }

    @PostMapping(value = "/weixin/login")
    public R weixinLogin(@RequestParam("accessTokenInfo") String accessTokenInfo) {
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:member:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:member:info")
    public R info(@PathVariable("id") Long id) {
        Member member = memberService.getById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:member:save")
    public R save(@RequestBody Member member) {
        memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:member:update")
    public R update(@RequestBody Member member) {
        memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:member:delete")
    public R delete(@RequestBody Long[] ids) {
        memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}

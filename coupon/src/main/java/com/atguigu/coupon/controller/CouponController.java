package com.atguigu.coupon.controller;

import com.atguigu.coupon.generator.domain.Coupon;
import com.atguigu.coupon.generator.service.CouponService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

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

    @Autowired
    CouponService couponService;

    @RequestMapping("/member/list")
    public R memberList() {
        Coupon coupon = new Coupon();
        coupon.setCouponName("满 100减 10元");
        return R.ok().put("coupons", Arrays.asList(coupon));
    }

    @RequestMapping("/member/list")
    public R membercoupons() {
        Coupon couponEntity = new Coupon();
        couponEntity.setCouponName("满100减10");
        return R.ok().put("coupons", Arrays.asList(couponEntity));
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:coupon:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = couponService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:coupon:info")
    public R info(@PathVariable("id") Long id) {
        Coupon coupon = couponService.getById(id);

        return R.ok().put("coupon", coupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:coupon:save")
    public R save(@RequestBody Coupon coupon) {
        couponService.save(coupon);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:coupon:update")
    public R update(@RequestBody Coupon coupon) {
        couponService.updateById(coupon);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:coupon:delete")
    public R delete(@RequestBody Long[] ids) {
        couponService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}

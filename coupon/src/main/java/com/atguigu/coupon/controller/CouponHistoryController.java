package com.atguigu.coupon.controller;


import com.atguigu.coupon.generator.domain.CouponHistory;
import com.atguigu.coupon.generator.service.CouponHistoryService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("coupon/couponhistory")
public class CouponHistoryController {
    @Autowired
    private CouponHistoryService couponHistoryService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:couponhistory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = couponHistoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:couponhistory:info")
    public R info(@PathVariable("id") Long id){
        CouponHistory couponHistory = couponHistoryService.getById(id);

        return R.ok().put("couponHistory", couponHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:couponhistory:save")
    public R save(@RequestBody CouponHistory couponHistory){
        couponHistoryService.save(couponHistory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:couponhistory:update")
    public R update(@RequestBody CouponHistory couponHistory){
        couponHistoryService.updateById(couponHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:couponhistory:delete")
    public R delete(@RequestBody Long[] ids){
        couponHistoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}

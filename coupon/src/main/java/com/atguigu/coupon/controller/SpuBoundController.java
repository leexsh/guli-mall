package com.atguigu.coupon.controller;

import com.atguigu.coupon.generator.domain.SpuBounds;
import com.atguigu.coupon.generator.service.SpuBoundsService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/coupon/spubound")
public class SpuBoundController {
    @Autowired
    private SpuBoundsService spuBoundsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:spubounds:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = spuBoundsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:spubounds:info")
    public R info(@PathVariable("id") Long id){
        SpuBounds spuBounds = spuBoundsService.getById(id);

        return R.ok().put("spuBounds", spuBounds);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("coupon:spubounds:save")
    public R save(@RequestBody SpuBounds spuBounds){
        spuBoundsService.save(spuBounds);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:spubounds:update")
    public R update(@RequestBody SpuBounds spuBounds){
        spuBoundsService.updateById(spuBounds);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:spubounds:delete")
    public R delete(@RequestBody Long[] ids){
        spuBoundsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}

package com.atguigu.product.controller;

import com.atguigu.product.generator.domain.Brand;
import com.atguigu.product.generator.service.BrandService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("product/brand")
public class brandController {
    @Autowired
    BrandService brandService;

    @RequestMapping("/list")
    //@RequiresPermissions("product:brand:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/info/{brandId}")
    //@RequiresPermissions("product:brand:info")
    public R info(@PathVariable("brandId") Long brandId) {
        Brand brand = brandService.getById(brandId);
        return R.ok().put("brand", brand);
    }

    @RequestMapping("/save")
    //@RequiresPermissions("product:brand:save")
    public R save(@RequestBody Brand brand/*,BindingResult result*/) {
        brandService.save(brand);

        return R.ok();
    }

    @RequestMapping("/update")
    //@RequiresPermissions("product:brand:update")
    public R update(@RequestBody Brand brand) {
        brandService.updateDetail(brand);

        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] catIds){
        brandService.removeBatchByIds(Arrays.asList(catIds));
        return R.ok();
    }
}

package com.atguigu.product.controller;

import com.atguigu.product.generator.domain.Brand;
import com.atguigu.product.generator.service.BrandService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.R;
import com.atguigu.valid.AddGroup;
import com.atguigu.valid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("product/brand")
public class BrandController {
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
    public R save(@Validated({AddGroup.class}) @RequestBody Brand brand) {
//        if (result.hasErrors()) {
//            Map<String, String> errMap = new HashMap<>();
//            result.getFieldErrors().forEach((item)->{
//               String msg = item.getDefaultMessage();
//               String field = item.getField();
//               errMap.put(field, msg);
//            });
//            return R.error().put("data", errMap);
//        }
        brandService.save(brand);

        return R.ok();
    }

    @RequestMapping("/update")
    //@RequiresPermissions("product:brand:update")
    public R update(@Validated({UpdateGroup.class}) @RequestBody Brand brand) {
        brandService.updateDetail(brand);

        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] catIds){
        brandService.removeBatchByIds(Arrays.asList(catIds));
        return R.ok();
    }
}

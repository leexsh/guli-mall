package com.atguigu.product.controller;

import com.atguigu.product.generator.domain.AttrAttrgroupRelation;
import com.atguigu.product.generator.domain.AttrGroup;
import com.atguigu.product.generator.service.AttrGroupService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;


    @RequestMapping("/list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params, @PathVariable("catelogId") Long catelogId) {
        PageUtils page = attrGroupService.queryPage(params, catelogId);
        return R.ok().put("page", page);
    }

    @RequestMapping("/save")
    //@RequiresPermissions("product:attrattrgrouprelation:save")
    public R save(@RequestBody AttrGroup attrgroup) {
        attrGroupService.save(attrgroup);
        return R.ok();
    }

    @RequestMapping("/update")
    //@RequiresPermissions("product:attrattrgrouprelation:update")
    public R update(@RequestBody AttrGroup relation) {
        attrGroupService.updateById(relation);
        return R.ok();
    }


    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        attrGroupService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }
}

package com.atguigu.product.controller;

import com.atguigu.product.generator.domain.AttrAttrgroupRelation;
import com.atguigu.product.generator.service.AttrAttrgroupRelationService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("product/attrattrgrouprelation")
public class AttrAttrGroupRelationController {

    @Autowired
    private AttrAttrgroupRelationService attrAttrGroupRelationService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrAttrGroupRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        AttrAttrgroupRelation entity = attrAttrGroupRelationService.getById(id);

        return R.ok().put("entity", entity);
    }

    @RequestMapping("/save")
    public R save(@RequestBody AttrAttrgroupRelation entity){
        attrAttrGroupRelationService.save(entity);

        return R.ok();
    }

    @RequestMapping("/update")
    public R update(@RequestBody AttrAttrgroupRelation entity){
        attrAttrGroupRelationService.updateById(entity);

        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        attrAttrGroupRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}

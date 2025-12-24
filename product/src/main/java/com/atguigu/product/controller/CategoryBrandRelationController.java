package com.atguigu.product.controller;

import com.atguigu.product.generator.domain.Brand;
import com.atguigu.product.generator.domain.CategoryBrandRelation;
import com.atguigu.product.generator.service.CategoryBrandRelationService;
import com.atguigu.product.vo.BrandVo;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("product/category_brand_relation")
@RestController
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @GetMapping("/catelog/list")
    //@RequiresPermissions("product:categorybrandrelation:list")
    public R cateloglist(@RequestParam("brandId")Long brandId){
        List<CategoryBrandRelation> data = categoryBrandRelationService.list(
                new QueryWrapper<CategoryBrandRelation>().eq("brand_id",brandId)
        );

        return R.ok().put("data", data);
    }

    @GetMapping("/brands/list")
    public R relationBrandsList(@RequestParam(value = "catId",required = true)Long catId){
        List<Brand> vos = categoryBrandRelationService.getBrandsByCatId(catId);

        List<BrandVo> collect = vos.stream().map(item -> {
            BrandVo brandVo = new BrandVo();
            brandVo.setBrandId(item.getBrandId());
            brandVo.setBrandName(item.getName());

            return brandVo;
        }).collect(Collectors.toList());

        return R.ok().put("data",collect);

    }

    @RequestMapping("/list")
    //@RequiresPermissions("product:categorybrandrelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryBrandRelationService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:categorybrandrelation:info")
    public R info(@PathVariable("id") Long id){
        CategoryBrandRelation categoryBrandRelation = categoryBrandRelationService.getById(id);

        return R.ok().put("categoryBrandRelation", categoryBrandRelation);
    }


    @RequestMapping("/save")
    //@RequiresPermissions("product:categorybrandrelation:save")
    public R save(@RequestBody CategoryBrandRelation categoryBrandRelation){
        categoryBrandRelationService.saveDetail(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CategoryBrandRelation categoryBrandRelation){
        categoryBrandRelationService.updateById(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        categoryBrandRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}

package com.atguigu.product.controller;

import com.atguigu.product.generator.domain.Category;
import com.atguigu.product.generator.service.CategoryService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("product/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/list/tree")
    public R list(){
        List<Category> entities = categoryService.listWithTree();
        return R.ok().put("data", entities);
    }

    @RequestMapping("/info/{catId}")
    //@RequiresPermissions("product:category:info")
    public R info(@PathVariable("catId") Long catId) {
        Category category = categoryService.getById(catId);
        return R.ok().put("data", category);
    }

    /*
    * 删除
    * */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        categoryService.removeMenuByIds(Arrays.asList(ids));
        return R.ok();
    }

    @RequestMapping("/save")
    public R save(@RequestBody Category category){
        categoryService.save(category);
        return R.ok();
    }

    @RequestMapping("/update/sort")
    public R updateSort(@RequestBody Category[] category){
        categoryService.updateBatchById(Arrays.asList(category));
        return R.ok();
    }

    /*
    * 单个修改
    * */
    @RequestMapping("/update")
    public R update(@RequestBody Category category){
        // 简单更新完了
        categoryService.updateCascade(category);
        return R.ok();
    }

}

package com.atguigu.product.controller;

import com.atguigu.product.generator.domain.Category;
import com.atguigu.product.generator.service.CategoryService;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

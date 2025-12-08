package com.atguigu.product.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.product.generator.domain.Category;
import com.atguigu.product.generator.service.CategoryService;
import com.atguigu.product.generator.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author zhenglee
* @description 针对表【pms_category(商品三级分类)】的数据库操作Service实现
* @createDate 2025-12-08 00:02:15
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}





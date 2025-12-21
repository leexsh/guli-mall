package com.atguigu.product.dao;

import com.atguigu.product.generator.domain.CategoryBrandRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelation> {
    void updateCategory(Long catId, String name);
}

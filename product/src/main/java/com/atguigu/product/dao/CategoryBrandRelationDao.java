package com.atguigu.product.dao;

import com.atguigu.product.generator.domain.CategoryBrandRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelation> {
    void updateCategory(Long catId, String name);
}

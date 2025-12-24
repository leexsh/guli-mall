package com.atguigu.product.dao;

import com.atguigu.product.generator.domain.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryDao extends BaseMapper<Category> {
}

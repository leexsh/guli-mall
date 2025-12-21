package com.atguigu.product.generator.mapper;

import com.atguigu.product.generator.domain.CategoryBrandRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author zhenglee
* @description 针对表【pms_category_brand_relation(品牌分类关联)】的数据库操作Mapper
* @createDate 2025-12-14 14:59:02
* @Entity com.atguigu.product.generator.domain.CategoryBrandRelation
*/
public interface CategoryBrandRelationMapper extends BaseMapper<CategoryBrandRelation> {

    void updateCategory(@Param("catId") Long catId, @Param("name") String name);

}





package com.atguigu.product.generator.service;

import com.atguigu.product.generator.domain.Brand;
import com.atguigu.product.generator.domain.CategoryBrandRelation;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author zhenglee
* @description 针对表【pms_category_brand_relation(品牌分类关联)】的数据库操作Service
* @createDate 2025-12-14 14:59:02
*/
public interface CategoryBrandRelationService extends IService<CategoryBrandRelation> {

    PageUtils queryPage(Map<String, Object> params);

    void saveDetail(CategoryBrandRelation categoryBrandRelation);

    void updateCategory(Long catId, String name);
    void updateBrand(Long brandId, String name);

    List<Brand> getBrandsByCatId(Long catId);
}

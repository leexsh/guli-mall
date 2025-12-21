package com.atguigu.product.generator.service;

import com.atguigu.product.generator.domain.CategoryBrandRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhenglee
* @description 针对表【pms_category_brand_relation(品牌分类关联)】的数据库操作Service
* @createDate 2025-12-14 14:59:02
*/
public interface CategoryBrandRelationService extends IService<CategoryBrandRelation> {

    void updateCategory(Long catId, String name);
    void updateBrand(Long brandId, String name);
}

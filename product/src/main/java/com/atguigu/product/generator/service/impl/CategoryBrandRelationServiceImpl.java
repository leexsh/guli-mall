package com.atguigu.product.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.product.generator.domain.CategoryBrandRelation;
import com.atguigu.product.generator.service.CategoryBrandRelationService;
import com.atguigu.product.generator.mapper.CategoryBrandRelationMapper;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;

/**
* @author zhenglee
* @description 针对表【pms_category_brand_relation(品牌分类关联)】的数据库操作Service实现
* @createDate 2025-12-14 14:59:02
*/
@Service
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationMapper, CategoryBrandRelation>
    implements CategoryBrandRelationService{
    @Override
    public void updateCategory(Long catId, String name) {
        baseMapper.updateCategory(catId, name);
    }

    @Override
    public void updateBrand(Long brandId, String name) {
        CategoryBrandRelation categoryBrandRelation = new CategoryBrandRelation();
        categoryBrandRelation.setBrandId(brandId);
        categoryBrandRelation.setBrandName(name);
        baseMapper.update(categoryBrandRelation, new UpdateWrapper<CategoryBrandRelation>().eq("brand_id", brandId));
    }
}





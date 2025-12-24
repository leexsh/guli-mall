package com.atguigu.product.generator.service.impl;

import com.atguigu.product.generator.domain.Brand;
import com.atguigu.product.generator.domain.Category;
import com.atguigu.product.generator.mapper.BrandMapper;
import com.atguigu.product.generator.mapper.CategoryMapper;
import com.atguigu.product.generator.service.BrandService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.product.generator.domain.CategoryBrandRelation;
import com.atguigu.product.generator.service.CategoryBrandRelationService;
import com.atguigu.product.generator.mapper.CategoryBrandRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author zhenglee
* @description 针对表【pms_category_brand_relation(品牌分类关联)】的数据库操作Service实现
* @createDate 2025-12-14 14:59:02
*/
@Service
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationMapper, CategoryBrandRelation>
    implements CategoryBrandRelationService{

    @Autowired
    BrandMapper brandDao;
    @Autowired
    CategoryMapper categoryDao;
    @Autowired
    CategoryBrandRelationMapper categoryBrandRelationDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelation> page = this.page(
                new Query<CategoryBrandRelation>().getPage(params),
                new QueryWrapper<CategoryBrandRelation>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveDetail(CategoryBrandRelation categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();
        //1、查询详细名字
        Brand brandEntity = brandDao.selectById(brandId);
        Category categoryEntity = categoryDao.selectById(catelogId);

        categoryBrandRelation.setBrandName(brandEntity.getName());
        categoryBrandRelation.setCatelogName(categoryEntity.getName());

        this.save(categoryBrandRelation);
    }

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

    @Override
    public List<Brand> getBrandsByCatId(Long catId) {
        List<CategoryBrandRelation> cb = categoryBrandRelationDao.selectList(new QueryWrapper<CategoryBrandRelation>().eq("catelog_id", catId));
        List<Brand> brands = cb.stream().map(item->{
            Long brandId = item.getBrandId();
            Brand brandEntity = brandDao.selectById(brandId);
            return brandEntity;
        }).collect(Collectors.toList());
        return brands;
    }
}





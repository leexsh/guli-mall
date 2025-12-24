package com.atguigu.product.generator.service.impl;

import com.atguigu.product.generator.domain.Category;
import com.atguigu.product.generator.mapper.CategoryMapper;
import com.atguigu.product.generator.service.CategoryService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
* @author zhenglee
* @description 针对表【pms_category(商品三级分类)】的数据库操作Service实现
* @createDate 2025-12-14 14:59:02
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    @Autowired
    CategoryBrandRelationServiceImpl categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Category> page = this.page(
                new Query<Category>().getPage(params),
                new QueryWrapper<Category>()
        );

        return new PageUtils(page);
    }
    @Override
    public List<Category> listWithTree() {

       List<Category> categories = baseMapper.selectList(null);
       List<Category> res = categories.stream().filter(category -> category.getParentCid() == 0)
               .map((menu)->{
                   menu.setChildren(getChildren(menu,categories));
                   return menu;
               }).sorted((m1, m2)->{
                   return (m1.getSort() == null ? 0 : m1.getSort())- (m2.getSort() == null ? 0 : m2.getSort());
               }).toList();
        return res;
    }

    @Override
    public void removeMenuByIds(List<Long> ids) {
        baseMapper.deleteBatchIds(ids);
    }

    private List<Long> findParentPath(Long catelogId, List<Long> paths) {
        //1、收集当前节点id
        paths.add(catelogId);
        Category byId = this.getById(catelogId);
        if (byId.getParentCid() != 0) {
            findParentPath(byId.getParentCid(), paths);
        }
        return paths;
    }

    @Override
    public Long[] findCatalogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId, paths);

        Collections.reverse(parentPath);
        return parentPath.toArray(new Long[parentPath.size()]);
    }

    @Transactional
    @Override
    public void updateCascade(Category category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
    }

    public List<Category> getChildren(Category category, List<Category> categories) {
        return categories.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid().equals(category.getCatId()))
                .peek(categoryEntity -> {
                    //1、找到子菜单
                    categoryEntity.setChildren(getChildren(categoryEntity, categories));
                })
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());
    }

}





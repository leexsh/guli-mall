package com.atguigu.product.generator.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.product.generator.domain.Brand;
import com.atguigu.product.generator.service.BrandService;
import com.atguigu.product.generator.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【pms_brand(品牌)】的数据库操作Service实现
* @createDate 2025-12-14 14:59:02
*/
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand>
    implements BrandService{

    @Autowired
    CategoryBrandRelationServiceImpl categoryBrandRelationService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //1、获取key
        String key = (String) params.get("key");
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.eq("brand_id", key).or().like("name", key);
        }

        IPage<Brand> page = this.page(
                new Query<Brand>().getPage(params),
                queryWrapper

        );

        return new PageUtils(page);
    }

    @Override
    public void updateDetail(Brand brand) {
        this.updateById(brand);
        if (!StringUtils.isEmpty(brand.getName())) {
            //同步更新其他关联表中的数据
            categoryBrandRelationService.updateBrand(brand.getBrandId(), brand.getName());
        }
    }
}





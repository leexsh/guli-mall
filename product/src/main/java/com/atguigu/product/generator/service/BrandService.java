package com.atguigu.product.generator.service;

import com.atguigu.product.generator.domain.Brand;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【pms_brand(品牌)】的数据库操作Service
* @createDate 2025-12-14 14:59:02
*/
public interface BrandService extends IService<Brand> {

    PageUtils queryPage(Map<String, Object> params);

    void updateDetail(Brand brand);
}

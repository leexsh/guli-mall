package com.atguigu.product.generator.service;

import com.atguigu.product.generator.domain.SkuImages;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author zhenglee
* @description 针对表【pms_sku_images(sku图片)】的数据库操作Service
* @createDate 2025-12-14 14:59:03
*/
public interface SkuImagesService extends IService<SkuImages> {

    PageUtils queryPage(Map<String, Object> params);

    List<SkuImages> getImagesBySkuId(Long skuId);
}

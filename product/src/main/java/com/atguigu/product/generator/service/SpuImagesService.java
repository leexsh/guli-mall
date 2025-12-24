package com.atguigu.product.generator.service;

import com.atguigu.product.generator.domain.SpuImages;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author zhenglee
* @description 针对表【pms_spu_images(spu图片)】的数据库操作Service
* @createDate 2025-12-14 14:59:03
*/
public interface SpuImagesService extends IService<SpuImages> {

    PageUtils queryPage(Map<String, Object> params);

    void saveImages(Long id, List<String> images);
}

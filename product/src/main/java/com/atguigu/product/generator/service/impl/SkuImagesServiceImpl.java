package com.atguigu.product.generator.service.impl;

import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.product.generator.domain.SkuImages;
import com.atguigu.product.generator.service.SkuImagesService;
import com.atguigu.product.generator.mapper.SkuImagesMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author zhenglee
* @description 针对表【pms_sku_images(sku图片)】的数据库操作Service实现
* @createDate 2025-12-14 14:59:03
*/
@Service
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesMapper, SkuImages>
    implements SkuImagesService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuImages> page = this.page(
                new Query<SkuImages>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SkuImages> getImagesBySkuId(Long skuId) {
        return this.baseMapper.selectList(new QueryWrapper<SkuImages>().eq("sku_id", skuId));
    }
}





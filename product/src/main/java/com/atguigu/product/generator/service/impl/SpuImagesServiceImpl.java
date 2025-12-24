package com.atguigu.product.generator.service.impl;

import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.product.generator.domain.SpuImages;
import com.atguigu.product.generator.service.SpuImagesService;
import com.atguigu.product.generator.mapper.SpuImagesMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author zhenglee
* @description 针对表【pms_spu_images(spu图片)】的数据库操作Service实现
* @createDate 2025-12-14 14:59:03
*/
@Service
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesMapper, SpuImages>
    implements SpuImagesService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuImages> page = this.page(
                new Query<SpuImages>().getPage(params),
                new QueryWrapper<SpuImages>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveImages(Long id, List<String> images) {
        if(images == null || images.size() == 0){

        }else{
            List<SpuImages> collect = images.stream().map(img -> {
                SpuImages spuImagesEntity = new SpuImages();
                spuImagesEntity.setSpuId(id);
                spuImagesEntity.setImgUrl(img);

                return spuImagesEntity;
            }).collect(Collectors.toList());

            this.saveBatch(collect);
        }
    }
}





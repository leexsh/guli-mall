package com.atguigu.product.generator.service.impl;

import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.product.generator.domain.SpuInfoDesc;
import com.atguigu.product.generator.service.SpuInfoDescService;
import com.atguigu.product.generator.mapper.SpuInfoDescMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SpuInfoDescServiceImpl extends ServiceImpl<SpuInfoDescMapper, SpuInfoDesc>
    implements SpuInfoDescService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoDesc> page = this.page(
                new Query<SpuInfoDesc>().getPage(params),
                new QueryWrapper<SpuInfoDesc>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSpuInfoDesc(SpuInfoDesc descEntity) {
        this.baseMapper.insert(descEntity);
    }
}





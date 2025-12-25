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

/**
 * @author zhenglee
 * @description 针对表【pms_spu_info_desc(商品介绍)】的数据库操作Service实现
 * @createDate 2025-12-14 14:59:03
 */
@Service("spuInfoDescService")
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





package com.atguigu.product.generator.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.atguigu.product.vo.SkuItemVo;
import com.atguigu.product.vo.SpuSaveVo;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.product.generator.domain.SpuInfo;
import com.atguigu.product.generator.service.SpuInfoService;
import com.atguigu.product.generator.mapper.SpuInfoMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【pms_spu_info(spu信息)】的数据库操作Service实现
* @createDate 2025-12-14 14:59:03
*/
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo>
    implements SpuInfoService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfo> page = this.page(
                new Query<SpuInfo>().getPage(params),
                new QueryWrapper<SpuInfo>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSpuInfo(SpuSaveVo vo) {
        // todo lizheng
    }

    @Override
    public void saveBaseSpuInfo(SpuInfo infoEntity) {
        this.baseMapper.insert(infoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SpuInfo> queryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and((w) -> {
                w.eq("spu_id", key).or().like("spu_name", key);
            });
        }

        String status = (String) params.get("status");
        if (!StringUtils.isEmpty(status)) {
            queryWrapper.eq("publish_status", status);
        }
        String brand = (String) params.get("brand");
        if (!StringUtils.isEmpty(brand)) {
            queryWrapper.eq("brand_id", brand);
        }
        String catelog = (String) params.get("catelogId");
        if (!StringUtils.isEmpty(catelog)) {
            queryWrapper.eq("catalog_id", catelog);
        }
        IPage<SpuInfo> page = this.page(
                new Query<SpuInfo>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void up(Long spuId) {
     // todo lizheng
    }
}





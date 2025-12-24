package com.atguigu.product.generator.mapper;

import com.atguigu.product.generator.domain.AttrGroup;
import com.atguigu.product.vo.SpuItemAttrGroupVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import feign.Param;

import java.util.List;

/**
* @author zhenglee
* @description 针对表【pms_attr_group(属性分组)】的数据库操作Mapper
* @createDate 2025-12-14 14:59:02
* @Entity com.atguigu.product.generator.domain.AttrGroup
*/
public interface AttrGroupMapper extends BaseMapper<AttrGroup> {
    List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(@Param("spuId") Long spuId, @Param("catalogId") Long catalogId);
}





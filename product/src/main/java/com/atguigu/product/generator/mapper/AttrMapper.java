package com.atguigu.product.generator.mapper;

import com.atguigu.product.generator.domain.Attr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import feign.Param;

import java.util.List;

/**
* @author zhenglee
* @description 针对表【pms_attr(商品属性)】的数据库操作Mapper
* @createDate 2025-12-14 14:59:02
* @Entity com.atguigu.product.generator.domain.Attr
*/
public interface AttrMapper extends BaseMapper<Attr> {

    List<Long> selectSearchAttrsIds(@Param("attrIds") List<Long> attrIds);
}





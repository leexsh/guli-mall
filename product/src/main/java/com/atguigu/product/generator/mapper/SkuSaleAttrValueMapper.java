package com.atguigu.product.generator.mapper;

import com.atguigu.product.generator.domain.SkuSaleAttrValue;
import com.atguigu.product.vo.SkuItemSaleAttrVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author zhenglee
* @description 针对表【pms_sku_sale_attr_value(sku销售属性&值)】的数据库操作Mapper
* @createDate 2025-12-14 14:59:03
* @Entity com.atguigu.product.generator.domain.SkuSaleAttrValue
*/
public interface SkuSaleAttrValueMapper extends BaseMapper<SkuSaleAttrValue> {

    List<SkuItemSaleAttrVo> getSaleAttrBySpuId(Long spuId);

    List<String> getSkuSaleAttrValuesAsStringList(Long skuId);
}





package com.atguigu.product.generator.service;

import com.atguigu.product.generator.domain.SkuSaleAttrValue;
import com.atguigu.product.vo.SkuItemSaleAttrVo;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author zhenglee
* @description 针对表【pms_sku_sale_attr_value(sku销售属性&值)】的数据库操作Service
* @createDate 2025-12-14 14:59:03
*/
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValue> {

    PageUtils queryPage(Map<String, Object> params);

    List<SkuItemSaleAttrVo> getSaleAttrBySpuId(Long spuId);

    List<String> getSkuSaleAttrValuesAsStringList(Long skuId);
}

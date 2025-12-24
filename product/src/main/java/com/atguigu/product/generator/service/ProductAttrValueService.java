package com.atguigu.product.generator.service;

import com.atguigu.product.generator.domain.ProductAttrValue;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author zhenglee
* @description 针对表【pms_product_attr_value(spu属性值)】的数据库操作Service
* @createDate 2025-12-14 14:59:02
*/
public interface ProductAttrValueService extends IService<ProductAttrValue> {

    PageUtils queryPage(Map<String, Object> params);

    void saveProductAttr(List<ProductAttrValue> collect);

    List<ProductAttrValue> baseAttrListforspu(Long spuId);

    void updateSpuAttr(Long spuId, List<ProductAttrValue> entities);
}

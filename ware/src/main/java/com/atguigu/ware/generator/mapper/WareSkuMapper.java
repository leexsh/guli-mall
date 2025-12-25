package com.atguigu.ware.generator.mapper;

import com.atguigu.ware.generator.domain.WareSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import feign.Param;

/**
* @author zhenglee
* @description 针对表【wms_ware_sku(商品库存)】的数据库操作Mapper
* @createDate 2025-12-13 21:59:22
* @Entity com.atguigu.ware.generator.domain.WareSku
*/
public interface WareSkuMapper extends BaseMapper<WareSku> {
    void addStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("skuNum") Integer skuNum);

    Long getSkuStock(@Param("skuId") Long skuId);
}





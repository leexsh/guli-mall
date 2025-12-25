package com.atguigu.ware.generator.service;

import com.atguigu.utils.PageUtils;
import com.atguigu.ware.generator.domain.WareSku;
import com.atguigu.ware.vo.SkuHasStockVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author zhenglee
* @description 针对表【wms_ware_sku(商品库存)】的数据库操作Service
* @createDate 2025-12-13 21:59:22
*/
public interface WareSkuService extends IService<WareSku> {
    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    /**
     * 判断是否有库存
     * @param skuIds
     * @return
     */
    List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds);
}

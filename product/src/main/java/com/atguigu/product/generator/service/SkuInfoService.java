package com.atguigu.product.generator.service;

import com.atguigu.product.generator.domain.SkuInfo;
import com.atguigu.product.vo.SkuItemVo;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
* @author zhenglee
* @description 针对表【pms_sku_info(sku信息)】的数据库操作Service
* @createDate 2025-12-14 14:59:03
*/
public interface SkuInfoService extends IService<SkuInfo> {


    PageUtils queryPage(Map<String, Object> params);

    void saveSkuInfo(SkuInfo skuInfoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);

    /**
     * 查出当前spuId对应的所有sku信息,品牌的名字
     *
     * @param spuId
     * @return
     */
    List<SkuInfo> getSkusBySpuId(Long spuId);

    SkuItemVo item(Long skuId) throws ExecutionException, InterruptedException;

}

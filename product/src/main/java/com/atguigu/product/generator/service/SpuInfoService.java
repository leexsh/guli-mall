package com.atguigu.product.generator.service;

import com.atguigu.product.generator.domain.SpuInfo;
import com.atguigu.product.vo.SpuSaveVo;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【pms_spu_info(spu信息)】的数据库操作Service
* @createDate 2025-12-14 14:59:03
*/
public interface SpuInfoService extends IService<SpuInfo> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 新增商品信息
     *
     * @param vo
     */
    void saveSpuInfo(SpuSaveVo vo);

    void saveBaseSpuInfo(SpuInfo infoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);

    /**
     * 商品上架功能
     *
     * @param spuId
     */
    void up(Long spuId);
}

package com.atguigu.product.generator.service;

import com.atguigu.product.generator.domain.SpuInfoDesc;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【pms_spu_info_desc(spu信息介绍)】的数据库操作Service
* @createDate 2025-12-14 14:59:03
*/
public interface SpuInfoDescService extends IService<SpuInfoDesc> {

    PageUtils queryPage(Map<String, Object> params);
    void saveSpuInfoDesc(SpuInfoDesc descEntity);

}

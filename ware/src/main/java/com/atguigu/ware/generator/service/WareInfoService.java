package com.atguigu.ware.generator.service;

import com.atguigu.utils.PageUtils;
import com.atguigu.ware.generator.domain.WareInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【wms_ware_info(仓库信息)】的数据库操作Service
* @createDate 2025-12-13 21:59:22
*/
public interface WareInfoService extends IService<WareInfo> {
    PageUtils queryPage(Map<String, Object> params);
}

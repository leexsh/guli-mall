package com.atguigu.product.generator.service;

import com.atguigu.product.generator.domain.AttrGroup;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【pms_attr_group(属性分组)】的数据库操作Service
* @createDate 2025-12-14 14:59:02
*/
public interface AttrGroupService extends IService<AttrGroup> {

    PageUtils queryPage(Map<String, Object> params, Long catelogId);
}

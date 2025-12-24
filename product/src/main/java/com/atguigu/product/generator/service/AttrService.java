package com.atguigu.product.generator.service;

import com.atguigu.product.generator.domain.Attr;
import com.atguigu.product.vo.AttrVo;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【pms_attr(商品属性)】的数据库操作Service
* @createDate 2025-12-14 14:59:02
*/
public interface AttrService extends IService<Attr> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attr);
}

package com.atguigu.product.generator.service;

import com.atguigu.product.generator.domain.AttrAttrgroupRelation;
import com.atguigu.product.vo.AttrGroupRelationVo;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author zhenglee
* @description 针对表【pms_attr_attrgroup_relation(属性&属性分组关联)】的数据库操作Service
* @createDate 2025-12-14 14:59:02
*/
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelation> {

    PageUtils queryPage(Map<String, Object> params);
    void saveBatch(List<AttrGroupRelationVo> collections);
}

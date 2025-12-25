package com.atguigu.product.generator.service.impl;

import com.atguigu.product.generator.domain.AttrAttrgroupRelation;
import com.atguigu.product.generator.mapper.AttrAttrgroupRelationMapper;
import com.atguigu.product.generator.service.AttrAttrgroupRelationService;
import com.atguigu.product.vo.AttrGroupRelationVo;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author zhenglee
* @description 针对表【pms_attr_attrgroup_relation(属性&属性分组关联)】的数据库操作Service实现
* @createDate 2025-12-14 14:59:02
*/
@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationMapper, AttrAttrgroupRelation>
    implements AttrAttrgroupRelationService{
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrAttrgroupRelation> page = this.page(
                new Query<AttrAttrgroupRelation>().getPage(params),
                new QueryWrapper<AttrAttrgroupRelation>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveBatch(List<AttrGroupRelationVo> collections) {
        List<AttrAttrgroupRelation> list = collections.stream().map(item -> {
            AttrAttrgroupRelation relation = new AttrAttrgroupRelation();
            relation.setAttrId(item.getAttrId());
            relation.setAttrGroupId(item.getAttrGroupId());
            return relation;
        }).collect(Collectors.toList());

        this.saveBatch(list);
    }

}





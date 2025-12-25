package com.atguigu.product.generator.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.atguigu.product.generator.domain.Attr;
import com.atguigu.product.generator.domain.AttrGroup;
import com.atguigu.product.generator.mapper.AttrGroupMapper;
import com.atguigu.product.generator.service.AttrGroupService;
import com.atguigu.product.generator.service.AttrService;
import com.atguigu.product.vo.AttrGroupWithAttrsVo;
import com.atguigu.product.vo.SpuItemAttrGroupVo;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author zhenglee
* @description 针对表【pms_attr_group(属性分组)】的数据库操作Service实现
* @createDate 2025-12-14 14:59:02
*/
@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroup>
    implements AttrGroupService{

    @Autowired
    AttrService attrService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroup> page = this.page(
                new Query<AttrGroup>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        String key = (String) params.get("key");
        QueryWrapper<AttrGroup> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)) {
            wrapper.and(obj -> obj.eq("attr_group_id", key).or().like("attr_group_name", key));
        }
        if (catelogId == 0){
            IPage<AttrGroup> page = this.page(new Query<AttrGroup>().getPage(params), wrapper);
            return new PageUtils(page);
        } else {
            wrapper.eq("catelog_id", catelogId);
            IPage<AttrGroup> page = this.page(new Query<AttrGroup>().getPage(params), wrapper);
            return new PageUtils(page);
        }
    }

    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId) {
        List<AttrGroup> attrGroups = this.list(new QueryWrapper<AttrGroup>().eq("catelog_id", catelogId));

        return attrGroups.stream().map(attrGroup -> {
            AttrGroupWithAttrsVo attrGroupWithAttrsVo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(attrGroup, attrGroupWithAttrsVo);
            List<Attr> attrs = attrService.getRelationAttr(attrGroupWithAttrsVo.getAttrGroupId());
            attrGroupWithAttrsVo.setAttrs(attrs);
            return attrGroupWithAttrsVo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId) {
        AttrGroupMapper baseMapper = this.getBaseMapper();
        return baseMapper.getAttrGroupWithAttrsBySpuId(catalogId, spuId);
    }
}





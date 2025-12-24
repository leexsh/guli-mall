package com.atguigu.product.generator.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.atguigu.constant.ProductConstant;
import com.atguigu.product.generator.domain.AttrAttrgroupRelation;
import com.atguigu.product.generator.domain.AttrGroup;
import com.atguigu.product.generator.domain.Category;
import com.atguigu.product.generator.mapper.AttrAttrgroupRelationMapper;
import com.atguigu.product.generator.mapper.AttrGroupMapper;
import com.atguigu.product.generator.mapper.CategoryMapper;
import com.atguigu.product.generator.service.AttrAttrgroupRelationService;
import com.atguigu.product.generator.service.CategoryService;
import com.atguigu.product.vo.AttrGroupRelationVo;
import com.atguigu.product.vo.AttrRespVo;
import com.atguigu.product.vo.AttrVo;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.product.generator.domain.Attr;
import com.atguigu.product.generator.service.AttrService;
import com.atguigu.product.generator.mapper.AttrMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
* @author zhenglee
* @description 针对表【pms_attr(商品属性)】的数据库操作Service实现
* @createDate 2025-12-14 14:59:02
*/
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr>
    implements AttrService{

    @Autowired
    AttrAttrgroupRelationMapper attrAttrGroupRelationDao;

    @Autowired
    AttrGroupMapper attrGroupMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Attr> page = this.page(
                new Query<Attr>().getPage(params),
                new QueryWrapper<Attr>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveAttr(AttrVo attr) {
        Attr attrEntity = new Attr();
        BeanUtils.copyProperties(attr,attrEntity);
        this.save(attrEntity);
        //2、保存关联关系
        if (attr.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() && attr.getAttrGroupId() != null) {
            AttrAttrgroupRelation relationEntity = new AttrAttrgroupRelation();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attrEntity.getAttrId());
            attrAttrGroupRelationDao.insert(relationEntity);
        }
    }

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type) {
        QueryWrapper<Attr> queryWrapper = new QueryWrapper<Attr>().eq("attr_type", "base".equalsIgnoreCase(type)
                ? ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()
                : ProductConstant.AttrEnum.ATTR_TYPE_SALE.getCode());
        if (catelogId != 0) {
            queryWrapper.eq("catelog_id", catelogId);
        }
        String key = (String) params.get("key");
        if (key != null) {
            queryWrapper.and((wrapper) -> {
                wrapper.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<Attr> page = this.page(
                new Query<Attr>().getPage(params),
                queryWrapper
        );
        PageUtils pageUtils = new PageUtils(page);
        List<Attr> records = page.getRecords();
        List<AttrRespVo> attrRespVos = records.stream().map((item) -> {
            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(item,attrRespVo);
            if ("base".equalsIgnoreCase(type)) {
                AttrAttrgroupRelation attrId = attrAttrGroupRelationDao.selectOne(new QueryWrapper<AttrAttrgroupRelation>().eq("attr_id", item.getAttrId()));
                if (attrId != null && attrId.getAttrGroupId() != null) {
                    AttrGroup g = attrGroupMapper.selectById(attrId.getAttrGroupId());
                    attrRespVo.setGroupName(g.getAttrGroupName());
                }
            }

            Category c = categoryMapper.selectById(item.getCatelogId());
            if (c != null) {
                attrRespVo.setCatelogName(c.getName());
            }
            return attrRespVo;
        }).collect(Collectors.toList());
        pageUtils.setList(attrRespVos);
        return pageUtils;
    }

    @Override
    public AttrRespVo getAttrInfo(Long attrId) {
        AttrRespVo attrRespVo = new AttrRespVo();
        Attr attr = this.getById(attrId);
        BeanUtils.copyProperties(attr, attrRespVo);
        if (attr.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            AttrAttrgroupRelation ar = attrAttrGroupRelationDao.selectOne(new QueryWrapper<AttrAttrgroupRelation>().eq("attr_id", attrId));
            if (ar != null) {
                attrRespVo.setAttrGroupId(ar.getAttrGroupId());
                AttrGroup a = attrGroupMapper.selectById(ar.getAttrGroupId());
                if (a != null) {
                    attrRespVo.setGroupName(a.getAttrGroupName());
                }
            }
        }

        Long catelogId = attr.getCatelogId();
        Long[] catelogPath = categoryService.findCatalogPath(catelogId);
        attrRespVo.setCatelogPath(catelogPath);
        Category c = categoryMapper.selectById(catelogId);
        if (c != null) {
            attrRespVo.setCatelogName(c.getName());
        }
        return attrRespVo;
    }

    @Transactional
    @Override
    public void updateAttr(AttrVo attr) {
        Attr attrEntity = this.getById(attr.getAttrId());
        BeanUtils.copyProperties(attr,attrEntity);
        this.updateById(attrEntity);
        if (attr.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            AttrAttrgroupRelation relationEntity = new AttrAttrgroupRelation();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attrEntity.getAttrId());
            Long count = attrAttrGroupRelationDao.selectCount(new QueryWrapper<AttrAttrgroupRelation>().eq("attr_id", attrEntity.getAttrId()));
            if (count > 0) {
                attrAttrGroupRelationDao.update(relationEntity, new QueryWrapper<AttrAttrgroupRelation>().eq("attr_id", attrEntity.getAttrId()));
            } else {
                //2、再添加
                attrAttrGroupRelationDao.insert(relationEntity);
            }
        }
    }

    @Override
    public List<Attr> getRelationAttr(Long attrgroupId) {
        List<AttrAttrgroupRelation> entities = attrAttrGroupRelationDao.selectList(new QueryWrapper<AttrAttrgroupRelation>().eq("attr_group_id", attrgroupId));
        List<Long> attrIds = entities.stream().map(AttrAttrgroupRelation::getAttrId).collect(Collectors.toList());
        if (attrIds == null || attrIds.size() == 0) {
            return null;
        }
        Collection<Attr> attrs = this.listByIds(attrIds);
        return (List<Attr>) attrs;
    }

    @Override
    public void deleteRelation(AttrGroupRelationVo[] vos) {
        List<AttrAttrgroupRelation> relations = Arrays.asList(vos).stream().map((item) -> {
            AttrAttrgroupRelation relation = new AttrAttrgroupRelation();
            BeanUtils.copyProperties(item, relation);
            return relation;
        }).collect(Collectors.toList());
        attrAttrGroupRelationDao.deleteBatchRelation(relations);
    }

    @Override
    public PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId) {
        AttrGroup attrGroup = attrGroupMapper.selectById(attrgroupId);
        Long catelogId = attrGroup.getCatelogId();
        List<AttrGroup> group  = attrGroupMapper.selectList(new QueryWrapper<AttrGroup>().eq("catelog_id", catelogId));
        List<Long> groupIds = group.stream().map(AttrGroup::getAttrGroupId).collect(Collectors.toList());

        List<AttrAttrgroupRelation> relations = attrAttrGroupRelationDao.selectList(new QueryWrapper<AttrAttrgroupRelation>().in("attr_group_id", groupIds));
        List<Long> relationAttrIds = relations.stream().map(AttrAttrgroupRelation::getAttrId).collect(Collectors.toList());

        QueryWrapper<Attr> wrapper = new QueryWrapper<Attr>().eq("catelog_id", catelogId).eq("attr_type", ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode());
        wrapper.notIn("attr_id", relationAttrIds);
        String key = (String) params.get("key");
        if (StringUtils.isEmpty(key)) {
            wrapper.and((w)->w.eq("attr_id", key). or().like("attr_name", key));
        }
        IPage<Attr> page = this.page(new Query<Attr>().getPage(params), wrapper);
        PageUtils pageUtils = new PageUtils(page);
        return pageUtils;
    }

    @Override
    public List<Long> selectSearchAttrs(List<Long> attrIds) {
        return this.baseMapper.selectSearchAttrsIds(attrIds);
    }

}





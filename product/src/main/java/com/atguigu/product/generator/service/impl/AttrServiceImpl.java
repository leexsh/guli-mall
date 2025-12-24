package com.atguigu.product.generator.service.impl;

import com.atguigu.constant.ProductConstant;
import com.atguigu.product.dao.AttrAttrGroupRelationDao;
import com.atguigu.product.generator.domain.AttrAttrgroupRelation;
import com.atguigu.product.generator.service.AttrAttrgroupRelationService;
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

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【pms_attr(商品属性)】的数据库操作Service实现
* @createDate 2025-12-14 14:59:02
*/
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr>
    implements AttrService{

    @Autowired
    AttrAttrGroupRelationDao attrAttrGroupRelationDao;
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
}





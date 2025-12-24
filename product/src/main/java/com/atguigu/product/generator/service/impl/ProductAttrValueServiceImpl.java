package com.atguigu.product.generator.service.impl;

import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.product.generator.domain.ProductAttrValue;
import com.atguigu.product.generator.service.ProductAttrValueService;
import com.atguigu.product.generator.mapper.ProductAttrValueMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author zhenglee
* @description 针对表【pms_product_attr_value(spu属性值)】的数据库操作Service实现
* @createDate 2025-12-14 14:59:02
*/
@Service
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueMapper, ProductAttrValue>
    implements ProductAttrValueService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductAttrValue> page = this.page(
                new Query<ProductAttrValue>().getPage(params),
                new QueryWrapper<ProductAttrValue>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveProductAttr(List<ProductAttrValue> collect) {

    }


    @Override
    public List<ProductAttrValue> baseAttrListforspu(Long spuId) {
        List<ProductAttrValue> list = baseMapper.selectList(
                new QueryWrapper<ProductAttrValue>().eq("spu_id", spuId)
        );
        return list;
    }

    @Transactional
    @Override
    public void updateSpuAttr(Long spuId, List<ProductAttrValue> entities) {
        this.baseMapper.delete(
                new QueryWrapper<ProductAttrValue>().eq("spu_id", spuId)
        );
        List<ProductAttrValue> colloect = entities.stream().map(item -> {
            item.setSpuId(spuId);
            return item;
        }).collect(Collectors.toList());
        this.saveBatch(colloect);
    }
}





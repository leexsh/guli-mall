package com.atguigu.product.generator.service.impl;

import com.atguigu.product.vo.SkuItemSaleAttrVo;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.product.generator.domain.SkuSaleAttrValue;
import com.atguigu.product.generator.service.SkuSaleAttrValueService;
import com.atguigu.product.generator.mapper.SkuSaleAttrValueMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author zhenglee
* @description 针对表【pms_sku_sale_attr_value(sku销售属性&值)】的数据库操作Service实现
* @createDate 2025-12-14 14:59:03
*/
@Service
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueMapper, SkuSaleAttrValue>
    implements SkuSaleAttrValueService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuSaleAttrValue> page = this.page(
                new Query<SkuSaleAttrValue>().getPage(params),
                new QueryWrapper<>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<SkuItemSaleAttrVo> getSaleAttrBySpuId(Long spuId) {
        SkuSaleAttrValueMapper skuSaleAttrValueMapper = this.getBaseMapper();
        return skuSaleAttrValueMapper.getSaleAttrBySpuId(spuId);
    }

    @Override
    public List<String> getSkuSaleAttrValuesAsStringList(Long skuId) {
        return this.getBaseMapper().getSkuSaleAttrValuesAsStringList(skuId);
    }
}





package com.atguigu.ware.generator.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.atguigu.ware.generator.domain.WareSku;
import com.atguigu.ware.generator.mapper.WareSkuMapper;
import com.atguigu.ware.generator.service.WareSkuService;
import com.atguigu.ware.vo.SkuHasStockVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author zhenglee
* @description 针对表【wms_ware_sku(商品库存)】的数据库操作Service实现
* @createDate 2025-12-13 21:59:22
*/
@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuMapper, WareSku>
    implements WareSkuService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WareSku> queryWrapper = new QueryWrapper<>();
        String skuId = (String) params.get("skuId");
        if(!StringUtils.isEmpty(skuId)){
            queryWrapper.eq("sku_id",skuId);
        }

        String wareId = (String) params.get("wareId");
        if(!StringUtils.isEmpty(wareId)){
            queryWrapper.eq("ware_id",wareId);
        }


        IPage<WareSku> page = this.page(
                new Query<WareSku>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        // todo lizheng
    }

    @Override
    public List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds) {
        return skuIds.stream().map((item)->{
            Long count = baseMapper.getSkuStock(item);
            SkuHasStockVo skuHasStockVo = new SkuHasStockVo();
            skuHasStockVo.setSkuId(item);
            skuHasStockVo.setHasStock(count>0);
            return skuHasStockVo;
        }).toList();
    }
}





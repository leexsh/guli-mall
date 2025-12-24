package com.atguigu.coupon.generator.service.impl;

import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.coupon.generator.domain.SeckillSkuRelation;
import com.atguigu.coupon.generator.service.SeckillSkuRelationService;
import com.atguigu.coupon.generator.mapper.SeckillSkuRelationMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【sms_seckill_sku_relation(秒杀活动商品关联)】的数据库操作Service实现
* @createDate 2025-12-13 21:35:42
*/
@Service
public class SeckillSkuRelationServiceImpl extends ServiceImpl<SeckillSkuRelationMapper, SeckillSkuRelation>
    implements SeckillSkuRelationService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillSkuRelation> page = this.page(
                new Query<SeckillSkuRelation>().getPage(params),
                new QueryWrapper<SeckillSkuRelation>()
        );

        return new PageUtils(page);
    }
}





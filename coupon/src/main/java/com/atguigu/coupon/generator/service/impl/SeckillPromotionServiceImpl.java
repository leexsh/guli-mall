package com.atguigu.coupon.generator.service.impl;

import com.atguigu.coupon.generator.domain.HomeSubjectSpu;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.coupon.generator.domain.SeckillPromotion;
import com.atguigu.coupon.generator.service.SeckillPromotionService;
import com.atguigu.coupon.generator.mapper.SeckillPromotionMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【sms_seckill_promotion(秒杀活动)】的数据库操作Service实现
* @createDate 2025-12-13 21:35:42
*/
@Service("seckillPromotionService")
public class SeckillPromotionServiceImpl extends ServiceImpl<SeckillPromotionMapper, SeckillPromotion>
    implements SeckillPromotionService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillPromotion> page = this.page(
                new Query<SeckillPromotion>().getPage(params),
                new QueryWrapper<SeckillPromotion>()
        );

        return new PageUtils(page);
    }
}





package com.atguigu.coupon.generator.service.impl;

import com.atguigu.coupon.generator.domain.CouponHistory;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.coupon.generator.domain.CouponSpuCategoryRelation;
import com.atguigu.coupon.generator.service.CouponSpuCategoryRelationService;
import com.atguigu.coupon.generator.mapper.CouponSpuCategoryRelationMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【sms_coupon_spu_category_relation(优惠券分类关联)】的数据库操作Service实现
* @createDate 2025-12-13 21:35:42
*/
@Service("couponSpuCategoryRelationService")
public class CouponSpuCategoryRelationServiceImpl extends ServiceImpl<CouponSpuCategoryRelationMapper, CouponSpuCategoryRelation>
    implements CouponSpuCategoryRelationService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponSpuCategoryRelation> page = this.page(
                new Query<CouponSpuCategoryRelation>().getPage(params),
                new QueryWrapper<CouponSpuCategoryRelation>()
        );

        return new PageUtils(page);
    }
}





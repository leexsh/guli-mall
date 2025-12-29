package com.atguigu.coupon.generator.service.impl;

import com.atguigu.coupon.generator.domain.MemberPrice;
import com.atguigu.coupon.generator.domain.SkuFullReduction;
import com.atguigu.coupon.generator.domain.SkuLadder;
import com.atguigu.coupon.generator.mapper.SkuFullReductionMapper;
import com.atguigu.coupon.generator.service.MemberPriceService;
import com.atguigu.coupon.generator.service.SkuFullReductionService;
import com.atguigu.coupon.generator.service.SkuLadderService;
import com.atguigu.to.MemberPriceTo;
import com.atguigu.to.SkuReductionTo;
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
* @description 针对表【sms_sku_full_reduction(商品满减信息)】的数据库操作Service实现
* @createDate 2025-12-13 21:35:42
*/
@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionMapper, SkuFullReduction>
    implements SkuFullReductionService{

    @Autowired
    MemberPriceService memberPriceService;
    @Autowired
    private SkuLadderService skuLadderService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReduction> page = this.page(
                new Query<SkuFullReduction>().getPage(params),
                new QueryWrapper<SkuFullReduction>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuReduction(SkuReductionTo reductionTo) {
        SkuLadder skuLadder = new SkuLadder();
        skuLadder.setSkuId(reductionTo.getSkuId());
        skuLadder.setFullCount(reductionTo.getFullCount());
        skuLadder.setDiscount(reductionTo.getDiscount());
        skuLadder.setAddOther(reductionTo.getCountStatus());
        if (reductionTo.getFullCount() > 0){
            skuLadderService.save(skuLadder);
        }
        SkuFullReduction skuFullReduction = new SkuFullReduction();
        BeanUtils.copyProperties(reductionTo, skuFullReduction);
        if (reductionTo.getFullPrice().compareTo(skuFullReduction.getFullPrice()) == 1){
            this.save(skuFullReduction);
        }
        List<MemberPriceTo> memberPrices = reductionTo.getMemberPrice();
        List<MemberPrice> collect = memberPrices.stream().map(item -> {
            MemberPrice memberPrice = new MemberPrice();
            BeanUtils.copyProperties(item, memberPrice);
            memberPrice.setSkuId(reductionTo.getSkuId());
            return memberPrice;
        }).collect(Collectors.toList());
        memberPrices.addAll(memberPrices);
    }
}





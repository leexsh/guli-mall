package com.atguigu.coupon.generator.service.impl;

import com.atguigu.coupon.generator.domain.MemberPrice;
import com.atguigu.coupon.generator.mapper.MemberPriceMapper;
import com.atguigu.coupon.generator.service.MemberPriceService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【sms_member_price(商品会员价格)】的数据库操作Service实现
* @createDate 2025-12-13 21:35:42
*/
@Service("memberPriceService")
public class MemberPriceServiceImpl extends ServiceImpl<MemberPriceMapper, MemberPrice>
    implements MemberPriceService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberPrice> page = this.page(
                new Query<MemberPrice>().getPage(params),
                new QueryWrapper<MemberPrice>()
        );

        return new PageUtils(page);
    }
}





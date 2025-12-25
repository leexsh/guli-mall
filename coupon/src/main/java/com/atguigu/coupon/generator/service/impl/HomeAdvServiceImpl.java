package com.atguigu.coupon.generator.service.impl;

import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.coupon.generator.domain.HomeAdv;
import com.atguigu.coupon.generator.service.HomeAdvService;
import com.atguigu.coupon.generator.mapper.HomeAdvMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【sms_home_adv(首页轮播广告)】的数据库操作Service实现
* @createDate 2025-12-13 21:35:42
*/
@Service("homeAdvService")
public class HomeAdvServiceImpl extends ServiceImpl<HomeAdvMapper, HomeAdv>
    implements HomeAdvService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HomeAdv> page = this.page(
                new Query<HomeAdv>().getPage(params),
                new QueryWrapper<HomeAdv>()
        );

        return new PageUtils(page);
    }
}





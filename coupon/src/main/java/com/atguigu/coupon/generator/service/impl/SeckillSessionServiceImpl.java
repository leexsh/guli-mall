package com.atguigu.coupon.generator.service.impl;

import com.atguigu.coupon.generator.domain.HomeSubjectSpu;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.coupon.generator.domain.SeckillSession;
import com.atguigu.coupon.generator.service.SeckillSessionService;
import com.atguigu.coupon.generator.mapper.SeckillSessionMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【sms_seckill_session(秒杀活动场次)】的数据库操作Service实现
* @createDate 2025-12-13 21:35:42
*/
@Service("seckillSessionService")
public class SeckillSessionServiceImpl extends ServiceImpl<SeckillSessionMapper, SeckillSession>
    implements SeckillSessionService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillSession> page = this.page(
                new Query<SeckillSession>().getPage(params),
                new QueryWrapper<SeckillSession>()
        );

        return new PageUtils(page);
    }
}





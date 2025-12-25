package com.atguigu.order.generator.service.impl;

import com.atguigu.order.generator.domain.OrderSetting;
import com.atguigu.order.generator.mapper.OrderSettingMapper;
import com.atguigu.order.generator.service.OrderSettingService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【oms_order_setting(订单配置信息)】的数据库操作Service实现
* @createDate 2025-12-13 21:56:33
*/
@Service("orderSettingService")
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSetting>
    implements OrderSettingService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderSetting> page = this.page(
                new Query<OrderSetting>().getPage(params),
                new QueryWrapper<OrderSetting>()
        );

        return new PageUtils(page);
    }
}





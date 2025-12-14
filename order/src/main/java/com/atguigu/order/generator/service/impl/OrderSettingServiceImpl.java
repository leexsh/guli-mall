package com.atguigu.order.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.order.generator.domain.OrderSetting;
import com.atguigu.order.generator.service.OrderSettingService;
import com.atguigu.order.generator.mapper.OrderSettingMapper;
import org.springframework.stereotype.Service;

/**
* @author zhenglee
* @description 针对表【oms_order_setting(订单配置信息)】的数据库操作Service实现
* @createDate 2025-12-13 21:56:33
*/
@Service
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSetting>
    implements OrderSettingService{

}





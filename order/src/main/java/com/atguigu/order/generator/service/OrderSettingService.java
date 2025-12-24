package com.atguigu.order.generator.service;

import com.atguigu.order.generator.domain.OrderSetting;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhenglee
* @description 针对表【oms_order_setting(订单配置信息)】的数据库操作Service
* @createDate 2025-12-13 21:56:33
*/
public interface OrderSettingService extends IService<OrderSetting> {
    PageUtils queryPage(Map<String, Object> params);
}

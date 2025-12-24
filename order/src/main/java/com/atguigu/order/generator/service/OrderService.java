package com.atguigu.order.generator.service;

import com.atguigu.order.generator.domain.Order;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【oms_order(订单)】的数据库操作Service
* @createDate 2025-12-13 21:56:33
*/
public interface OrderService extends IService<Order> {
    PageUtils queryPage(Map<String, Object> params);
}

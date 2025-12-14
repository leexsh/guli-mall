package com.atguigu.order.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.order.generator.domain.Order;
import com.atguigu.order.generator.service.OrderService;
import com.atguigu.order.generator.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author zhenglee
* @description 针对表【oms_order(订单)】的数据库操作Service实现
* @createDate 2025-12-13 21:56:33
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}





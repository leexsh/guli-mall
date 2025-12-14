package com.atguigu.order.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.order.generator.domain.OrderItem;
import com.atguigu.order.generator.service.OrderItemService;
import com.atguigu.order.generator.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

/**
* @author zhenglee
* @description 针对表【oms_order_item(订单项信息)】的数据库操作Service实现
* @createDate 2025-12-13 21:56:33
*/
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem>
    implements OrderItemService{

}





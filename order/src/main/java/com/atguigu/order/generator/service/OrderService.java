package com.atguigu.order.generator.service;

import com.atguigu.order.generator.domain.Order;
import com.atguigu.order.vo.*;
import com.atguigu.to.SeckillOrderTo;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
* @author zhenglee
* @description 针对表【oms_order(订单)】的数据库操作Service
* @createDate 2025-12-13 21:56:33
*/
public interface OrderService extends IService<Order> {

    PageUtils queryPage(Map<String, Object> params);
    //订单确认页返回需要的数据
    OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException;

    SubmitOrderResponseVo submitOrder(OrderSubmitVo vo);

    Order getOrderByOrderSn(String orderSn);

    void closeOrder(Order entity);

    PayVo getOrderPay(String orderSn);

    PageUtils queryPageWithItem(Map<String, Object> params);

    String handlePayResult(PayAsyncVo vo);

    void createSeckillOrder(SeckillOrderTo orderTo);
}

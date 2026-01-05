package com.atguigu.order.generator.service.impl;

import com.atguigu.order.generator.domain.Order;
import com.atguigu.order.generator.mapper.OrderMapper;
import com.atguigu.order.generator.service.OrderService;
import com.atguigu.order.vo.*;
import com.atguigu.to.SeckillOrderTo;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.atguigu.vo.MemberRespVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
* @author zhenglee
* @description 针对表【oms_order(订单)】的数据库操作Service实现
* @createDate 2025-12-13 21:56:33
*/
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Order> page = this.page(
                new Query<Order>().getPage(params),
                new QueryWrapper<Order>()
        );

        return new PageUtils(page);
    }

    @Override
    public OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException {
        OrderConfirmVo orderConfirmVo = new OrderConfirmVo();
        MemberRespVo memberRespVo = new MemberRespVo();
        return null;
    }

    @Override
    public SubmitOrderResponseVo submitOrder(OrderSubmitVo vo) {
        return null;
    }

    @Override
    public Order getOrderByOrderSn(String orderSn) {
        return null;
    }

    @Override
    public void closeOrder(Order entity) {

    }

    @Override
    public PayVo getOrderPay(String orderSn) {
        return null;
    }

    @Override
    public PageUtils queryPageWithItem(Map<String, Object> params) {
        return null;
    }

    @Override
    public String handlePayResult(PayAsyncVo vo) {
        return "";
    }

    @Override
    public void createSeckillOrder(SeckillOrderTo orderTo) {

    }
}





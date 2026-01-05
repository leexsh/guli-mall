package com.atguigu.order.to;

import com.atguigu.order.generator.domain.Order;
import com.atguigu.order.generator.domain.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderCreateTo {
    private Order order;
    private List<OrderItem> items;
    private BigDecimal payPrice;
    private BigDecimal fare;
}

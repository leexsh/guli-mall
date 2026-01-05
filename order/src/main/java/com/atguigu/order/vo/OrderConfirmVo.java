package com.atguigu.order.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderConfirmVo {
    List<MemberAddress> addresses;
    List<OrderItemVo> items;
    BigDecimal totalPrice;
    BigDecimal PayPrice;
}

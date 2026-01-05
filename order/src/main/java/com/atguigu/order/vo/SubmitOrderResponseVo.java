package com.atguigu.order.vo;

import com.atguigu.order.generator.domain.Order;
import lombok.Data;

@Data
public class SubmitOrderResponseVo {
    private Order order;
    private Integer code;//0成功

}

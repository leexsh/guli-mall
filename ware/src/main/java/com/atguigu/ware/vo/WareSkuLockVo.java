package com.atguigu.ware.vo;

import lombok.Data;

@Data
public class WareSkuLockVo {
    private String orderSn;
    private List<OrderItemVo> locks;

}

package com.atguigu.to;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpuBound {
    private Long spuId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}

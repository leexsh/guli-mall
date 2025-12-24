package com.atguigu.order.generator.service;

import com.atguigu.order.generator.domain.OrderReturnReason;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author zhenglee
* @description 针对表【oms_order_return_reason(退货原因)】的数据库操作Service
* @createDate 2025-12-13 21:56:33
*/
public interface OrderReturnReasonService extends IService<OrderReturnReason> {
    PageUtils queryPage(Map<String, Object> params);
}

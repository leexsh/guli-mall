package com.atguigu.order.generator.service;

import com.atguigu.order.generator.domain.OrderOperateHistory;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【oms_order_operate_history(订单操作历史记录)】的数据库操作Service
* @createDate 2025-12-13 21:56:33
*/
public interface OrderOperateHistoryService extends IService<OrderOperateHistory> {
    PageUtils queryPage(Map<String, Object> params);
}

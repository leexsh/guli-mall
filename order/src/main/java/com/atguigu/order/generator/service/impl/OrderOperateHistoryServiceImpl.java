package com.atguigu.order.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.order.generator.domain.OrderOperateHistory;
import com.atguigu.order.generator.service.OrderOperateHistoryService;
import com.atguigu.order.generator.mapper.OrderOperateHistoryMapper;
import org.springframework.stereotype.Service;

/**
* @author zhenglee
* @description 针对表【oms_order_operate_history(订单操作历史记录)】的数据库操作Service实现
* @createDate 2025-12-13 21:56:33
*/
@Service
public class OrderOperateHistoryServiceImpl extends ServiceImpl<OrderOperateHistoryMapper, OrderOperateHistory>
    implements OrderOperateHistoryService{

}





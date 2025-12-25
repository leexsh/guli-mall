package com.atguigu.order.generator.service.impl;

import com.atguigu.order.generator.domain.OrderOperateHistory;
import com.atguigu.order.generator.mapper.OrderOperateHistoryMapper;
import com.atguigu.order.generator.service.OrderOperateHistoryService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【oms_order_operate_history(订单操作历史记录)】的数据库操作Service实现
* @createDate 2025-12-13 21:56:33
*/
@Service("orderOperateHistoryService")
public class OrderOperateHistoryServiceImpl extends ServiceImpl<OrderOperateHistoryMapper, OrderOperateHistory>
    implements OrderOperateHistoryService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderOperateHistory> page = this.page(
                new Query<OrderOperateHistory>().getPage(params),
                new QueryWrapper<OrderOperateHistory>()
        );

        return new PageUtils(page);
    }
}





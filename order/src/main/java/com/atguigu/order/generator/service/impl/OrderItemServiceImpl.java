package com.atguigu.order.generator.service.impl;

import com.atguigu.order.generator.domain.OrderItem;
import com.atguigu.order.generator.mapper.OrderItemMapper;
import com.atguigu.order.generator.service.OrderItemService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【oms_order_item(订单项信息)】的数据库操作Service实现
* @createDate 2025-12-13 21:56:33
*/
@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem>
    implements OrderItemService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderItem> page = this.page(
                new Query<OrderItem>().getPage(params),
                new QueryWrapper<OrderItem>()
        );

        return new PageUtils(page);
    }
}





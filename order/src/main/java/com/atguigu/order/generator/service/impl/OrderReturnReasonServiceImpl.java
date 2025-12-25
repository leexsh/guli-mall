package com.atguigu.order.generator.service.impl;

import com.atguigu.order.generator.domain.OrderReturnReason;
import com.atguigu.order.generator.mapper.OrderReturnReasonMapper;
import com.atguigu.order.generator.service.OrderReturnReasonService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【oms_order_return_reason(退货原因)】的数据库操作Service实现
* @createDate 2025-12-13 21:56:33
*/
@Service("orderReturnReasonService")
public class OrderReturnReasonServiceImpl extends ServiceImpl<OrderReturnReasonMapper, OrderReturnReason>
    implements OrderReturnReasonService{
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderReturnReason> page = this.page(
                new Query<OrderReturnReason>().getPage(params),
                new QueryWrapper<OrderReturnReason>()
        );

        return new PageUtils(page);
    }
}





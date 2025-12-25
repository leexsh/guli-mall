package com.atguigu.order.generator.service.impl;

import com.atguigu.order.generator.domain.OrderReturnApply;
import com.atguigu.order.generator.mapper.OrderReturnApplyMapper;
import com.atguigu.order.generator.service.OrderReturnApplyService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【oms_order_return_apply(订单退货申请)】的数据库操作Service实现
* @createDate 2025-12-13 21:56:33
*/
@Service("orderReturnApplyService")
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyMapper, OrderReturnApply>
    implements OrderReturnApplyService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderReturnApply> page = this.page(
                new Query<OrderReturnApply>().getPage(params),
                new QueryWrapper<OrderReturnApply>()
        );

        return new PageUtils(page);
    }
}





package com.atguigu.order.generator.service.impl;

import com.atguigu.order.generator.domain.PaymentInfo;
import com.atguigu.order.generator.mapper.PaymentInfoMapper;
import com.atguigu.order.generator.service.PaymentInfoService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【oms_payment_info(支付信息表)】的数据库操作Service实现
* @createDate 2025-12-13 21:56:33
*/
@Service("paymentInfoService")
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo>
    implements PaymentInfoService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PaymentInfo> page = this.page(
                new Query<PaymentInfo>().getPage(params),
                new QueryWrapper<PaymentInfo>()
        );

        return new PageUtils(page);
    }
}





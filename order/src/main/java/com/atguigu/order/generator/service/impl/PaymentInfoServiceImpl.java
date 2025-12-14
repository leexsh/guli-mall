package com.atguigu.order.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.order.generator.domain.PaymentInfo;
import com.atguigu.order.generator.service.PaymentInfoService;
import com.atguigu.order.generator.mapper.PaymentInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author zhenglee
* @description 针对表【oms_payment_info(支付信息表)】的数据库操作Service实现
* @createDate 2025-12-13 21:56:33
*/
@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo>
    implements PaymentInfoService{

}





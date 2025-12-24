package com.atguigu.order.generator.service;

import com.atguigu.order.generator.domain.PaymentInfo;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【oms_payment_info(支付信息表)】的数据库操作Service
* @createDate 2025-12-13 21:56:33
*/
public interface PaymentInfoService extends IService<PaymentInfo> {
    PageUtils queryPage(Map<String, Object> params);
}

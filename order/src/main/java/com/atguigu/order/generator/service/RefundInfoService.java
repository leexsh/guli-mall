package com.atguigu.order.generator.service;

import com.atguigu.order.generator.domain.RefundInfo;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【oms_refund_info(退款信息)】的数据库操作Service
* @createDate 2025-12-13 21:56:33
*/
public interface RefundInfoService extends IService<RefundInfo> {
    PageUtils queryPage(Map<String, Object> params);
}

package com.atguigu.order.generator.service.impl;

import com.atguigu.order.generator.domain.RefundInfo;
import com.atguigu.order.generator.mapper.RefundInfoMapper;
import com.atguigu.order.generator.service.RefundInfoService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【oms_refund_info(退款信息)】的数据库操作Service实现
* @createDate 2025-12-13 21:56:33
*/
@Service("refundInfoService")
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoMapper, RefundInfo>
    implements RefundInfoService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RefundInfo> page = this.page(
                new Query<RefundInfo>().getPage(params),
                new QueryWrapper<RefundInfo>()
        );

        return new PageUtils(page);
    }
}





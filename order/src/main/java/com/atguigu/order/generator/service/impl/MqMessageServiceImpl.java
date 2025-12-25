package com.atguigu.order.generator.service.impl;

import com.atguigu.order.generator.domain.MqMessage;
import com.atguigu.order.generator.mapper.MqMessageMapper;
import com.atguigu.order.generator.service.MqMessageService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【mq_message】的数据库操作Service实现
* @createDate 2025-12-13 21:56:33
*/
@Service("mqMessageService")
public class MqMessageServiceImpl extends ServiceImpl<MqMessageMapper, MqMessage>
    implements MqMessageService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MqMessage> page = this.page(
                new Query<MqMessage>().getPage(params),
                new QueryWrapper<MqMessage>()
        );

        return new PageUtils(page);
    }
}





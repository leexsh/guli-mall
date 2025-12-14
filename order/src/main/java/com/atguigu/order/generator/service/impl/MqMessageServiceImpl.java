package com.atguigu.order.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.order.generator.domain.MqMessage;
import com.atguigu.order.generator.service.MqMessageService;
import com.atguigu.order.generator.mapper.MqMessageMapper;
import org.springframework.stereotype.Service;

/**
* @author zhenglee
* @description 针对表【mq_message】的数据库操作Service实现
* @createDate 2025-12-13 21:56:33
*/
@Service
public class MqMessageServiceImpl extends ServiceImpl<MqMessageMapper, MqMessage>
    implements MqMessageService{

}





package com.atguigu.product.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.product.generator.domain.SmsHomeSubject;
import com.atguigu.product.generator.service.SmsHomeSubjectService;
import com.atguigu.product.generator.mapper.SmsHomeSubjectMapper;
import org.springframework.stereotype.Service;

/**
* @author zhenglee
* @description 针对表【sms_home_subject(首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】)】的数据库操作Service实现
* @createDate 2025-12-14 14:59:03
*/
@Service
public class SmsHomeSubjectServiceImpl extends ServiceImpl<SmsHomeSubjectMapper, SmsHomeSubject>
    implements SmsHomeSubjectService{

}





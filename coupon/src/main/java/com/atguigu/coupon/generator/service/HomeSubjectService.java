package com.atguigu.coupon.generator.service;

import com.atguigu.coupon.generator.domain.HomeSubject;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【sms_home_subject(首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】)】的数据库操作Service
* @createDate 2025-12-13 21:35:42
*/
public interface HomeSubjectService extends IService<HomeSubject> {
    PageUtils queryPage(Map<String, Object> params);
}

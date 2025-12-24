package com.atguigu.coupon.generator.service;

import com.atguigu.coupon.generator.domain.HomeSubjectSpu;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【sms_home_subject_spu(专题商品)】的数据库操作Service
* @createDate 2025-12-13 21:35:42
*/
public interface HomeSubjectSpuService extends IService<HomeSubjectSpu> {
    PageUtils queryPage(Map<String, Object> params);
}

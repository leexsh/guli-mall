package com.atguigu.coupon.generator.service.impl;

import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.coupon.generator.domain.HomeSubject;
import com.atguigu.coupon.generator.service.HomeSubjectService;
import com.atguigu.coupon.generator.mapper.HomeSubjectMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【sms_home_subject(首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】)】的数据库操作Service实现
* @createDate 2025-12-13 21:35:42
*/
@Service("homeSubjectService")
public class HomeSubjectServiceImpl extends ServiceImpl<HomeSubjectMapper, HomeSubject>
    implements HomeSubjectService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HomeSubject> page = this.page(
                new Query<HomeSubject>().getPage(params),
                new QueryWrapper<HomeSubject>()
        );

        return new PageUtils(page);
    }
}





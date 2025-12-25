package com.atguigu.coupon.generator.service.impl;

import com.atguigu.coupon.generator.domain.HomeSubjectSpu;
import com.atguigu.coupon.generator.mapper.HomeSubjectSpuMapper;
import com.atguigu.coupon.generator.service.HomeSubjectSpuService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【sms_home_subject_spu(专题商品)】的数据库操作Service实现
* @createDate 2025-12-13 21:35:42
*/
@Service("homeSubjectSpuService")
public class HomeSubjectSpuServiceImpl extends ServiceImpl<HomeSubjectSpuMapper, HomeSubjectSpu>
    implements HomeSubjectSpuService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HomeSubjectSpu> page = this.page(
                new Query<HomeSubjectSpu>().getPage(params),
                new QueryWrapper<HomeSubjectSpu>()
        );

        return new PageUtils(page);
    }
}





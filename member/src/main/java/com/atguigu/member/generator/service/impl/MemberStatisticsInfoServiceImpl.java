package com.atguigu.member.generator.service.impl;

import com.atguigu.member.generator.domain.MemberStatisticsInfo;
import com.atguigu.member.generator.mapper.MemberStatisticsInfoMapper;
import com.atguigu.member.generator.service.MemberStatisticsInfoService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【ums_member_statistics_info(会员统计信息)】的数据库操作Service实现
* @createDate 2025-12-13 21:48:50
*/
@Service("memberStatisticsInfoService")
public class MemberStatisticsInfoServiceImpl extends ServiceImpl<MemberStatisticsInfoMapper, MemberStatisticsInfo>
    implements MemberStatisticsInfoService{


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberStatisticsInfo> page = this.page(
                new Query<MemberStatisticsInfo>().getPage(params),
                new QueryWrapper<MemberStatisticsInfo>()
        );

        return new PageUtils(page);
    }
}





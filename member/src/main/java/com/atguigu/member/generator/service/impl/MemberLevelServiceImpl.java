package com.atguigu.member.generator.service.impl;

import com.atguigu.member.generator.domain.MemberLevel;
import com.atguigu.member.generator.mapper.MemberLevelMapper;
import com.atguigu.member.generator.service.MemberLevelService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【ums_member_level(会员等级)】的数据库操作Service实现
* @createDate 2025-12-13 21:48:49
*/
@Service("memberLevelService")
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelMapper, MemberLevel>
    implements MemberLevelService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberLevel> page = this.page(
                new Query<MemberLevel>().getPage(params),
                new QueryWrapper<MemberLevel>()
        );

        return new PageUtils(page);
    }
}





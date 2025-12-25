package com.atguigu.member.generator.service.impl;

import com.atguigu.member.generator.domain.MemberCollectSubject;
import com.atguigu.member.generator.mapper.MemberCollectSubjectMapper;
import com.atguigu.member.generator.service.MemberCollectSubjectService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【ums_member_collect_subject(会员收藏的专题活动)】的数据库操作Service实现
* @createDate 2025-12-13 21:48:49
*/
@Service("memberCollectSubjectService")
public class MemberCollectSubjectServiceImpl extends ServiceImpl<MemberCollectSubjectMapper, MemberCollectSubject>
    implements MemberCollectSubjectService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
       IPage<MemberCollectSubject> page = this.page(
                new Query<MemberCollectSubject>().getPage(params),
                new QueryWrapper<MemberCollectSubject>()
        );

        return new PageUtils(page);
    }
}





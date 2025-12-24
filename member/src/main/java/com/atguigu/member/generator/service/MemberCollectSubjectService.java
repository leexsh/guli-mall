package com.atguigu.member.generator.service;

import com.atguigu.member.generator.domain.MemberCollectSubject;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【ums_member_collect_subject(会员收藏的专题活动)】的数据库操作Service
* @createDate 2025-12-13 21:48:49
*/
public interface MemberCollectSubjectService extends IService<MemberCollectSubject> {
    PageUtils queryPage(Map<String, Object> params);
}

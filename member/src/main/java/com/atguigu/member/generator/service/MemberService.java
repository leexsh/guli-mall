package com.atguigu.member.generator.service;

import com.atguigu.member.generator.domain.Member;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【ums_member(会员)】的数据库操作Service
* @createDate 2025-12-13 21:48:49
*/
public interface MemberService extends IService<Member> {
    PageUtils queryPage(Map<String, Object> params);
}

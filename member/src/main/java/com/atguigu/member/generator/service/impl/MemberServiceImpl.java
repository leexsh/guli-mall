package com.atguigu.member.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.member.generator.domain.Member;
import com.atguigu.member.generator.service.MemberService;
import com.atguigu.member.generator.mapper.MemberMapper;
import org.springframework.stereotype.Service;

/**
* @author zhenglee
* @description 针对表【ums_member(会员)】的数据库操作Service实现
* @createDate 2025-12-13 21:48:49
*/
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
    implements MemberService{

}





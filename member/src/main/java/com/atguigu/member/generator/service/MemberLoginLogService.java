package com.atguigu.member.generator.service;

import com.atguigu.member.generator.domain.MemberLoginLog;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【ums_member_login_log(会员登录记录)】的数据库操作Service
* @createDate 2025-12-13 21:48:49
*/
public interface MemberLoginLogService extends IService<MemberLoginLog> {
    PageUtils queryPage(Map<String, Object> params);
}

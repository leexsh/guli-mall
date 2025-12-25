package com.atguigu.member.generator.service.impl;

import com.atguigu.member.generator.domain.MemberLoginLog;
import com.atguigu.member.generator.mapper.MemberLoginLogMapper;
import com.atguigu.member.generator.service.MemberLoginLogService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【ums_member_login_log(会员登录记录)】的数据库操作Service实现
* @createDate 2025-12-13 21:48:49
*/
@Service("memberLoginLogService")
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogMapper, MemberLoginLog>
    implements MemberLoginLogService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberLoginLog> page = this.page(
                new Query<MemberLoginLog>().getPage(params),
                new QueryWrapper<MemberLoginLog>()
        );

        return new PageUtils(page);
    }
}





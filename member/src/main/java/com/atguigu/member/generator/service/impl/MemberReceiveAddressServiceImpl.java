package com.atguigu.member.generator.service.impl;

import com.atguigu.member.generator.domain.MemberReceiveAddress;
import com.atguigu.member.generator.mapper.MemberReceiveAddressMapper;
import com.atguigu.member.generator.service.MemberReceiveAddressService;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【ums_member_receive_address(会员收货地址)】的数据库操作Service实现
* @createDate 2025-12-13 21:48:49
*/
@Service("memberReceiveAddressService")
public class MemberReceiveAddressServiceImpl extends ServiceImpl<MemberReceiveAddressMapper, MemberReceiveAddress>
    implements MemberReceiveAddressService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberReceiveAddress> page = this.page(
                new Query<MemberReceiveAddress>().getPage(params),
                new QueryWrapper<MemberReceiveAddress>()
        );

        return new PageUtils(page);
    }
}





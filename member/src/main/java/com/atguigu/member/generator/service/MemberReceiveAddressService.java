package com.atguigu.member.generator.service;

import com.atguigu.member.generator.domain.MemberReceiveAddress;
import com.atguigu.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author zhenglee
* @description 针对表【ums_member_receive_address(会员收货地址)】的数据库操作Service
* @createDate 2025-12-13 21:48:49
*/
public interface MemberReceiveAddressService extends IService<MemberReceiveAddress> {
    PageUtils queryPage(Map<String, Object> params);
}

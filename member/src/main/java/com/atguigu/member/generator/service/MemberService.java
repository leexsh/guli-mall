package com.atguigu.member.generator.service;

import com.atguigu.member.exception.PhoneException;
import com.atguigu.member.exception.UserNameException;
import com.atguigu.member.generator.domain.Member;
import com.atguigu.member.vo.MemberUserLoginVo;
import com.atguigu.member.vo.MemberUserRegisterVo;
import com.atguigu.member.vo.SocialUser;
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

    /**
     * 用户注册
     * @param vo
     */
    void register(MemberUserRegisterVo vo);

    /**
     * 判断邮箱是否重复
     * @param phone
     * @return
     */
    void checkPhoneUnique(String phone) throws PhoneException;

    /**
     * 判断用户名是否重复
     * @param userName
     * @return
     */
    void checkUserNameUnique(String userName) throws UserNameException;

    /**
     * 用户登录
     * @param vo
     * @return
     */
    Member login(MemberUserLoginVo vo);

    /**
     * 社交用户的登录
     * @param socialUser
     * @return
     */
    Member login(SocialUser socialUser) throws Exception;

    /**
     * 微信登录
     * @param accessTokenInfo
     * @return
     */
    Member login(String accessTokenInfo);
}

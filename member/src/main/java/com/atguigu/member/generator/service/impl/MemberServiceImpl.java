package com.atguigu.member.generator.service.impl;

import com.atguigu.member.exception.PhoneException;
import com.atguigu.member.exception.UserNameException;
import com.atguigu.member.generator.domain.Member;
import com.atguigu.member.generator.domain.MemberLevel;
import com.atguigu.member.generator.mapper.MemberLevelMapper;
import com.atguigu.member.generator.mapper.MemberMapper;
import com.atguigu.member.generator.service.MemberService;
import com.atguigu.member.vo.MemberUserLoginVo;
import com.atguigu.member.vo.MemberUserRegisterVo;
import com.atguigu.member.vo.SocialUser;
import com.atguigu.utils.PageUtils;
import com.atguigu.utils.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
* @author zhenglee
* @description 针对表【ums_member(会员)】的数据库操作Service实现
* @createDate 2025-12-13 21:48:49
*/
@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
    implements MemberService{

    @Autowired
    MemberLevelMapper memberLevelMapper;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Member> page = this.page(
                new Query<Member>().getPage(params),
                new QueryWrapper<Member>()
        );

        return new PageUtils(page);
    }

    @Override
    public void register(MemberUserRegisterVo vo) {
        Member member = new Member();
        MemberLevel level = memberLevelMapper.getDefaultMemberLevel();
        member.setLevelId(level.getId());

        checkPhoneUnique(vo.getPhone());
        checkUserNameUnique(vo.getUserName());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(vo.getPassword());
        member.setPassword(encode);
        member.setNickname(vo.getUserName());
        member.setUsername(vo.getUserName());
        member.setMobile(vo.getPhone());
        member.setCreateTime(new Date());
        this.save(member);
    }

    @Override
    public void checkPhoneUnique(String phone) throws PhoneException {
        Long phoneCount = baseMapper.selectCount(new QueryWrapper<Member>().eq("mobile", phone));
        if(phoneCount > 0){
            throw new PhoneException();
        }
    }

    @Override
    public void checkUserNameUnique(String userName) throws UserNameException {
        Long userNameCount = baseMapper.selectCount(new QueryWrapper<Member>().eq("username", userName));
        if(userNameCount > 0){
            throw new UserNameException();
        }
    }

    @Override
    public Member login(MemberUserLoginVo vo) {
        String loginacct = vo.getLoginacct();
        String password = vo.getPassword();

        //1、去数据库查询 SELECT * FROM ums_member WHERE username = ? OR mobile = ?
        Member memberEntity = this.baseMapper.selectOne(new QueryWrapper<Member>()
                .eq("username", loginacct).or().eq("mobile", loginacct));

        if (memberEntity == null) {
            //登录失败
            return null;
        } else {
            //获取到数据库里的password
            String password1 = memberEntity.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            //进行密码匹配
            boolean matches = passwordEncoder.matches(password, password1);
            if (matches) {
                //登录成功
                return memberEntity;
            }
        }
        return null;
    }

    @Override
    public Member login(SocialUser socialUser) throws Exception {
        return null;
    }

    @Override
    public Member login(String accessTokenInfo) {
        return null;
    }
}





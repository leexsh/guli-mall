package com.atguigu.member;

import com.atguigu.member.generator.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberApplicationTests {


    @Autowired
    MemberService memberService;
    @Test
    void contextLoads() {
        memberService.list().forEach(System.out::println);
        System.out.println("success"+ memberService.count());
    }

}

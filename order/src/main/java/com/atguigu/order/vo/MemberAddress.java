package com.atguigu.order.vo;

import lombok.Data;

@Data
public class MemberAddress {
    private Long id;
    private Long memberId;
    private String name;
    private String phone;
    private String postCode;
    private String address;
    private String detailAddress;
    private String province;
    private String city;
}

package com.atguigu.member.exception;

public class UserNameException extends RuntimeException {
    public UserNameException() {
        super("用户名已存在");
    }
    public UserNameException(String message) {
        super(message);
    }
}

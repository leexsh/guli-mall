package com.atguigu.exception;

public enum BizCodeEnum {
    VALID_EXCEPTION(10001, "参数校验失败"),
    UNKNOW_EXCEPTION(10000, "未知异常");

    BizCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private final Integer code;
    private final String msg;
}

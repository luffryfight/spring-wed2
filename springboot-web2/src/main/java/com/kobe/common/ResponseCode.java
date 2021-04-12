package com.kobe.common;

/**
 * 作用：
 * 2020/12/2
 */
public enum  ResponseCode {
    //成功

    SUCCESS(0,"SUCCESS"),
    //失败
    ERROR(1,"ERROR"),
    //没有登录
    NEDED_LOGIN(10,"NEDED_LOGIN"),
    //参数没法
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT"),
    //验证手机
    NEED_PHONE(20,"NEED_PHONE");

    private final int code;
    private final String des;

    ResponseCode(int code, String des) {
        this.code = code;
        this.des = des;
    }

    public int getCode() {
        return code;
    }

    public String getDes() {
        return des;
    }
}

package com.origami.springsecurity.common;

/**
 * @author origami1203
 * @date 2021-5-3 8:52
 * @description TODO
 */
public enum Code {
    SUCCESS(20000,"请求成功"),
    BAD_REQUEST(40000,"请求错误"),
    ACCESS_DENY(40001,"权限不足"),
    NOT_LOGIN(40001,"未登录");
    
    private final int code;
    private final String desc;
    
    Code(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public int value() {
        return this.code;
    }
    
    public String desc() {
        return this.desc;
    }
}

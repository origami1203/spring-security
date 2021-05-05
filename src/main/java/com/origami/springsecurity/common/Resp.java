package com.origami.springsecurity.common;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

/**
 * @author origami1203
 * @date 2021-5-3 8:37
 * @description TODO
 */
@Data
@Accessors(chain = true)
public class Resp<T> {
    private String message;
    private int code;
    private T data;
    
    private Resp() {
    }
    
    public static <T> Resp<T> resp(int code, String message) {
        return resp(code, message, null);
    }
    
    public static <T> Resp<T> resp(int code, String message, T data) {
        return new Resp<T>().setCode(code).setMessage(message).setData(data);
    }
    
    public static <T> Resp<T> ok() {
        return ok(null);
    }
    
    
    public static <T> Resp<T> ok(T data) {
        return resp(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }
    
    
    public static <T> Resp<T> failed() {
        return failed(Code.BAD_REQUEST.value(), Code.BAD_REQUEST.desc(), null);
    }
    
    
    public static <T> Resp<T> failed(String message) {
        return failed(Code.BAD_REQUEST.value(), message, null);
    }
    
    public static <T> Resp<T> failed(int code,String message) {
        return failed(code,message,null);
    }
    
    public static <T> Resp<T> failed(int code,String message,T data) {
        return resp(code, message, data);
    }
}

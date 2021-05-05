package com.origami.springsecurity.exception.base;

/**
 * @author origami1203
 * @date 2021-5-3 8:34
 * @description TODO
 */
public class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }
    
    public BaseException(String message,Throwable cause) {
        super(message,cause);
    }
}

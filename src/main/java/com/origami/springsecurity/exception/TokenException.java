package com.origami.springsecurity.exception;

import com.origami.springsecurity.exception.base.BaseException;

/**
 * @author origami1203
 * @date 2021-5-3 9:13
 * @description TODO
 */
public class TokenException extends BaseException {
    public TokenException(String message) {
        super(message);
    }
    
    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }
}

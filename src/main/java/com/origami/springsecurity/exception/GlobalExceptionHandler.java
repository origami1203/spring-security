package com.origami.springsecurity.exception;

import com.origami.springsecurity.common.Resp;
import com.origami.springsecurity.exception.base.BaseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author origami1203
 * @date 2021-5-3 8:41
 * @description 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BaseException.class)
    public Resp handleBaseException(BaseException e) {
        return Resp.failed(e.getMessage());
    }
}

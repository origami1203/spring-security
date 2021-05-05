package com.origami.springsecurity.handler;

import com.origami.springsecurity.common.Code;
import com.origami.springsecurity.utils.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author origami1203
 * @date 2021-5-4 11:06
 * @description 登录后权限不足,处理方式
 */
@Component
public class TokenAccessDeniedHandler implements AccessDeniedHandler {
    
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseUtil.response(response, 200, Code.ACCESS_DENY.value(), Code.ACCESS_DENY.desc());
    }
}

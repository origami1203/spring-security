package com.origami.springsecurity.handler;

import com.origami.springsecurity.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author origami1203
 * @date 2021-5-4 8:49
 * @description 未登录用户访问需要权限的资源时的操作
 */

@Slf4j
@Component
public class NotLoginAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        log.warn("用户需要登录，访问[{}]失败，AuthenticationException={}", request.getRequestURI(), authException);
        
        ResponseUtil.unauthorized(response);
    }
}

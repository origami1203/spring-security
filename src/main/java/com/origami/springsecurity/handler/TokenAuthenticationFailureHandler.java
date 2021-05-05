package com.origami.springsecurity.handler;

import com.origami.springsecurity.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author origami1203
 * @date 2021-5-4 8:46
 * @description TODO
 */
@Slf4j
@Component
public class TokenAuthenticationFailureHandler implements AuthenticationFailureHandler {
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String msg = "";
        String username = request.getParameter("username");
        String ip = request.getRemoteAddr();
        if (exception instanceof BadCredentialsException) {
            msg = "用户名或密码错误,请检查后重新登录";
        } else if (exception instanceof DisabledException) {
            msg = "用户已被禁用,请联系管理员";
        } else if (exception instanceof CredentialsExpiredException) {
            msg = "密码已过期,请重新登录";
        } else {
            msg = exception.getMessage();
        }
    
        log.error("用户 [{}] 登录失败 - ip地址: {}", username, ip);
    
        ResponseUtil.unauthorized(response, msg);
        
    }
}

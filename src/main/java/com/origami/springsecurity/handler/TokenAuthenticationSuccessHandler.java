package com.origami.springsecurity.handler;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONObject;
import com.origami.springsecurity.domain.LoginUser;
import com.origami.springsecurity.domain.entity.Token;
import com.origami.springsecurity.service.TokenService;
import com.origami.springsecurity.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author origami1203
 * @date 2021-5-3 17:31
 * @description TODO
 */
@Slf4j
@Component
public class TokenAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    
    @Autowired
    private TokenService tokenService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
    
        String uuid = UUID.randomUUID().toString(true);
    
        Token token = new Token();
        token.setAuthentication(uuid);
        token.setValue(JSONObject.toJSONString(loginUser));
    
        tokenService.saveToken(token);
        
        log.info("用户:[{}]登录成功,ip地址:[{}],访问路径:[{}]",loginUser.getUsername(),request.getRemoteHost(),request.getRequestURL());
        response.setHeader("Authentication",uuid);
        ResponseUtil.ok(response,"登录成功");
    }
}

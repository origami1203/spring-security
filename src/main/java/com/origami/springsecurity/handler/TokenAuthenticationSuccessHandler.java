package com.origami.springsecurity.handler;

import cn.hutool.core.lang.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.origami.springsecurity.domain.LoginUser;
import com.origami.springsecurity.domain.entity.Token;
import com.origami.springsecurity.service.TokenService;
import com.origami.springsecurity.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author origami1203
 * @date 2021-5-3 17:31
 * @description TODO
 */
@Slf4j
@Component
public class TokenAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    
    public static final String REDIS_TOKEN_KEY = "token";
    
    @Value("${jwt.expireTime}")
    private Long expireTime;
    
    @Resource
    private HashOperations<String, String, String> hashOperations;
    
    @Resource
    private TokenService tokenService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
    
        String uuid = UUID.randomUUID().toString(true);
    
        Token token = new Token();
        token.setAuthentication(uuid);
        token.setAuthentication(new ObjectMapper().writeValueAsString(loginUser));
        token.setValue(uuid);
        token.setExpireTime(expireTime);
    
        hashOperations.put(REDIS_TOKEN_KEY,uuid,new ObjectMapper().writeValueAsString(loginUser));
        hashOperations.getOperations().expire(REDIS_TOKEN_KEY, expireTime, TimeUnit.SECONDS);
        tokenService.saveToken(token);
        
        log.info("??????:[{}]????????????,ip??????:[{}],????????????:[{}]",loginUser.getUsername(),request.getRemoteHost(),request.getRequestURL());
        response.setHeader("Authentication",uuid);
        ResponseUtil.ok(response,"????????????");
    }
}

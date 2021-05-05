package com.origami.springsecurity.service.impl;

import com.origami.springsecurity.domain.LoginUser;
import com.origami.springsecurity.domain.entity.Token;
import com.origami.springsecurity.service.TokenService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author origami1203
 * @date 2021-5-3 18:52
 * @description TODO
 */
@Service
@Primary
@ConditionalOnBean(name = "redisTemplate")
public class RedisTokenServiceImpl implements TokenService {
    @Override
    public void saveToken(Token token) {
    }
    
    @Override
    public void refresh(String token) {
    
    }
    
    @Override
    public LoginUser getLoginUserFromToken(String token) {
        return null;
    }
    
    @Override
    public boolean deleteToken(String token) {
        return false;
    }
    
    @Override
    public boolean checkToken(String token) {
        return false;
    }
}

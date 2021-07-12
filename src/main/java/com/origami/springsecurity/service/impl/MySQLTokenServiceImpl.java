package com.origami.springsecurity.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.origami.springsecurity.domain.LoginUser;
import com.origami.springsecurity.domain.entity.Token;
import com.origami.springsecurity.mapper.TokenMapper;
import com.origami.springsecurity.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author origami1203
 * @date 2021-5-3 18:51
 * @description TODO
 */
@Service
public class MySQLTokenServiceImpl implements TokenService {
    
    @Autowired
    private final TokenMapper tokenMapper;
    
    private Long expireSecond;
    
    public MySQLTokenServiceImpl(TokenMapper tokenMapper) {
        this.tokenMapper = tokenMapper;
    }
    
    @Override
    public void saveToken(Token token) {
    
        long loginTime = System.currentTimeMillis();
        long expireTime = loginTime + expireSecond * 1000;
        
        token.setLoginTime(loginTime);
        token.setExpireTime(expireTime);
    
        tokenMapper.insert(token);
    }
    
    @Override
    public void refresh(String token) {
        Optional.ofNullable(tokenMapper.selectOne(new QueryWrapper<Token>().eq("authentication", token)))
                .map(t -> t.setExpireTime(System.currentTimeMillis() + expireSecond * 1000))
                .map(tokenMapper::updateById);
    }
    
    @Override
    public LoginUser getLoginUserFromToken(String token) {
        return Optional.ofNullable(tokenMapper.selectOne(new QueryWrapper<Token>().eq("authentication",token)))
                .map(t-> JSONObject.parseObject(t.getValue()))
                .map(t-> JSONObject.toJavaObject(t,LoginUser.class))
                .orElse(null);
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

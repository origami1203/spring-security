package com.origami.springsecurity.service;

import com.origami.springsecurity.domain.LoginUser;
import com.origami.springsecurity.domain.entity.Token;

/**
 * @author origami1203
 * @date 2021-5-3 17:37
 * @description TODO
 */
public interface TokenService {
    
    void saveToken(Token token);
    
    void refresh(String token);
    
    LoginUser getLoginUserFromToken(String token);
    
    boolean deleteToken(String token);
    
    /**
     * 是否过期
     * @param token
     * @return
     */
    boolean checkToken(String token);
}

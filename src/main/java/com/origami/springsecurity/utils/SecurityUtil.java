package com.origami.springsecurity.utils;

import com.origami.springsecurity.domain.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author origami1203
 * @date 2021-5-4 10:26
 * @description TODO
 */
public class SecurityUtil {
    
    private SecurityUtil() {
    }
    
    public static LoginUser getCurrentLoginUser() {
        return (LoginUser) SecurityContextHolder.getContext().getAuthentication();
    }
    
    public static Long getCurrentLoginUserId() {
        return getCurrentLoginUser().getId();
    }
}

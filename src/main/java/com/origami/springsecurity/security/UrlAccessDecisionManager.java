package com.origami.springsecurity.security;

import cn.hutool.core.util.StrUtil;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author origami1203
 * @date 2021-5-4 13:07
 * @description 决策是否有权限访问
 */
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
            InsufficientAuthenticationException {
        List<String> needRoles = configAttributes.stream()
                                               .map(ConfigAttribute::getAttribute).collect(Collectors.toList());
    
        Collection<? extends GrantedAuthority> hasRoles = authentication.getAuthorities();
    
        for (String needRole : needRoles) {
            for (GrantedAuthority hasRole : hasRoles) {
                if (StrUtil.equalsIgnoreCase(needRole, hasRole.getAuthority())) {
                    return;
                }
            }
        }
    
        throw new AccessDeniedException("权限不足,无法访问");
    
    }
    
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}

package com.origami.springsecurity.security;

import com.origami.springsecurity.domain.entity.SysPermission;
import com.origami.springsecurity.domain.entity.SysRole;
import com.origami.springsecurity.service.SysPermissionService;
import com.origami.springsecurity.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author origami1203
 * @date 2021-5-4 12:55
 * @description 获取访问该url需要的权限
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private SysRoleService sysRoleService;
    
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    
    
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        
        if ("/login".equals(requestUrl) || requestUrl.contains("logout")) {
            return null;
        }
    
        // 获取所有菜单及权限
        List<SysPermission> permissions = sysPermissionService.list();
    
        
        for (SysPermission permission : permissions) {
            if (antPathMatcher.match(permission.getUrl(), requestUrl)) {
                String[] roles = sysRoleService.listAllRoleNamesByUrl(requestUrl)
                                                   .stream()
                                                   .map(SysRole::getRoleName)
                                                   .toArray(String[]::new);
    
                return SecurityConfig.createList(roles);
            }
        }
    
        return SecurityConfig.createList("ROLE_LOGIN");
    }
    
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
    
}

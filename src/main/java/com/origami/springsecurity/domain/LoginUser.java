package com.origami.springsecurity.domain;

import cn.hutool.core.util.StrUtil;
import com.origami.springsecurity.domain.entity.SysPermission;
import com.origami.springsecurity.domain.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author origami1203
 * @date 2021-5-3 9:41
 * @description TODO
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class LoginUser extends SysUser implements UserDetails, CredentialsContainer {
    
    private List<SysPermission> permissions;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.stream()
                          .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                          .collect(Collectors.toSet());
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return getEnabled();
    }
    
    // 擦除敏感信息
    @Override
    public void eraseCredentials() {
        this.setPassword(null);
    }
}

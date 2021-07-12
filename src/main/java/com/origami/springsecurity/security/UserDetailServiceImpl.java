package com.origami.springsecurity.security;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.origami.springsecurity.domain.LoginUser;
import com.origami.springsecurity.domain.entity.SysPermission;
import com.origami.springsecurity.domain.entity.SysRole;
import com.origami.springsecurity.domain.entity.SysUser;
import com.origami.springsecurity.service.SysPermissionService;
import com.origami.springsecurity.service.SysRoleService;
import com.origami.springsecurity.service.SysUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author origami1203
 * @date 2021-5-3 16:07
 * @description TODO
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    
    private final SysUserService sysUserService;
    
    private final SysPermissionService sysPermissionService;
    
    public UserDetailServiceImpl(SysUserService sysUserService, SysPermissionService sysPermissionService) {
        this.sysUserService = sysUserService;
        this.sysPermissionService = sysPermissionService;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser userByUsername =
                sysUserService.getOne(new QueryWrapper<SysUser>().eq("username", username), true);
    
        if (userByUsername == null) {
            throw new UsernameNotFoundException("用户名:["+username+"]不存在 ");
        }
        
        LoginUser loginUser = BeanUtil.toBean(userByUsername, LoginUser.class);
    
        List<SysPermission> permissions =
                sysPermissionService.getPermissionListByUserId(userByUsername.getId());
    
    
        return loginUser.setPermissions(permissions);
    }
}

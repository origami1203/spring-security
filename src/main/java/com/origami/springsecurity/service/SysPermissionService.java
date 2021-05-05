package com.origami.springsecurity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.origami.springsecurity.domain.entity.SysPermission;

import java.util.List;

/**
 * @author origami1203
 * @date 2021-5-2 22:42
 * @description TODO
 */
public interface SysPermissionService extends IService<SysPermission> {
    
    List<SysPermission> getPermissionListByUsername(String username);
    
    List<SysPermission> getPermissionListByUserId(Long userId);
}

package com.origami.springsecurity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.origami.springsecurity.domain.entity.SysRole;

import java.util.List;
import java.util.Set;

/**
 * @author origami1203
 * @date 2021-5-2 19:37
 * @description TODO
 */
public interface SysRoleService extends IService<SysRole> {
    List<SysRole> listAllRoleNamesByPermissionId(Long permissionId);
    
    List<SysRole> listAllRoleNamesByUserId(Long userId);
    
    List<SysRole> listAllRoleNamesByUrl(String url);
}

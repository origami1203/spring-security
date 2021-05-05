package com.origami.springsecurity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.origami.springsecurity.domain.entity.SysRole;
import com.origami.springsecurity.mapper.SysRoleMapper;
import com.origami.springsecurity.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author origami1203
 * @date 2021-5-2 19:42
 * @description TODO
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    
    @Autowired
    private SysRoleMapper sysRoleMapper;
    
    @Override
    public List<SysRole> listAllRoleNamesByPermissionId(Long permissionId) {
        return sysRoleMapper.selectAllRoleNamesByPermissionId(permissionId);
    }
    
    @Override
    public List<SysRole> listAllRoleNamesByUserId(Long userId) {
        return sysRoleMapper.selectAllRoleNamesByUserId(userId);
    }
    
    @Override
    public List<SysRole> listAllRoleNamesByUrl(String url) {
        return sysRoleMapper.selectAllRoleNamesByUrl(url);
    }
    
}

package com.origami.springsecurity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.origami.springsecurity.domain.entity.SysPermission;
import com.origami.springsecurity.mapper.SysPermissionMapper;
import com.origami.springsecurity.service.SysPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author origami1203
 * @date 2021-5-2 22:43
 * @description TODO
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements
        SysPermissionService {
    
    private final SysPermissionMapper sysPermissionMapper;
    
    public SysPermissionServiceImpl(SysPermissionMapper sysPermissionMapper) {
        this.sysPermissionMapper = sysPermissionMapper;
    }
    
    
    @Override
    public List<SysPermission> getPermissionListByUsername(String username) {
        return sysPermissionMapper.selectListByUsername(username);
    }
    
    @Override
    public List<SysPermission> getPermissionListByUserId(Long userId) {
        return sysPermissionMapper.selectListByUserId(userId);
    }
}

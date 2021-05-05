package com.origami.springsecurity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.origami.springsecurity.domain.entity.SysUser;
import com.origami.springsecurity.mapper.SysUserMapper;
import com.origami.springsecurity.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @author origami1203
 * @date 2021-5-2 19:40
 * @description TODO
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}

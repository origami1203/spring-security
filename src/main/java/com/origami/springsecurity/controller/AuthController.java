package com.origami.springsecurity.controller;

import com.origami.springsecurity.common.Resp;
import com.origami.springsecurity.domain.entity.SysUser;
import com.origami.springsecurity.dto.LoginDTO;
import com.origami.springsecurity.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author origami1203
 * @date 2021-5-4 21:03
 * @description TODO
 */

@RestController
public class AuthController {
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private SysUserService sysUserService;
    
    @PostMapping("/register")
    public Resp<Void> register(@RequestBody SysUser user) {
        user.setEnabled(true).setDeleted(false);
    
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        
        boolean save = sysUserService.save(user);
        if (save) {
            return Resp.ok();
        }
        return Resp.failed("注册失败");
    }
    
}

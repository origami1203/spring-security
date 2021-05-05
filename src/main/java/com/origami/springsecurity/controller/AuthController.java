package com.origami.springsecurity.controller;

import com.origami.springsecurity.common.Resp;
import com.origami.springsecurity.domain.entity.SysUser;
import com.origami.springsecurity.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
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
    public Resp<Object> register(SysUser user) {
        user.setEnabled((byte) 1).setDeleted((byte) 0);
    
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        
        boolean save = sysUserService.save(user);
        if (save) {
            return Resp.ok();
        }
        return Resp.failed("注册失败");
    
    }
    
}
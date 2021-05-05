package com.origami.springsecurity.controller;

import com.origami.springsecurity.domain.entity.SysUser;
import com.origami.springsecurity.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author origami1203
 * @date 2021-5-4 21:21
 * @description TODO
 */

@RestController
@RequestMapping("/user")
public class SysUserController {
    
    @Autowired
    private SysUserService sysUserService;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @PostMapping()
    public void addUser(SysUser user) {
        user.setEnabled((byte) 1).setPassword(passwordEncoder.encode(user.getPassword())).setDeleted((byte) 0);
        sysUserService.save(user);
    }
    
    
}

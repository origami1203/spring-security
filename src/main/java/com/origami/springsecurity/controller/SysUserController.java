package com.origami.springsecurity.controller;

import com.origami.springsecurity.common.Resp;
import com.origami.springsecurity.domain.entity.SysUser;
import com.origami.springsecurity.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author origami1203
 * @date 2021-5-4 21:21
 * @description TODO
 */

@RestController
@RequestMapping("/user")
public class SysUserController {
    
    @Resource
    private SysUserService sysUserService;
    
    @Resource
    private BCryptPasswordEncoder passwordEncoder;
    
    @PostMapping("")
    public void addUser(SysUser user) {
        user.setEnabled(true).setPassword(passwordEncoder.encode(user.getPassword())).setDeleted(false);
        sysUserService.save(user);
    }
    
    @DeleteMapping("")
    public Resp<Void> delete(Long id) {
        sysUserService.removeById(id);
        return Resp.ok();
    }
    
    
}

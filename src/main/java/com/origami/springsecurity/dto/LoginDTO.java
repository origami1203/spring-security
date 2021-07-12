package com.origami.springsecurity.dto;

import java.io.Serializable;

/**
 * @author origami1203
 * @date 2021-7-10 21:01
 * @description TODO
 */
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = -2568410603880351666L;
    
    private String username;
    
    private String password;
}

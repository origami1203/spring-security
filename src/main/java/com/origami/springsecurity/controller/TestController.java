package com.origami.springsecurity.controller;

import com.origami.springsecurity.common.Resp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author origami1203
 * @date 2021-7-11 11:53
 * @description TODO
 */
@RestController
public class TestController {
    
    @GetMapping("/index")
    public Resp<String> index() {
        return Resp.ok("hello");
    }
}

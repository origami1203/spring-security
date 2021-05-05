package com.origami.springsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author origami
 */
@SpringBootApplication
@MapperScan(basePackages = "com.origami.springsecurity.mapper")
public class SpringSecurityApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }
    
}

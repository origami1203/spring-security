package com.origami.springsecurity.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.origami.springsecurity.common.Code;
import com.origami.springsecurity.common.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author origami1203
 * @date 2021-5-4 8:58
 * @description TODO
 */

@Slf4j
public class ResponseUtil {
    
    private ResponseUtil() {
    }
    
    public static void response(HttpServletResponse resp,int status,int code, String message) {
        resp.setContentType("application/json; charset=utf-8");
        resp.setStatus(status);
        try (PrintWriter out = resp.getWriter()) {
            ObjectMapper mapper = new ObjectMapper();
            String data = mapper.writeValueAsString(Resp.resp(code, message));
            out.print(data);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
    
    public static void unauthorized(HttpServletResponse resp) {
        response(resp, HttpStatus.UNAUTHORIZED.value(), Code.NOT_LOGIN.value(), Code.NOT_LOGIN.desc());
    }
    
    public static void unauthorized(HttpServletResponse resp,String msg) {
        response(resp,HttpStatus.UNAUTHORIZED.value(),Code.NOT_LOGIN.value(), msg);
    }
    
    public static void ok(HttpServletResponse response) {
        response(response,HttpStatus.OK.value(),Code.SUCCESS.value(), HttpStatus.OK.toString());
    }
    
    public static void ok(HttpServletResponse response,String msg) {
        response(response,HttpStatus.OK.value(),Code.SUCCESS.value(), msg);
    }
    
    public static void accessDeny(HttpServletResponse resp) {
        response(resp, HttpStatus.OK.value(), Code.ACCESS_DENY.value(), Code.ACCESS_DENY.desc());
    }
}

package com.origami.springsecurity.filter;

import cn.hutool.core.util.StrUtil;
import com.origami.springsecurity.domain.LoginUser;
import com.origami.springsecurity.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author origami1203
 * @date 2021-5-3 9:36
 * @description 自定义token过滤器
 */
public class TokenFilter extends OncePerRequestFilter {
    
    private final String TOKEN_HEADER = "Authentication";
    
    private TokenService tokenService;
    
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(TOKEN_HEADER);
    
        if (StrUtil.isBlank(token)) {
            filterChain.doFilter(request, response);
            return;
        }
    
        LoginUser loginUser = tokenService.getLoginUserFromToken(token);
    
        if (loginUser == null) {
            filterChain.doFilter(request, response);
            return;
        }
    
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(loginUser, null,
                                                        loginUser.getAuthorities());
    
        authentication.setDetails(new WebAuthenticationDetails(request));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    
    }
}

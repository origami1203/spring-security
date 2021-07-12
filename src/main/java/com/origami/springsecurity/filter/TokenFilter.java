package com.origami.springsecurity.filter;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.origami.springsecurity.domain.LoginUser;
import com.origami.springsecurity.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private HashOperations<String, String, String> hashOperations;
    @Autowired
    private TokenService tokenService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(TOKEN_HEADER);


        if (StrUtil.isBlank(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String json = hashOperations.get("token", token);
        final ObjectMapper objectMapper = new ObjectMapper();
        final LoginUser loginUser = objectMapper.readValue(json, LoginUser.class);

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

package com.origami.springsecurity.config;

import com.origami.springsecurity.filter.TokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author origami1203
 * @date 2021-5-3 9:45
 * @description TODO
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    private final UserDetailsService userDetailsService;
    private final AccessDeniedHandler accessDeniedHandler;
    private final LogoutSuccessHandler logoutSuccessHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;
    private final AccessDecisionManager accessDecisionManager;
    
    public WebSecurityConfig(UserDetailsService userDetailsService,
                                AccessDeniedHandler accessDeniedHandler,
                                LogoutSuccessHandler logoutSuccessHandler,
                                AuthenticationEntryPoint authenticationEntryPoint,
                                AuthenticationSuccessHandler authenticationSuccessHandler,
                                AuthenticationFailureHandler authenticationFailureHandler,
                                FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource,
                                AccessDecisionManager accessDecisionManager) {
        this.userDetailsService = userDetailsService;
        this.accessDeniedHandler = accessDeniedHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.filterInvocationSecurityMetadataSource = filterInvocationSecurityMetadataSource;
        this.accessDecisionManager = accessDecisionManager;
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).disable()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/login", "/logout", "/register")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .usernameParameter("username")
            .passwordParameter("password")
            .successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailureHandler)
            .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                @Override
                public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                    object.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
                    object.setAccessDecisionManager(accessDecisionManager);
                    return object;
                }
            })
            .and()
            .logout()
            .logoutSuccessHandler(logoutSuccessHandler)
            .and()
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(authenticationEntryPoint)
            .and()
            .addFilterBefore(new TokenFilter(), UsernamePasswordAuthenticationFilter.class);
        
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
    
    @Bean
    public FilterRegistrationBean<TokenFilter> filterRegistrationBean() {
        FilterRegistrationBean<TokenFilter> filter = new FilterRegistrationBean<>();
        filter.setFilter(tokenFilter());
        filter.setOrder(1);
        
        return filter;
    }
    
    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }
}

package com.demo.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

//    @Autowired
//    private AuthenticationSuccessHandler successHandler;
//
//    @Autowired
//    private AuthenticationFailureHandler failureHandler;
//
//    @Autowired
//    private LogoutHandler logoutHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //确保对我们应用程序的任何请求都需要对用户进行身份验证
        httpSecurity.authorizeRequests(authorize ->
                                authorize.antMatchers("/oauth2/**", "/login/**", "/logout/**")
                .permitAll()
                // 除了以上匹配的请求，其他都要认证
                .anyRequest()
                .authenticated()
                // 允许表单登录
                )
                //允许用户使用基于表单的登录进行身份验证
                .formLogin(Customizer.withDefaults())
                .csrf().disable()
                //允许用户使用 HTTP Basic 身份验证进行身份验证
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }


}

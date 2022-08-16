package com.demo.gateway.config;

import com.demo.gateway.handler.SimpleAccessDeniedHandler;
import com.demo.gateway.handler.SimpleAuthenticationEntryPoint;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.io.InputStream;
import java.security.cert.CertificateFactory;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @Description: TODO
 * @Author: DaiFu
 * Date: 2022/8/12
 */
@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启鉴权服务
public class ResourceServerConfiguration {
    @Bean
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity httpSecurity) throws Exception {
        // 所有请求都进行拦截
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        // 关闭session
        httpSecurity.sessionManagement().disable();
        // 配置资源服务器的无权限，无认证拦截器等 以及JWT验证
        httpSecurity.oauth2ResourceServer()
                .accessDeniedHandler(new SimpleAccessDeniedHandler())
                .authenticationEntryPoint(new SimpleAuthenticationEntryPoint())
                .jwt();
        return httpSecurity.build();
    }

}


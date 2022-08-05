package com.demo.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;

@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsServiceImpl;

    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * 配置客户端
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client_id")
                .resourceIds("rid")
                .secret(passwordEncoder.encode("secret"))
                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                .accessTokenValiditySeconds(3600)
                .scopes("all");

    }

    /**
     * 配置授权端点
     * 密码模式必须配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //密码模式需要配置(可能是因为直接根据用户名密码登录)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsServiceImpl);
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer serverSecurityConfigurer) throws Exception{
        serverSecurityConfigurer.allowFormAuthenticationForClients();
        serverSecurityConfigurer.checkTokenAccess("permitAll()");
        serverSecurityConfigurer.tokenKeyAccess("permitAll()");
        serverSecurityConfigurer.passwordEncoder(passwordEncoder);
    }
}
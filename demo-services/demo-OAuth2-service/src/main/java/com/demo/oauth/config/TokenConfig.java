package com.demo.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
public class TokenConfig {

    @Bean
    public TokenStore tokenStore(){
        // 这里还可以配置很多token存储的地方
        // JdbcTokenStore、JwtTokenStore、RedisTokenStore...
        // 我这里就直接放在内存中了，不过需要注意的是，当上线用户量激增的时候，放在内存中可能会出大事情
        return new InMemoryTokenStore();
    }
}

package com.demo.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description: TODO
 * @Author: DaiFu
 * Date: 2022/8/5
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class OAuth2ServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(OAuth2ServiceApp.class,args);
    }
}

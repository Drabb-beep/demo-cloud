package com.demo.vite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 * @Description: TODO
 * @Author: DaiFu
 * Date: 2022/8/4
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication

@MapperScan("com.demo.vite.mapper")
public class ViteServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ViteServiceApp.class,args);
    }
}

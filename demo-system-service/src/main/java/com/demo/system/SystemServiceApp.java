package com.demo.system;

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
public class SystemServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(SystemServiceApp.class,args);
    }
}

package com.demo.file;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@MapperScan("com.demo.file.mapper")
public class DemoFileServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoFileServiceApplication.class, args);
    }

}

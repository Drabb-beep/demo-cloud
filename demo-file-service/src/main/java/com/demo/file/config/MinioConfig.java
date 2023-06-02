package com.demo.file.config;

import io.minio.MinioClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author
 * @version 1.0
 * @date: 2022/5/20 10:30
 * @Description minio配置
 */
@Configuration
@Component
@ConfigurationProperties(prefix = "minio")
@Getter
@Setter
public class MinioConfig {
    private String endpoint;
    private String accessKey;
    private String secretKey;

    private String  bucketName;

    @Bean
    public MinioClient getMinioClient(){
        MinioClient minioClient = MinioClient.builder().endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
        return minioClient;
    }
//
//    @Bean(name = "multipartResolver")
//    public MultipartResolver multipartResolver(){
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        resolver.setDefaultEncoding("UTF-8");
//        //resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
//        resolver.setResolveLazily(true);
//        resolver.setMaxInMemorySize(40960);
//        //上传文件大小 50M 50*1024*1024
//        resolver.setMaxUploadSize(50*1024*1024);
//        return resolver;
//    }
}
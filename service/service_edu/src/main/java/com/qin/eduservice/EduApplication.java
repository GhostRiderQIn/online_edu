package com.qin.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: online_edu_parent
 * @description: 启动类
 * @author: qinda
 * @create: 2020-07-05 23:15
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.qin"})
@EnableDiscoveryClient // nacos注册
@EnableFeignClients // Feign服务发现
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}

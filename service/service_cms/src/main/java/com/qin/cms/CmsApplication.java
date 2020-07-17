package com.qin.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-16 12:34
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.qin"})
@MapperScan("com.qin.cms.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}

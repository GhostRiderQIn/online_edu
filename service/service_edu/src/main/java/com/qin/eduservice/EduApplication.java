package com.qin.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: online_edu_parent
 * @description: 启动类
 * @author: qinda
 * @create: 2020-07-05 23:15
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.qin"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}

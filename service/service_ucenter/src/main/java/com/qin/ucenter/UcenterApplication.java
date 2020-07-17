package com.qin.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-17 12:04
 **/
@ComponentScan("com.qin")
@SpringBootApplication
@MapperScan("com.qin.ucenter.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}

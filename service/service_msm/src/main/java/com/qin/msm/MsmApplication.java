package com.qin.msm;

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
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源自动配置
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class,args);
    }
}

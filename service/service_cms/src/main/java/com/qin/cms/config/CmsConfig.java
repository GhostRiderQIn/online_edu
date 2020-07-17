package com.qin.cms.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-16 13:51
 **/
@Configuration
public class CmsConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    @Bean
    public ISqlInjector iSqlInjector(){
        return new LogicSqlInjector();
    }

}

package com.sunl19ht.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@EnableWebMvc //给handlerAdapter配置json转化器 添加RequestMappingHandlerMapping 添加RequestMappingHandlerAdapter 添加Json处理器
@Configuration
@ComponentScan("com.sunl19ht.header")
public class MvcConfig {
    //添加了 EnableWebMvc 注解的类中，RequestMappingHandlerMapping和RequestMappingHandlerAdapter会自动注册可以省略
//    @Bean
//    public RequestMappingHandlerMapping handlerMapping() {
//        return new RequestMappingHandlerMapping();
//    }
//
//    @Bean
//    public RequestMappingHandlerAdapter handlerAdapter() {
//        return new RequestMappingHandlerAdapter();
//    }
}

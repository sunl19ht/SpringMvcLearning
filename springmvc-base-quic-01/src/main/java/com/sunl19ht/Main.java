package com.sunl19ht;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;

public class Main implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //只要web程序一启动就会执行此方法
        System.out.println("web程序启动了");
        //IoC容器初始化
    }
}

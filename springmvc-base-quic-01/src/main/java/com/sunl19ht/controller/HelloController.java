package com.sunl19ht.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    //handler -> springmvc/hello return "hello springmvc!"

    @RequestMapping("spring/mvc/hello") //对外访问的地址 到handlerMapping注册的注解
    @ResponseBody //直接返回给前端 不要找视图解析器
    public String hello() {
        //返回给前端
        return "hello springmvc!";
    }
}

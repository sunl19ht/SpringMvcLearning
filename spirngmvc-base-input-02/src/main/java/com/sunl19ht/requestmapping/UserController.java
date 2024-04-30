package com.sunl19ht.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")    //指定UserController开头地址
public class UserController {

    //handler -> handlerMapping 每一个方法就是一个handler 将handler注册到handlerMapping并且指定访问地址

    /**
     * @WebServlet("/必须斜杠开头")
     * @RequestMapping("非必须斜杠开头")
     * 1. 精准地址[一个/多个] /user/login, {"地址1", "地址2"}
     * 2. 支持模糊地址 [*任意一层字符串 /user/*, **任意多层字符串 /user/**]
     * 3. 类上添加@RequesstMapping 提取通用访问地址 见第7行
     * 4. 请求方式指定
     *    客户端 -> http(get | post | put | delete) -> ds -> handler
     *    默认只要地址正确任何请求方式都可以访问
     *    指定请求方式：method = RequestMethod.GET
     *        @RequestMapping(value = "/login", method = RequestMethod.GET)
     *    不符合请求方式：405异常!
     * 5. 注解进阶(只能使用在方法上)
     *    @GetMapping
     *    @PostMapping
     *    @PutMapping
     *    @DeleteMapping
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)//类 接口 方法 作用：将handler注册到handlerMapping
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping
    public String index() {
        return "index";
    }
}

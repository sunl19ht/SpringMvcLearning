package com.sunl19ht.path;

import jakarta.ws.rs.Path;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("path")
@ResponseBody
public class PathController {
    // path/账号/密码
    @RequestMapping("{account}/{password}") //动态路径
    public String login(@PathVariable String account, @PathVariable String password) { //接收动态参数
        System.out.println("account:" + account + " password:" + password);
        return null;
    }
}

package com.sunl19ht.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    //空指针异常
    @GetMapping("data")
    public String data() {
        String name = null;
        name.toString();
        return "ok";
    }

    //算数异常
    @GetMapping("data1")
    public String data1() {
        int i = 1 / 0;
        return "ok";
    }
}

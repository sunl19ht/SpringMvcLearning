package com.sunl19ht.controller;

import com.sunl19ht.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("json")
public class JsonController {

    @GetMapping("data")
    @ResponseBody   //数据直接返回 不返回视图
    public User data() {
        User user = new User();
        user.setName("to dogs");
        user.setAge(18);
        return user; //user -> handlerAdapter -> json @ResponseBody -> json直接返回
    }

    @GetMapping("data1")
    @ResponseBody
    public List<User> data1() {
        User user = new User();
        user.setName("to dogs");
        user.setAge(18);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        return users;
    }
}

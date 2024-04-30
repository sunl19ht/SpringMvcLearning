package com.sunl19ht.param;

import com.sunl19ht.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("param")
public class ParamController {
    //直接接收 /param/data?name=zhangsan&age=19
    //形参列表填写对应名称的参数即可
    //1. 名称相同
    //2. 可以不传递 不报错
    @RequestMapping("data")
    @ResponseBody
    public String data(String name, int age) {
        System.out.println("name: " + name);
        System.out.println("name: " + name + " age: " + age);
        return "name: " + name + " age: " + age;
    }

    //注解指定 /param/data1?account=root&page=1
    // account 必须传递
    // page 可以不传递 默认值1
    // @RequestParam(value = "指定参数名 如果和请求参数名一样可以忽略",required = "是否必须传递true/false", defaultValue = "默认值")
    @GetMapping("data1")
    @ResponseBody
    public String data1(@RequestParam(value = "account") String account, @RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
        System.out.println("account: " + account + " page: " + page);
        return "account: " + account + " page: " + page;
    }
    //特殊值
    //一名多值 key=1&key=2 直接使用集合接收
    //param/data2/hbs=吃饭&hbs=睡觉&hbs=打豆豆
    //集合必须加@RequestParam 如果不加会将对应的字符串赋值给集合 导致类型错误
    @GetMapping("data2")
    @ResponseBody
    public String data2(@RequestParam("hbs") List<String>[] hbs) {
        for (List<String> hb : hbs) {
            System.out.println(hb);
        }
        return "hbs: " + hbs;
    }

    //使用实体对象接收值
    //param/data3?name=zhangsan&age=19

    /**
     * user的属性名必须要等于参数名
     * 默认值直接在属性上设置 private String name = "张三";
     * @param user
     * @return
     */
    @RequestMapping("data3")
    @ResponseBody
    public String data3(User user) {
        System.out.println(user);
        return user.toString();
    }
}

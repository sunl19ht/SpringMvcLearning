package com.sunl19ht.json;

import com.sunl19ht.pojo.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("json")
@Controller
@ResponseBody
public class JsonController {
    //data -> 请求体 post{name, age, gender}
    /**
     * 报错415
     * 原因：Java原生api不支持接收json数据
     * 解决：1.导入Json处理的依赖 2. handlerAdapter配置json转化器
     * @param person
     * @return
     */
    @PostMapping("data")
    public String data(@RequestBody Person person) {
        System.out.println(person.toString());
        return person.toString();
    }
}

package com.sunl19ht.share;

import jakarta.enterprise.inject.Model;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("share")
@ResponseBody
public class ShareController {
    //原生API
    @Autowired
    private ServletContext servletContext;

    //原生api
    public void data(HttpServletRequest request, HttpSession session) {

    }
}

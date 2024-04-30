package com.sunl19ht.api;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

public class ApiController {

    @Autowired
    private ServletContext servletContext;
    public void data(HttpServletResponse response,
                     HttpServletRequest request,
                     HttpSession session) {
        //使用原生对象就可以获取
        ServletContext servletContext = request.getServletContext();
        ServletContext servletContext1 = session.getServletContext();
    }
}

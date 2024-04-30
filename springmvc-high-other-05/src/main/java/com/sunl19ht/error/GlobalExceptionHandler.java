package com.sunl19ht.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
// @ControllerAdvice //全局异常发生会走此类下的handler方法 可以返回逻辑视图 可以转发和重定向
@RestControllerAdvice   //ResponseBody + ControllerAdvice 直接返回json字符串
public class GlobalExceptionHandler {
    //算数异常处理
    @ExceptionHandler(ArithmeticException.class) //捕获异常
    public Object ArithmeticExceptionHandler(ArithmeticException e) {   //接收异常
        //自定义处理
        System.out.println(e.getMessage());
        return "by zero";
    }

    @ExceptionHandler(NullPointerException.class)
    public Object NullPointerExceptionHandler(NullPointerException e) {
        //自定义处理
        System.out.println(e.getMessage());
        return "nullPointerException";
    }

    @ExceptionHandler(Exception.class)
    public Object ExceptionHandler(Exception e) {
        //自定义处理
        String message = e.getMessage();
        System.out.println(message);
        return message;
    }
}

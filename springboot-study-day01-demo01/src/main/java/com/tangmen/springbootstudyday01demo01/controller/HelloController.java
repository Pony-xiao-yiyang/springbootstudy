package com.tangmen.springbootstudyday01demo01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author:
 * @time: 2020/9/24 15:45
 */
@Controller
@RequestMapping("/xiao")
public class HelloController {

    //接口：http://localhost:8080/hello
    @RequestMapping("/liyi")
    @ResponseBody
    public String hello(){

        //调用业务，接受前端参数
        return "hello world";
    }

    @RequestMapping("/mama")
    @ResponseBody
    public String haha(){

        //调用业务，接受前端参数
        return "hello,springboot";
    }
}

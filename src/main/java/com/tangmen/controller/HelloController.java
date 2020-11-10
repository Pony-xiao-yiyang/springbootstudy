package com.tangmen.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author:
 * @time: 2020/11/10 7:29
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/world")
    @ResponseBody
    public String hello(){
        return "hello,world";
    }
}

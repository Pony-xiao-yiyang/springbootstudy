package com.tangmen.springbootday05demo01.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author:
 * @time: 2020/11/9 22:49
 */
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/world")
    public String hello(){
        System.out.println("此代码1运行成功");
        return "hello.world";
    }
}

package com.tangmen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author:
 * @time: 2020/11/10 22:02
 */
@Controller
@RequestMapping("/hellojava")
public class HelloController {
@RequestMapping("/world")
@ResponseBody
    public String hello(){
    System.out.println("输入成功/hellojava/world");
    return "hello,java and world";
}
}

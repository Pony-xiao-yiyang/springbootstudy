package com.tangmen.springbootstudyday17swaggerui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author:
 * @time: 2020/12/2 22:48
 */
@Controller
public class HelloController {

    @RequestMapping("/world")
    public String hello(Model model){
        model.addAttribute("msg","hello,world");
        return "world";
    }
}

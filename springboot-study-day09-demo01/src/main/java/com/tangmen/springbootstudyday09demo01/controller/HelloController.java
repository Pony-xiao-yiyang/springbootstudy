package com.tangmen.springbootstudyday09demo01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

/**
 * @description:
 * @author:
 * @time: 2020/11/16 15:25
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String HelloJava(Model model){
        model.addAttribute("msg","<h1>hello,springboot for java</h1>");
        model.addAttribute("users", Arrays.asList("波多野结衣","苍老师"));
        return "test";
    }
}

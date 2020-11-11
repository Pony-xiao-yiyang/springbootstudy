package com.tangmen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author:
 * @time: 2020/11/12 0:48
 */
@Controller
@RequestMapping("/hello")
public class IndexController {

    @RequestMapping("/world")
    public  String index(){
        return "world";
    }
}

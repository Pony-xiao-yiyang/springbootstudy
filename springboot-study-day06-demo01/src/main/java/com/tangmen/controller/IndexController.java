package com.tangmen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author:
 * @time: 2020/11/10 23:23
 */

/**
 * 在templates目录下的所有页面，只能通过controller来跳转
 */
@Controller
@RequestMapping("/controller")
public class IndexController {

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "index";
    }
}

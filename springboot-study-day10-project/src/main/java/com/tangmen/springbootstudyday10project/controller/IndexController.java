package com.tangmen.springbootstudyday10project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author:
 * @time: 2020/11/19 22:34
 */
@RestController
public class IndexController {

    @RequestMapping({"/", "/index.jsp"})
    @ResponseBody
    public String index(){
        return "index";
    }
}

package com.tangmen.springbootstudyday09demo01.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

/**
 * @description:
 * @author:
 * @time: 2020/11/16 15:58
 */
//如果我们要拓展springmvc，官方建议我们这样去做
    @Configuration
    //这导入一个类:DelegatingWebMvcConfiguration:从容器中获取webmvcconfig;
    @EnableWebMvc
public class MyConfig implements WebMvcConfigurer {
//        视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/adolf").setViewName("test");
    }
}

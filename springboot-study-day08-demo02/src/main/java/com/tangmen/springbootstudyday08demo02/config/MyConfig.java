package com.tangmen.springbootstudyday08demo02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

/**
 * @description:
 * @author:
 * @time: 2020/11/16 15:58
 */
//如果你想diy一些定制化的功能，只要写这个组件，然后将他交给springboot，springboot会帮我们自动装配！

//扩展 springmvc dispatchservlet
    @Configuration
public class MyConfig implements WebMvcConfigurer {
    //ViewResolver 实现了视图解析器接口的类，我们可以把它看做视图解析器
    @Bean
    public ViewResolver myMyViewResolver(){
        return new MyViewResoiver();
    }
    //自定义一个自己的视图解析器
    public static  class MyViewResoiver implements ViewResolver{

        @Override
        public View resolveViewName(String s, Locale locale) throws Exception {
            return null;
        }
    }
}

package com.tangmen.springbootstudyday11project.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author:
 * @time: 2020/11/24 21:25
 */
@Configuration
public class DruidController {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource(){
        return new DruidDataSource();
    }
    //后台监控:web.xml
    @Bean
    public ServletRegistrationBean a(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //后台需要有人登陆，账号密码配置
        final HashMap<String,String> initParameters = new HashMap<>();
        //增加数据
        //登陆key 是固定loginUsername loginPassword
        initParameters.put("loginUsername","admin");
        initParameters.put("loginPassword","123456");

        //允许谁可以访问
        initParameters.put("allow","localhost");

        //禁止访问initParameters.put("adolf","192.168.11.123");

        bean.setInitParameters(initParameters);//设置初始化
        return bean;
    }
    //filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
         FilterRegistrationBean bean = new FilterRegistrationBean();
         bean.setFilter(new WebStatFilter());

         //可以过滤那些请求？
         Map<String, String> initParameters = new HashMap<>();
         //这些东西不进行统计
         initParameters.put("exclusions","*.js,*.css,/druid/*");
         return bean;
    }
}

package com.tangmen.springbootstudyday14security.comfig;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @description:
 * @author:
 * @time: 2020/11/26 21:09
 */
//AOP:拦截器
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //链式编程
    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页可以访问，但是功能页只能对应的有权限的人才能访问
        //请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");
        //目前没有权限默认回到登录页面,需要开启登录页面
        http.formLogin();
//        注销，开启注销功能,跳到首页
//        防止网站工具：get , post
//        防CSRF攻击：
//        跨站请求伪造（英語：Cross-site request forgery），也被称为one-click attack 或者session riding，通常缩写为CSRF 或者XSRF， 是一种挟制用户在当前已登录的Web应用程序上执行非本意的操作的攻击方法。
        http.csrf().disable();//关闭csrf功能
        http.logout().logoutSuccessUrl( "/");
        //开启记住功能,默认保存14天
        http.rememberMe();

    }
    //认证，springboot2.4.x可以正常验证
    //密码编码：PasswordEncoder
    //在spring security 5.0+ 新增了很多的加密方法
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //通过jdbc去认证
//        auth.jdbcAuthentication()
        //通过内存去认证
        //这些数据正常应该从数据库中去读
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("adolf").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("12345")).roles("vip1","vip2","vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("1234")).roles("vip1");
    }
}

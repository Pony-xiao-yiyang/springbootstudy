package com.tangmen.springbootstudyday16shiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @description:
 * @author:
 * @time: 2020/12/1 16:12
 */
@Configuration
public class ShiroConfig {

    //shiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
//添加shiro的内置过滤器
        /*
            anon:无需认证就可以访问
            authc:必须认证了才能访问
            user:必须拥有 记住我 功能才能实现
            perms:拥有某个资源的权限才能访问
            role:拥有某个角色权限才能访问
         */
         HashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

//         filterChainDefinitionMap.put("/user/add","authc");
//         filterChainDefinitionMap.put("/user/update","authc");
        filterChainDefinitionMap.put("/user/*","authc");
         bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//设置登录的请求
         bean.setLoginUrl("/toLogin");
        return bean;
    }

    //DefaultWebSecurityManager:2
    @Bean(name = "securityManager")
public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
     DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
     //关闭UserRealm
    securityManager.setRealm(userRealm);
    return securityManager;
}
    //创建realm 对象,需要自定义类；1
//    @Bean(name = "userRealm")
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }
}

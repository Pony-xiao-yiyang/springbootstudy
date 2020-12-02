package com.tangmen.springbootstudyday17swaggerui.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @description:
 * @author:
 * @time: 2020/12/2 22:56
 */
@Configuration //配置类
@EnableSwagger2// 开启Swagger2的自动配置
public class SwaggerConfig {
    @Bean //配置docket以配置Swagger具体参数
    //Environment environment 获取环境
    public Docket docket(Environment environment) {

        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev","test");
        //通过environment,acceptsProfiles判断是否处在自己设定的环境中
        boolean acceptsProfiles = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //：是否启用swagger,如果为false，无法启动
                .enable(acceptsProfiles)
                .select()
                // 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                // basePackage指定要扫描的包
                //any():扫描全部
                //none():不扫描
                //withClassAnnotation:扫描类的注解，参数是一个注解的反射对象
//                withMethodAnnotation:扫描方法上的注解
                .apis(RequestHandlerSelectors
//                        .any();
//                        .none();
//                        .withClassAnnotation(GetMapping.class);
//                        .withMethodAnnotation(GetMapping.class);
                        .basePackage("com.tangmen.springbootstudyday17swaggerui.controller"))
//                 配置如何通过path过滤,即这里只扫描请求以/tangmen开头的接口
                .paths(PathSelectors.ant("/tangmen/**"))
                .build();

    }

    //配置文档信息
    private ApiInfo apiInfo() {
        //作者信息:联系人名字，地址，email
        Contact contact = new Contact("adlof.musk", "https://github.com/Pony-xiao-yiyang", "ponyxiaoyiyang@gmail.com");
        return new ApiInfo(
                "深田えいみのセックスコース", // 标题
                "学ぶ深田えいみ先生のオンラインコース", // 描述
                "v1.0", // 版本
                "https://cn.pornhub.com/", // 组织链接(Swagger上显示为服务条款）
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }
}

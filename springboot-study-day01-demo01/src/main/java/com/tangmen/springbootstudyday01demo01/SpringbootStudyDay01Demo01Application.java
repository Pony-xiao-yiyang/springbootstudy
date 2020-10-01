package com.tangmen.springbootstudyday01demo01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 本身就是spring的一个组件
 */
//程序的主入口
//@SpringBootApplication：标注这个类是一个springboot的应用
@SpringBootApplication
public class SpringbootStudyDay01Demo01Application {

	public static void main(String[] args) {
		//将springboot应用启动
		//springApplication类
		//run方法
		SpringApplication.run(SpringbootStudyDay01Demo01Application.class, args);
	}

}

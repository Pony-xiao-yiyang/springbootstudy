package com.tangmen.springbootstudyday10project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootStudyDay10ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootStudyDay10ProjectApplication.class, args);
	}

}

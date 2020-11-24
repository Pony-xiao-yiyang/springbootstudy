package com.tangmen.springbootstudyday11project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

@SpringBootTest
class SpringbootStudyDay11ProjectApplicationTests {

	@Autowired
	DataSource dataSource;

	@Test
	void contextLoads() throws SQLException {
		//查看一下默认的数据库：class com.zaxxer.hikari.HikariDataSource
		System.out.println(dataSource.getClass());

		//获取数据库的连接
		Connection connection = dataSource.getConnection();
		System.out.println(connection);

		//xxxx Template :springboot已配置好模板的bean：拿来即用:CRUD

		//关闭
		connection.close();


	}

}

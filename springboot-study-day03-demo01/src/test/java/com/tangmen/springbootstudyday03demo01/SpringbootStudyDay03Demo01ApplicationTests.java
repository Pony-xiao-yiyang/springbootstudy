package com.tangmen.springbootstudyday03demo01;

import com.tangmen.springbootstudyday03demo01.pojo.Dog;
import com.tangmen.springbootstudyday03demo01.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootStudyDay03Demo01ApplicationTests {
@Autowired

private Dog dog;
	@Test
	void contextLoads() {
		System.out.println(dog);
	}

}

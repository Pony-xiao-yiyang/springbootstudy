package com.tangmen.springbootstudyday03demo01.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author:
 * @time: 2020/10/2 20:37
 */
@Data
@Component

public class Person {
    @Value("小锦")
    private String name;
    @Value("23")
    private Integer age;
    private Dog dog;

}

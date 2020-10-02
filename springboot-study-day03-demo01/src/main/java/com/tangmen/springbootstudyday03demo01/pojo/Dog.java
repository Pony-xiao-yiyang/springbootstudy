package com.tangmen.springbootstudyday03demo01.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author:
 * @time: 2020/10/2 20:35
 */
@Data
@Component

public class Dog {

    private String name;

    private Integer age;

    private Integer number;

}

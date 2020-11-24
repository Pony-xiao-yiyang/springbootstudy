package com.tangmen.springbootstudyday10project.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description:
 * @author:
 * @time: 2020/11/19 21:21
 */
@Data
@NoArgsConstructor
public class Employee {
    private Integer EmploeeId;
    private String LastName;
    private String email;
    //1：男     2：女
    private Integer gender;
    private Department department;
    private Date birth;

    public Employee(Integer emploeeId, String lastName, String email, Integer gender, Department department) {
        EmploeeId = emploeeId;
        LastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.birth = new Date();
    }
}

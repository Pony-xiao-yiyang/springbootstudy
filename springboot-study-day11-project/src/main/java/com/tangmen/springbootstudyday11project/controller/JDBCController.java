package com.tangmen.springbootstudyday11project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author:
 * @time: 2020/11/24 17:37
 */
@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //查询数据所有信息
    //没有实体类，数据库中的对象，怎么获取？ Map
    @GetMapping("/userList")
    public List<Map<String,Object>> userList(){
        String sql = "select * from cms_user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    @GetMapping("/addUser")
    public String addUser(){
        String sql = "insert into user(id,name,pwd) values(4,'小明',123456)";
        jdbcTemplate.update(sql);
        return "update-ok";
    }

    @GetMapping("/updateUser")
    public String updateUser(@PathVariable("id") int id){
        String sql = "update user set name = ?, pwd = ? where id = "+id;
        //封装
        Object[]objects=new Object[2];
        objects[0]="小明2";
        objects[1]="xzzzzz";
        jdbcTemplate.update(sql,objects);
        return "update-ok";
    }
    @GetMapping("/selectUser")
    public String selectUser(@PathVariable("id") int id){
        String sql = "select * from user where id = ?"+id;
        jdbcTemplate.update(sql,id);
        return "update-ok";
    }

    @GetMapping("/deleteUser")
    public String eleteUser(@PathVariable("id") int id){
        String sql = "delete from user where id = ?"+id;
        jdbcTemplate.update(sql,id);
        return "deleteUser-ok";
    }

}

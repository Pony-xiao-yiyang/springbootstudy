package com.tangmen.springbootstudyday12myabatis.mapper;

import com.tangmen.springbootstudyday12myabatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author:
 * @time: 2020/11/25 16:47
 */
//表示该注释为一个mybatis的mapper类：Dao
@Mapper
@Repository
public interface UserMapper {

    List<User>queryUserList();

    User queryUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);

}

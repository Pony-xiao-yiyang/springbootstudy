package com.tangmen.springbootstudyday10project.mapper;

import com.tangmen.springbootstudyday10project.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author:
 * @time: 2020/11/19 21:24
 */
@Repository
public class DepartmentMapper {
    //模拟数据库数据
    private static Map<Integer, Department> departments=null;

    static {
        //创建一个部门表
        departments = new HashMap<Integer, Department>();

        departments.put(101,new Department(101,"教学部"));
        departments.put(102,new Department(102,"研发部"));
        departments.put(103,new Department(103,"人力部"));
        departments.put(104,new Department(104,"运营部"));
        departments.put(105,new Department(105,"销售部"));
    }
    //获取所有部门信息
    public Collection<Department> getDepartments(){
        return departments.values();
    }
    //通过ID找到部门
    public Department getDepartmentById(Integer DepartmentById){
        return departments.get(DepartmentById);
    }
}

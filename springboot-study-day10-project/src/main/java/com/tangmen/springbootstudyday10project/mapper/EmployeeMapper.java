package com.tangmen.springbootstudyday10project.mapper;

import com.tangmen.springbootstudyday10project.pojo.Department;
import com.tangmen.springbootstudyday10project.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author:
 * @time: 2020/11/19 21:37
 */
@Repository
public class EmployeeMapper {
    //模拟数据库数据
    private static Map<Integer, Employee> employees=null;
//员工有所属部门
    @Autowired
    private DepartmentMapper departmentMapper;

    static {
        //创建一个部门表
        employees = new HashMap<Integer, Employee>();

        employees.put(1001,new Employee(1001,"AA","15975325.com",1,new Department(101,"销售部")));
        employees.put(1002,new Employee(1002,"BB","15975325.com",0,new Department(101,"人力部")));
        employees.put(1003,new Employee(1003,"CC","15975325.com",0,new Department(101,"研发部")));
        employees.put(1004,new Employee(1004,"DD","15975325.com",0,new Department(101,"运营部")));
        employees.put(1005,new Employee(1005,"EE","15975325.com",1,new Department(101,"教学部")));
    }
    //主键自增
    private static Integer initId =1006;
    //增加一个员工
    public void insert(Employee employee){
        if (employee.getEmploeeId()==null){
            employee.setEmploeeId(initId++);
        }
        employee.setDepartment(departmentMapper.getDepartmentById(employee.getDepartment().getDepartmentId()));
        employees.put(employee.getEmploeeId(),employee);
    }
    //查询员工
    public Collection<Employee>getAll(){
        return employees.values();
    }
    //通过id查询员工
    public  Employee getEmployeeById(Integer employeeById){
        return employees.get(employeeById);
    }
    //删除员工通过id
    public void deleteId(Integer id){
        employees.remove(id);
    }
    }


package com.yangtao.mapper;

import com.yangtao.entity.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmployeeMapper {

    @Select("SELECT emp_id AS empId, emp_name AS empName FROM `mybatis-example`.t_emp")
    List<Employee> listEmployees();

//    @Select("SELECT emp_id AS empId, emp_name AS empName FROM `mybatis-example`.t_emp WHERE emp_id = #{empId} AND emp_name = #{empName}")
    Employee getEmployeByIdAndName(Integer empId, String empName);

    @Insert("INSERT INTO `mybatis-example`.t_emp (emp_name) VALUES (#{empName})")
    @Options(useGeneratedKeys = true, keyProperty = "empId")
    void save(Employee employee);

    @Select("SELECT * FROM `mybatis-example`.t_emp WHERE emp_id = #{idKey} AND emp_name = #{nameKey}")
    Employee getByMap(Map<String, Object> map);

}

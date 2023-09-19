package com.yangtao.controller;

import com.yangtao.entity.Employee;
import com.yangtao.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {


    @Autowired
    private EmployeeMapper employeeMapper;

    @RequestMapping("/list")
    public List<Employee> list() {
        log.info("list employees");

        return employeeMapper.listEmployees();
    }

    @RequestMapping("/getByIdAndName")
    public Employee getEmployeByIdAndName() {

        return employeeMapper.getEmployeByIdAndName(1, "tom");
    }

    @RequestMapping("/save")
    public String save(Employee employee) {
        employeeMapper.save(employee);

        return "empId ：" + employee.getEmpId();
    }

    @RequestMapping("/map")
    public Employee getByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("nameKey", "tom");
        map.put("salaryKey", 22.3);
        map.put("idKey", 1);
        return employeeMapper.getByMap(map);
    }

}

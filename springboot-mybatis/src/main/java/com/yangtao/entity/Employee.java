package com.yangtao.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {

    private Integer empId;

    private String empName;

    private Double empSalary;

    //getter | setter
}
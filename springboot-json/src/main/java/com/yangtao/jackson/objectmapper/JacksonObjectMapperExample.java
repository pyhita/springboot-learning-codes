package com.yangtao.jackson.objectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yangtao.jackson.entity.Address;
import com.yangtao.jackson.entity.Employee;

import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: kante_yang
 * @Date: 2023/9/21 09:48
 */
public class JacksonObjectMapperExample {

    // transform  json to java object using Jackson JSON parser API

        public static void main(String[] args) throws Exception {

            // read json file data to string
            byte[] jsonData = Files.readAllBytes(Paths.get("/Users/kante_yang/Codes/java-backend/springboot-learning-codes/springboot-json/src/main/resources/employee.txt"));
            ObjectMapper objectMapper = new ObjectMapper();

            // convert json string to obj
            Employee employee = objectMapper.readValue(jsonData, Employee.class);

            System.out.println("emp obj : " + employee);

        // convert object to json
        Employee empl = createEmployee();
        // config object mapper
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        // writing to console
        StringWriter stringEmp = new StringWriter();
        objectMapper.writeValue(stringEmp, empl);
        System.out.println("emp json : " + stringEmp);
    }

    public static Employee createEmployee() {

        Employee emp = new Employee();
        emp.setId(100);
        emp.setName("David");
        emp.setPermanent(false);
        emp.setPhoneNumbers(new long[] { 123456, 987654 });
        emp.setRole("Manager");

        Address add = new Address();
        add.setCity("Bangalore");
        add.setStreet("BTM 1st Stage");
        add.setZipcode(560100);
        emp.setAddress(add);

        List<String> cities = new ArrayList<String>();
        cities.add("Los Angeles");
        cities.add("New York");
        emp.setCities(cities);

        Map<String, String> props = new HashMap<String, String>();
        props.put("salary", "1000 Rs");
        props.put("age", "28 years");
        emp.setProperties(props);

        return emp;
    }

}

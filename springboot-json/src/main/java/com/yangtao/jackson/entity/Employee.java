package com.yangtao.jackson.entity;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: kante_yang
 * @Date: 2023/9/21 09:46
 */
@Data
public class Employee {
    private int id;
    private String name;
    private boolean permanent;
    private Address address;
    private long[] phoneNumbers;
    private String role;
    private List<String> cities;
    private Map<String, String> properties;

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("***** Employee Details *****\n");
        sb.append("ID="+getId()+"\n");
        sb.append("Name="+getName()+"\n");
        sb.append("Permanent="+isPermanent()+"\n");
        sb.append("Role="+getRole()+"\n");
        sb.append("Phone Numbers="+ Arrays.toString(getPhoneNumbers())+"\n");
        sb.append("Address="+getAddress()+"\n");
        sb.append("Cities="+Arrays.toString(getCities().toArray())+"\n");
        sb.append("Properties="+getProperties()+"\n");
        sb.append("*****************************");

        return sb.toString();
    }
}

/**
 * {
 *   "id": 123,
 *   "name": "Pankaj",
 *   "permanent": true,
 *   "address": {
 *     "street": "Albany Dr",
 *     "city": "San Jose",
 *     "zipcode": 95129
 *   },
 *   "phoneNumbers": [
 *     123456,
 *     987654
 *   ],
 *   "role": "Manager",
 *   "cities": [
 *     "Los Angeles",
 *     "New York"
 *   ],
 *   "properties": {
 *     "age": "29 years",
 *     "salary": "1000 USD"
 *   }
 * }
 */




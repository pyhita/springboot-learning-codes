package com.yangtao.fastjson;

/**
 * @Author: kante_yang
 * @Date: 2023/9/22 09:51
 */

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.yangtao.fastjson.entity.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 把 JavaBean转换成JSON格式字符串
 */
public class TestJSONString {

    // map 转成json字符串
    @Test
    public void test1() {
        Map<String, Object> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");

        String mapJson = JSON.toJSONString(map);
        System.out.println(mapJson);
    }

    // map 与 jsonObject转换
    @Test
    public void test2() {
        Map map = new HashMap();
        map.put("k1", "v1");
        map.put("k2", "v2");

        JSONObject jsonObject = (JSONObject) JSON.toJSON(map);
        System.out.println(jsonObject.getString("k1"));

        JSONObject jsonObject1 = new JSONObject(map);
        System.out.println(jsonObject1.getString("k1"));

        // JSONObject -> map
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("k1", "v1");
        Map jsonMap = jsonObject2;
        System.out.println(jsonMap.get("k1"));
    }

    // List<Map> -> json
    @Test
    public void test3() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map map1 = new HashMap();
        map1.put("k1", "v1");

        Map map2 = new HashMap();
        map2.put("k2", "v2");

        list.add(map1);
        list.add(map2);

        String jsonString = JSON.toJSONString(list);
        System.out.println(jsonString);
    }

    // Java Bean -> json string
    @Test
    public void test4() {
        Student student = new Student("kante", 22);
        String jsonString = JSON.toJSONString(student);

        System.out.println(jsonString);
    }
}

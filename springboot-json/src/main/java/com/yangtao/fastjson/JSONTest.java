package com.yangtao.fastjson;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.yangtao.fastjson.entity.Student;
import com.yangtao.fastjson.entity.Teacher;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @Author: kante_yang
 * @Date: 2023/9/22 09:20
 */
public class JSONTest {

    //json字符串-简单对象型
    private static final String  JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";
    //json字符串-数组类型
    private static final String  JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";
    //复杂格式json字符串
    private static final String  COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

    // json对象 与 JSON对象
    @Test
    public void test1() {
        JSONObject jsonObject = JSON.parseObject(JSON_OBJ_STR);
        // JSONObject 继承 JSON 所以下面同样成立
        JSONObject jsonObject1 = JSONObject.parseObject(JSON_OBJ_STR);

        System.out.println(jsonObject.getString("studentName") + " " + jsonObject.getInteger("studentAge"));
        jsonObject.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        });
    }

    // json array
    @Test
    public void test2() {
        JSONArray jsonArray = JSON.parseArray(JSON_ARRAY_STR);
//        JSONArray jsonArray1 = JSONArray.parseArray(JSON_ARRAY_STR);

        // 遍历数组
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;

            System.out.println(jsonObject.getString("studentName") + " " + jsonObject.getInteger("studentAge"));
        }
    }

    @Test
    public void test3() throws Exception {
        JSONObject jsonObject = JSON.parseObject(COMPLEX_JSON_STR);

        String teacherName = jsonObject.getString("teacherName");
        Integer teacherAge = jsonObject.getInteger("teacherAge");
        JSONObject course = jsonObject.getJSONObject("course");

    }

    // josn字符串 和 javaBean之间的转换

    // json string -> 简单的java bean
    @Test
    public void test4() {
        Student student = JSONObject.parseObject(JSON_OBJ_STR, new TypeReference<Student>() {});

        System.out.println(student);
    }

    @Test
    public void test5() {
        ArrayList<Student> arrayList = JSON.parseObject(JSON_ARRAY_STR, new TypeReference<ArrayList<Student>>() {});

        System.out.println(arrayList);
    }

    @Test
    public void test6() {
        Teacher teacher = JSON.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>() {});

        System.out.println(teacher);
    }

}

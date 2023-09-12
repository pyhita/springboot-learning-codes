package com.yangtao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.*;

@SpringBootTest(
        properties = {"test.prop=kante"},
        args = {"--user.name=liam"}, // 添加临时的命令行参数
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
// 开启虚拟调用
@AutoConfigureMockMvc
class Springboot04TestApplicationTests {

    @Value("${test.prop}")
    private String prop;
    @Value("${user.name}")
    private String name;

    @Test
    void contextLoads() {
        System.out.println(prop);
        System.out.println(name);
    }

    @Test
    void testStatus(@Autowired MockMvc mockMvc) throws Exception {
        // 创建虚拟请求，当前访问/books
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/books");
        // 执行请求
        ResultActions actions = mockMvc.perform(builder);
        StatusResultMatchers status = MockMvcResultMatchers.status();
        // 预计本次成功值
        ResultMatcher ok = status.isOk();
        // 进行匹配
        actions.andExpect(ok);
    }

    @Test
    void testBody(@Autowired MockMvc mockMvc) throws Exception {
        // 创建虚拟请求，当前访问/books
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/books");
        // 执行请求
        ResultActions actions = mockMvc.perform(builder);
        ContentResultMatchers content = MockMvcResultMatchers.content();
        // 预计本次成功值
        ResultMatcher result = content.string("springboot");
        // 进行匹配
        actions.andExpect(result);
    }

    @Test
    void testJson(@Autowired MockMvc mockMvc) throws Exception {
        // 创建虚拟请求，当前访问/books
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/books");
        // 执行请求
        ResultActions actions = mockMvc.perform(builder);
        ContentResultMatchers content = MockMvcResultMatchers.content();
        // 预计本次成功值
        ResultMatcher result = content.json("{}");
        // 进行匹配
        actions.andExpect(result);
    }

    @Test
    void testHeader(@Autowired MockMvc mockMvc) throws Exception {
        // 创建虚拟请求，当前访问/books
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/books");
        // 执行请求
        ResultActions actions = mockMvc.perform(builder);
        HeaderResultMatchers header = MockMvcResultMatchers.header();
        // 预计本次成功值
        ResultMatcher matcher = header.string("Content-Type", "application/json");
        // 进行匹配
        actions.andExpect(matcher);
    }

}

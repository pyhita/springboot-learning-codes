package com.yangtao.controller;

import com.yangtao.config.JwtProperties;
import com.yangtao.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JwtProperties jwtProperties;

    @GetMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        // 1 对用户名和密码进行校验
        if ("kante".equals(username) && "123".equals(password)) {
            // 2 校验成功，生成jwt
             // 封装cliams
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", "user-01");
            claims.put("job", "leader");

            String token = JwtUtil.createJWT(
                    jwtProperties.getAdminSecretKey(),
                    jwtProperties.getAdminTtl(),
                    claims);

            return token;
        }

        return "";
    }

    @GetMapping
    public String user() {

        return "users";
    }
}

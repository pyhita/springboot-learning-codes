package com.yangtao;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;

@SpringBootTest
class SpringbootJwtApplicationTests {

    @Test
    void contextLoads() {
        // 生成JWT
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 6);
        String token = JWT.create()
                .withClaim("username", "kante")
                .withClaim("userId", "CVE-01")
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256("!rerI0)"));

        System.out.println(token);
    }

    @Test
    void test() {
        // 验证签名
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("!rerI0)")).build();
        DecodedJWT jwt = verifier.
                verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2OTQxMTc1NzksInVzZXJJZCI6IkNWRS0wMSIsInVzZXJuYW1lIjoia2FudGUifQ.muakYn2vPWDnAmLyvsY_Ez_9PJRRH6kWjodUxL9-NLw");

        System.out.println(jwt.getClaim("username").asString());
        System.out.println(jwt.getClaim("userId").asString());

    }

}

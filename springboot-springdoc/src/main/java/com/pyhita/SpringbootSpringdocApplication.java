package com.pyhita;

import com.pyhita.config.OpenApiProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class SpringbootSpringdocApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(
            SpringbootSpringdocApplication.class, args);

//        OpenAPI openAPI = context.getBean(OpenAPI.class);
//        log.info("openAPI: {}", openAPI);
//
//        for (Entry<String, PathItem> entry : openAPI.getPaths().entrySet()) {
//            log.info("entry key: {}", entry.getKey());
//            log.info("entry value: {}", entry.getValue());
//        }

        OpenApiProperties bean = context.getBean(OpenApiProperties.class);
        log.info("bean properties: {}", bean.getExcludeRoleIdUrls());
    }

}

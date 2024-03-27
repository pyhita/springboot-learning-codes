package com.pyhita.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: kante_yang
 * @Date: 2023/12/26
 */
@Configuration
@Slf4j
public class OpenApiConfiguration {

    @Autowired
    private OpenApiProperties openApiProperties;

    @Bean
    public OpenAPI openApi() {
        // info
        License license = new License().name("Copyright © 2023 Trend Micro Incorporated. All rights reserved.").url("https://www.trendmicro.com");
        // components
        Components components = new Components().addSecuritySchemes("bearerAuth", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"));
        // security
        List<SecurityRequirement> security = List.of(new SecurityRequirement().addList("bearerAuth"));
        // servers
        List<Server> servers = new ArrayList<>();
        servers.add(new Server().url("http://localhost" + ":" + 80));
        servers.add(new Server().url("https://internal-api-int.visionone.trendmicro.com").description("Default server url"));

        //创建Api帮助文档的描述信息、联系人信息(contact)、授权许可信息(license)
        Info info = new Info()
            .title("Swagger3.0 (Open API) 框架学习示例文档")      // Api接口文档标题（必填）
            .description("学习Swagger框架而用来定义测试的文档")     // Api接口文档描述
            .version("1.2.1")                                  // Api接口版本
            .termsOfService("https://example.com/")            // Api接口的服务条款地址
            .license(license);                                 // 设置联系人信息

        return new OpenAPI()
            .info(info)
            .components(components)
            .security(security)
            .servers(servers);
    }


    @Bean
    public OpenApiCustomizer openApiCustomizer(@Value("${server.servlet.context-path:}") String contextPath) {
        return openApi -> {
            // Add schemas for default components
            // Replace tags name and sort tags by name
//            if (CollectionUtils.isNotEmpty(openApi.getTags())) {
//                openApi.getTags().forEach(tag -> tag.setName(replaceTagName(tag.getName())));
//                openApi.getTags().sort(Comparator.comparing(Tag::getName));
//            }
            // Make custom configuration modifications to the path class
            Paths paths = new Paths();
            paths.setExtensions(openApi.getPaths().getExtensions());
            for(Map.Entry<String, PathItem> path : openApi.getPaths().entrySet()) {
                String key = path.getKey();
                log.info("path key: {}", path.getKey());
                log.info("path value: {}", path.getValue());
//                path.getValue().readOperations().forEach(this::processOperation);
                paths.put(key, path.getValue());
                if (StringUtils.contains(key, "listPersons")) {
                    path.getValue().readOperations().forEach(operation -> {
                        addConsumerParametersItem(operation);
                    });
                }
            }
            openApi.setPaths(paths);
        };
    }

    private void addConsumerParametersItem(Operation operation) {
        List<Parameter> parameters1 = ListUtils.emptyIfNull(operation.getParameters());
        List<Parameter> parameters2 = new ArrayList<>();
        // 根据 path进行过滤
        parameters2.add(0, buildRoleIdHeader());
        operation.setParameters(ListUtils.union(parameters1, parameters2));
    }

    private HeaderParameter buildRoleIdHeader() {
        HeaderParameter customerIdHeader = new HeaderParameter();
        customerIdHeader.setName("x-role-id");
        customerIdHeader.setDescription("if calling is from console or public, uic or svp will automatically add it. if calling is from backend server, the caller should add it by itself.");
        customerIdHeader.setRequired(false);
        StringSchema stringSchema = new StringSchema();
        customerIdHeader.setSchema(stringSchema);
        return customerIdHeader;
    }


}

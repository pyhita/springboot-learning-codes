package com.pyhita.config;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: kante_yang
 * @Date: 2023/12/26
 */
@Data
@ConfigurationProperties(prefix = "open.api")
@Component
public class OpenApiProperties {

    private Set<String> excludeRoleIdUrls = new HashSet<>();
}

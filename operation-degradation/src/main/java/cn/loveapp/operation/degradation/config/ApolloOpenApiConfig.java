package cn.loveapp.operation.degradation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-04-26 11:39
 * @Description: apollo OpenAPI 配置
 */
@Data
@ConfigurationProperties(prefix = "degradation.apollo.openapi.config")
public class ApolloOpenApiConfig {

    /**
     * 第三方授权配置
     */
    private Apollo apollo;

    @Data
    public static class Apollo {
        private String portalUrl;
        private String token;
    }
}

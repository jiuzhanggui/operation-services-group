package cn.loveapp.operation.degradation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;

import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-04-26 12:01
 * @Description: 降级接口连接配置类
 */
@Data
@Configuration
@EnableConfigurationProperties({ApolloOpenApiConfig.class})
public class DegradationConfiguration {

    @Autowired
    private ApolloOpenApiConfig apolloOpenApiConfig;

    @Autowired
    @Qualifier("userRedisTemplate")
    private StringRedisTemplate userRedisTemplate;

    @Autowired
    @Qualifier("stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    /**
     * apolloAPI 开放客户端
     * 
     * @return
     */
    @Bean
    public ApolloOpenApiClient apolloOpenApiClient() {
        String portalUrl = apolloOpenApiConfig.getApollo().getPortalUrl();
        String token = apolloOpenApiConfig.getApollo().getToken();
        return ApolloOpenApiClient.newBuilder().withPortalUrl(portalUrl).withToken(token).build();
    }
}

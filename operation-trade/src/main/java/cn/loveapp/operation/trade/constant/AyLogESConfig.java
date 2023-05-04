package cn.loveapp.operation.trade.constant;

import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import cn.loveapp.operation.trade.config.MonitorPlatformBaseConfig;

/**
 * @author xujianhu
 * @date 2023-05-03 16:10
 * @Description: 日志ES存储库配置类
 */
@Configuration
public class AyLogESConfig {
    public static final Logger LOGGER = LoggerFactory.getLogger(AyLogESConfig.class);

    @Autowired
    private MonitorPlatformBaseConfig monitorPlatformBaseConfig;

    public RestHighLevelClient elasticsearchClient() {
        String username = monitorPlatformBaseConfig.getUsername();
        String password = monitorPlatformBaseConfig.getPassword();
        String[] urls = monitorPlatformBaseConfig.getUrls().stream().toArray(String[]::new);
        final ClientConfiguration clientConfiguration =
            ClientConfiguration.builder().connectedTo(urls).withBasicAuth(username, password).build();
        return RestClients.create(clientConfiguration).rest();
    }
}

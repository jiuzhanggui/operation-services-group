package com.ruoyi.framework.config.properties;

import java.time.Duration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xujianhu
 * @date 2023-04-27 10:00
 * @Description: redis配置文件
 */
@ConfigurationProperties(prefix = "spring.redis.userinfo")
@Data
public class RedisProperties {

    /**
     * 数据库 默认0
     */
    private int database = 0;

    /**
     * 地址
     */
    private String url;

    /**
     * 主机
     */
    private String host = "localhost";

    /**
     * 密码
     */
    private String password;

    /**
     * 端口号
     */
    private int port = 6379;

    /**
     * 连接超时
     */
    private Duration timeout;

    /**
     * 客户端名
     */
    private String clientName;

}

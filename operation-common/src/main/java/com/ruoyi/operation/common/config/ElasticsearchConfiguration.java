package com.ruoyi.operation.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-05-04 10:24
 * @Description: ES配置
 */
@Data
@Configuration
public class ElasticsearchConfiguration {
    /**
     * 索引数量
     */
    @Value("${orders.elasticsearch.index.num: 5}")
    private int indexNum;
}

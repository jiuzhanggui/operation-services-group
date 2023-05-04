package com.ruoyi.operation.common.dao.es;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import javax.validation.constraints.NotNull;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.operation.common.config.ElasticsearchConfiguration;

/**
 * @author xujianhu
 * @date 2023-05-04 10:04
 * @Description: ES基础dao
 */
public abstract class BaseElasticsearchDao {
    public static final String GMT_FORMATER = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String MINUTE_SECOND_FORMATER = "yyyy-MM-dd'T'HH:mm:ss";

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseElasticsearchDao.class);

    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GMT_FORMATER);
    protected final DateTimeFormatter minuteSecondFormatter = DateTimeFormatter.ofPattern(MINUTE_SECOND_FORMATER);

    protected ElasticsearchConfiguration elasticsearchConfiguration;

    protected RestHighLevelClient client;

    // protected ResultsMapper resultsMapper;

    protected ElasticsearchOperations operations;

    protected ObjectMapper notNullObjectMapper;

    public BaseElasticsearchDao(ElasticsearchOperations operations, RestHighLevelClient client) {
        this.operations = operations;
        this.client = client;
        notNullObjectMapper = new ObjectMapper();
        notNullObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        notNullObjectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    }

    public ElasticsearchOperations getOperations() {
        return operations;
    }

    public RestHighLevelClient getClient() {
        return client;
    }

    public abstract String getIndexName(String sellerId);

    public abstract String getIndexName();

    public abstract String getRouting(String sellerId);

    protected String getHashRoutingKey(@NotNull String sellerId, String corpId) {
        return sellerId;
    }
}

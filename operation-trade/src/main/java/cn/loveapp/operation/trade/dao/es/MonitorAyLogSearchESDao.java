package cn.loveapp.operation.trade.dao.es;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;

import cn.loveapp.operation.trade.config.MonitorPlatformBaseConfig;
import cn.loveapp.operation.trade.constant.AyLogESConfig;
import cn.loveapp.operation.trade.constant.LogEsFields;
import cn.loveapp.operation.trade.entity.AyLogSearchES;

/**
 * @author xujianhu
 * @date 2023-05-03 16:06
 * @Description: 爱用日志搜索数据访问类
 */
@Primary
@Repository
public class MonitorAyLogSearchESDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorAyLogSearchESDao.class);

    @Autowired
    private AyLogESConfig ayLogESConfig;

    @Autowired
    private MonitorPlatformBaseConfig monitorPlatformBaseConfig;

    private RestHighLevelClient client;

    /**
     * 通过timestamp查询日志，并筛选指定订单的日志
     *
     * @param sellerNick
     * @param tid
     * @param timestamp
     * @param indexPrefix
     * @return
     */
    public List<String> getLogMessageListBySellerAndTid(String sellerNick, String tid, Date timestamp,
        String indexPrefix) {
        BoolQueryBuilder boolQueryBuilder =
            QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery(LogEsFields.timestamp).gte(timestamp))
                .must(QueryBuilders.matchPhraseQuery(LogEsFields.sellerNick, sellerNick))
                .must(QueryBuilders.matchPhraseQuery(LogEsFields.message, sellerNick))
                .must(QueryBuilders.matchPhraseQuery(LogEsFields.message, tid));

        List<String> messages = new ArrayList<>();
        try {
            List<AyLogSearchES> ayLogSearchES = searchLogByQuery(new NativeSearchQueryBuilder()
                .withSearchType(SearchType.QUERY_THEN_FETCH).withQuery(boolQueryBuilder).build(), indexPrefix);
            if (ayLogSearchES != null) {
                getMessage(messages, ayLogSearchES);
            }
        } catch (IOException e) {
            LOGGER.error(sellerNick, tid, "通过timestamp日志查询异常 " + e.getMessage());
        }
        return messages;
    }

    /**
     * 通过sellerNick查询日志
     *
     * @param sellerNick
     * @param indexPrefix
     * @return
     */
    public List<String> getLogMessageListBySellerNick(String sellerNick, String indexPrefix) {
        BoolQueryBuilder boolQueryBuilder =
            QueryBuilders.boolQuery().must(QueryBuilders.termsQuery(LogEsFields.sellerNick, sellerNick))
                .must(QueryBuilders.matchPhraseQuery(LogEsFields.message, sellerNick));
        List<String> messages = new ArrayList<>();
        try {
            List<AyLogSearchES> ayLogSearchES = searchLogByQuery(new NativeSearchQueryBuilder()
                .withSearchType(SearchType.QUERY_THEN_FETCH).withQuery(boolQueryBuilder).build(), indexPrefix);
            if (ayLogSearchES != null) {
                getMessage(messages, ayLogSearchES);
            }
        } catch (IOException e) {
            LOGGER.error(sellerNick, null, "通过timestamp日志查询异常 " + e.getMessage());
        }
        return messages;
    }

    /**
     * 通过sellerNick和messageParts查询message内容
     *
     * @param sellerNick
     * @param messageParts
     * @param indexPrefix
     * @return
     */
    public List<String> getLogMessageList(String sellerNick, List<String> messageParts, Date timestamp,
        String indexPrefix) {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchPhraseQuery(LogEsFields.sellerNick, sellerNick));
        boolQueryBuilder.must(QueryBuilders.rangeQuery(LogEsFields.timestamp).gte(timestamp));
        if (messageParts.size() > 0) {
            for (String messagePart : messageParts) {
                boolQueryBuilder.must(QueryBuilders.matchPhraseQuery(LogEsFields.message, messagePart));
            }
        }
        List<String> messageList = new ArrayList<>();
        try {
            List<AyLogSearchES> ayLogSearchES = searchLogByQuery(new NativeSearchQueryBuilder()
                .withSearchType(SearchType.QUERY_THEN_FETCH).withQuery(boolQueryBuilder).build(), indexPrefix);
            if (ayLogSearchES != null) {
                getMessage(messageList, ayLogSearchES);
            }
        } catch (IOException e) {
            LOGGER.error(sellerNick, null, "通过timestamp日志查询异常 " + e.getMessage());
        }
        return messageList;
    }

    public List<String> getLogMessageList(String sellerNick, List<String> messageParts, String indexPrefix) {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchPhraseQuery(LogEsFields.sellerNick, sellerNick));
        if (messageParts.size() > 0) {
            for (String messagePart : messageParts) {
                boolQueryBuilder.must(QueryBuilders.matchPhraseQuery(LogEsFields.message, messagePart));
            }
        }
        List<String> messageList = new ArrayList<>();
        try {
            List<AyLogSearchES> ayLogSearchES = searchLogByQuery(new NativeSearchQueryBuilder()
                .withSearchType(SearchType.QUERY_THEN_FETCH).withQuery(boolQueryBuilder).build(), indexPrefix);
            if (ayLogSearchES != null) {
                getMessage(messageList, ayLogSearchES);
            }
        } catch (IOException e) {
            LOGGER.error(sellerNick, null, "通过timestamp日志查询异常 " + e.getMessage());
        }
        return messageList;
    }

    /**
     * 通过查询条件和索引查询日志
     *
     * @param query
     * @param indexPrefix
     * @return
     * @throws IOException
     */
    private List<AyLogSearchES> searchLogByQuery(NativeSearchQuery query, String indexPrefix) throws IOException {
        getClient();
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(query.getQuery());
        builder.sort(LogEsFields.timestamp, SortOrder.DESC);
        builder.size(monitorPlatformBaseConfig.getEsSearchSize());
        SearchRequest searchRequest = new SearchRequest(getIndexName(indexPrefix));
        searchRequest.indicesOptions(IndicesOptions.strictExpand());
        searchRequest.source(builder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(builder.query());

        List<AyLogSearchES> ayLogSearchESArrayList = new ArrayList<>();
        for (int i = 0; i < response.getHits().getHits().length; i++) {
            SearchHit at = response.getHits().getAt(i);
            Map<String, Object> sourceAsMap = at.getSourceAsMap();
            AyLogSearchES ayLogSearchES = JSON.parseObject(JSON.toJSONString(sourceAsMap), AyLogSearchES.class);
            ayLogSearchESArrayList.add(ayLogSearchES);
        }
        return ayLogSearchESArrayList;
    }

    /**
     * 获取es客户端
     */
    private void getClient() {
        if (client == null) {
            client = ayLogESConfig.elasticsearchClient();
        }
    }

    /**
     * 获取索引名
     *
     * @param indexPrefix
     * @return
     */
    private String getIndexName(String indexPrefix) {
        return indexPrefix + "*";
    }

    /**
     * 获取消息内容
     *
     * @param messages
     * @param ayLogSearchES
     */
    private void getMessage(List<String> messages, List<AyLogSearchES> ayLogSearchES) {
        for (AyLogSearchES ayLogSearch : ayLogSearchES) {
            messages.add(ayLogSearch.getMessage());
        }
    }
}

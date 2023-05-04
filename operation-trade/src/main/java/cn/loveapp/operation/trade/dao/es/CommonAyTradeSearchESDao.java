package cn.loveapp.operation.trade.dao.es;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.operation.common.constant.CommonPlatformConstants;
import com.ruoyi.operation.common.dao.es.BaseElasticsearchDao;

import cn.loveapp.operation.trade.constant.EsFields;

/**
 * @author xujianhu
 * @date 2023-05-04 10:19
 * @Description:
 */
@Repository
public class CommonAyTradeSearchESDao extends BaseElasticsearchDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(CommonAyTradeSearchESDao.class);

    public static final String INDEX_NAME_PREFIX = "ay_trade_search-";

    public CommonAyTradeSearchESDao(ElasticsearchOperations operations, RestHighLevelClient client) {
        super(operations, client);
    }

    @Override
    public String getIndexName() {
        return INDEX_NAME_PREFIX + "*";
    }

    @Override
    public String getIndexName(String sellerId) {
        return INDEX_NAME_PREFIX + (Math.abs(sellerId.hashCode()) % elasticsearchConfiguration.getIndexNum());
    }

    @Override
    public String getRouting(String sellerId) {
        return sellerId;
    }

    public Object getById(String sellerId, String tid, String platformId, String appName) {
        String id = getId(tid, platformId, appName);
        if (StringUtils.isEmpty(id)) {
            LOGGER.error("id为NULL, 用户id: " + sellerId + ", platformId: " + platformId + ", appName: " + appName);
        }
        GetRequest request = new GetRequest(getIndexName(sellerId)).id(id).routing(getRouting(sellerId));
        try {
            GetResponse response = client.get(request, RequestOptions.DEFAULT);
            return response.getSourceAsMap();
        } catch (IOException e) {
            throw new RuntimeException("Error while getting for request: " + JSON.toJSONString(request), e);
        }
    }

    /**
     * 根据tid或区域订单信息
     *
     * @param tid
     * @param platformId
     * @param appName
     * @return
     */
    public Object getByTid(@NotEmpty String tid, String platformId, String appName) {

        SearchRequest request = new SearchRequest(getIndexName());
        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        if (StringUtils.isNotEmpty(tid)) {
            boolQueryBuilder.must(QueryBuilders.termQuery(EsFields.tid, tid));
        }

        if (StringUtils.isAnyBlank(platformId, appName)) {
            boolQueryBuilder.must(QueryBuilders.termQuery(EsFields.sellerId, platformId));
        }

        appName = cheakAppName(platformId, appName);
        if (StringUtils.isNotEmpty(appName)) {
            boolQueryBuilder.must(QueryBuilders.termQuery(EsFields.appName, appName));
        }

        builder.query(boolQueryBuilder);
        request.source(builder);
        try {
            SearchResponse search = client.search(request, RequestOptions.DEFAULT);
            SearchHits hits = search.getHits();
            List<Map<String, Object>> result = new ArrayList<>();
            for (SearchHit hit : hits) {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                result.add(sourceAsMap);
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException("Error while getting for request: " + JSON.toJSONString(request), e);
        }
    }

    private String cheakAppName(String platformId, String appName) {
        if (CommonPlatformConstants.PLATFORM_TAO.equals(platformId)) {
            return null;
        }
        return appName;
    }

    private String getId(String tid, String platformId, String appName) {
        if (tid != null && platformId != null) {
            return platformId + StringUtils.trimToEmpty(appName) + tid;
        }
        return null;
    }
}

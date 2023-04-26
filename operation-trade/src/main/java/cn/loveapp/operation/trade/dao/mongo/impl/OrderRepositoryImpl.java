package cn.loveapp.operation.trade.dao.mongo.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ruoyi.operation.common.constant.MongoConstant;
import com.ruoyi.operation.common.dao.mongo.CommonMongoDao;

import cn.loveapp.operation.trade.dao.mongo.OrderRepository;

/**
 * @author xujianhu
 * @date 2023-04-24 18:41
 * @Description: 订单mongo库操作实现类
 */
@Repository
public class OrderRepositoryImpl extends CommonMongoDao implements OrderRepository {

    @Override
    protected String getCollectionName() {
        return "tc_order";
    }

    @Override
    public Object queryByTid(String tid, String storeId, String appName) {

        Query query = query(where(MongoConstant.TID).is(tid).and(MongoConstant.STORE_ID_FIELD).is(storeId)
            .and(MongoConstant.APP_NAME_FIELD).is(StringUtils.trimToNull(appName)));

        return findOne(query, Map.class);
    }

    @Override
    public Object queryAllByTid(String tid, String storeId, String appName) {
        Query query = query(where(MongoConstant.TID).is(tid).and(MongoConstant.STORE_ID_FIELD).is(storeId)
            .and(MongoConstant.APP_NAME_FIELD).is(StringUtils.trimToNull(appName)));

        return findAll(query, Map.class);
    }

}

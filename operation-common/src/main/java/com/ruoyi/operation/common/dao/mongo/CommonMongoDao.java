package com.ruoyi.operation.common.dao.mongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @author xujianhu
 * @date 2023-04-24 18:04
 * @Description: 订单mongo数据库访问
 */
public abstract class CommonMongoDao {

    protected Logger logger = LoggerFactory.getLogger(CommonMongoDao.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询指定条件的单个文档
     *
     * @param query
     * @param t
     * @return
     * @param <T>
     */
    protected <T> T findOne(Query query, Class<T> t) {
        return mongoTemplate.findOne(query, t, getCollectionName());
    }

    /**
     * 查询指定条件的全部文档
     *
     * @param query
     * @param t
     * @return
     * @param <T>
     */
    protected <T> List<T> findAll(Query query, Class<T> t) {
        return mongoTemplate.find(query, t, getCollectionName());
    }

    protected Query query(Criteria c) {
        return new Query().addCriteria(c);
    }

    protected Criteria where(String s) {
        return Criteria.where(s);
    }

    /**
     * 获取collectionName
     * 
     * @return string
     */
    abstract protected String getCollectionName();

}

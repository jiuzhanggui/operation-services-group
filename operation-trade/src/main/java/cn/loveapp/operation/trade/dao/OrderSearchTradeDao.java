package cn.loveapp.operation.trade.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.loveapp.operation.trade.entity.OrderSearchTrade;

/**
 * 订单记录基本信息(TaobaoOrderSearchTrade)表数据库访问层
 *
 * @author xujianhu
 * @since 2022-03-23 16:26:05
 */
@Mapper
public interface OrderSearchTradeDao {

    /***
     * 通过nice查询单条数据
     *
     * @param tableName
     * @param nick
     * @return
     */
    OrderSearchTrade queryByNick(@Param("tableName") String tableName, @Param("nick") String nick);

    /**
     * 通过sellerId查询单条数据
     *
     * @param tableName
     * @param sellerId
     * @return
     */
    OrderSearchTrade queryBySellerId(@Param("tableName") String tableName, @Param("sellerId") String sellerId);

    /**
     * 获取用户订购记录
     *
     * @param sellerNick
     * @param tableName
     * @return
     */
    List<OrderSearchTrade> queryListBySellerNick(@Param("sellerNick") String sellerNick,
        @Param("tableName") String tableName);

    /**
     * 获取用户订购记录
     *
     * @param sellerId
     * @param tableName
     * @return
     */
    List<OrderSearchTrade> queryListBySellerId(@Param("sellerId") String sellerId,
        @Param("tableName") String tableName);
}

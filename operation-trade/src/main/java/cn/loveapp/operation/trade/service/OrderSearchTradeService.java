package cn.loveapp.operation.trade.service;

import java.util.List;

import cn.loveapp.operation.trade.dto.OrderSearchTradeInfoDto;
import cn.loveapp.operation.trade.entity.OrderSearchTrade;

/**
 * 订单记录基本信息(TaobaoOrderSearchTrade)表服务接口
 *
 * @author xujianhu
 * @since 2022-03-23 16:26:06
 */
public interface OrderSearchTradeService {

    /**
     * 获取最新的用户订购信息
     *
     * @param nick
     * @param sellerId
     * @param storeId
     * @param appName
     * @return
     */
    OrderSearchTrade getOrderSearchInfo(String nick, String sellerId, String storeId, String appName);

    /**
     * 获取用户订购记录
     *
     * @param sellerNick
     * @param sellerId
     * @param storeId
     * @param appName
     * @return
     */
    List<OrderSearchTradeInfoDto> getAllOrderSearchBySellerNick(String sellerNick, String sellerId, String storeId,
        String appName);

}

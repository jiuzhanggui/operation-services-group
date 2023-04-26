package cn.loveapp.operation.trade.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.operation.common.constant.CommonPlatformConstants;

import cn.loveapp.operation.trade.dao.mysql.OrderSearchTradeDao;
import cn.loveapp.operation.trade.dto.OrderSearchTradeInfoDTO;
import cn.loveapp.operation.trade.entity.OrderSearchTrade;
import cn.loveapp.operation.trade.service.OrderSearchTradeService;

/**
 * 订单记录基本信息(TaobaoOrderSearchTrade)表服务实现类
 *
 * @author xujianhu
 * @since 2022-03-23 16:26:10
 */
@Service
@DataSource(value = DataSourceType.DREAM_DATA_SOURCE)
public class OrderSearchTradeServiceImpl implements OrderSearchTradeService {
    private static final String TABLE_NAME = "_order_search_trade";

    @Autowired
    private OrderSearchTradeDao orderSearchTradeDao;

    /**
     * 获取订单信息
     *
     * @param nick
     * @param sellerId
     * @param storeId
     * @param appName
     * @return
     */
    @Override
    public OrderSearchTrade getOrderSearchInfo(String nick, String sellerId, String storeId, String appName) {
        if (CommonPlatformConstants.PLATFORM_DOUDIAN.equals(storeId)) {
            return orderSearchTradeDao.queryBySellerId(getStoreTableName(storeId), sellerId);
        }
        return orderSearchTradeDao.queryByNick(getStoreTableName(storeId), nick);
    }

    @Override
    public List<OrderSearchTradeInfoDTO> getAllOrderSearchBySellerNick(String sellerNick, String sellerId,
                                                                       String storeId, String appName) {
        // 抖店没有存nick
        List<OrderSearchTrade> orderSearchTrades = null;
        if (CommonPlatformConstants.PLATFORM_DOUDIAN.equals(storeId)) {
            orderSearchTrades = orderSearchTradeDao.queryListBySellerId(sellerId, getStoreTableName(storeId));
        } else {
            orderSearchTrades = orderSearchTradeDao.queryListBySellerNick(sellerNick, getStoreTableName(storeId));
        }

        if (orderSearchTrades == null) {
            return null;
        }
        List<OrderSearchTradeInfoDTO> orderSearchTradeInfoDTOList =
            orderSearchTrades.stream().map(OrderSearchTradeInfoDTO::of).collect(Collectors.toList());
        return orderSearchTradeInfoDTOList;
    }

    /**
     * 判断数据来源storeId
     *
     * @param storeId
     * @return
     */
    public String getStoreTableName(String storeId) {
        if (CommonPlatformConstants.PLATFORM_TAO.equals(storeId)) {
            return "taobao" + TABLE_NAME;
        } else {
            return storeId.toLowerCase() + TABLE_NAME;
        }
    }
}

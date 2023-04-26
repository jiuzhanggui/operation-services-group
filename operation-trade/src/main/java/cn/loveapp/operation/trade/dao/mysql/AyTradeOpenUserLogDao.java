package cn.loveapp.operation.trade.dao.mysql;

import cn.loveapp.operation.trade.entity.AyTradeOpenUserLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xujianhu
 * @date 2023-04-24 17:49
 * @Description: (ay_trade_open_user_log)开户记录日志表数据访问层
 */
public interface AyTradeOpenUserLogDao {

    /**
     * 通过用户名获取订购记录
     * @param sellerNick
     * @return
     */
    List<AyTradeOpenUserLog> getAllBySellerNick(@Param("sellerNick") String sellerNick);
}

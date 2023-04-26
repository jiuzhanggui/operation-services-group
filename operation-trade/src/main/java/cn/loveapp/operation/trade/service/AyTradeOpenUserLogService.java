package cn.loveapp.operation.trade.service;

import java.util.List;

import cn.loveapp.operation.trade.dto.AyTradeOpenUserLogDTO;

/**
 * @author xujianhu
 * @date 2023-04-24 17:51
 * @Description: (ay_trade_open_user_log)开户记录日志表数据访问接口
 */
public interface AyTradeOpenUserLogService {
    /**
     * 获取用户开户日志记录
     *
     * @param sellerNick
     * @return
     */
    List<AyTradeOpenUserLogDTO> getAllOpenUserLogBySellerNick(String sellerNick);
}

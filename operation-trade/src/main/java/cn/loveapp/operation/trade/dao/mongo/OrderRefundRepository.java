package cn.loveapp.operation.trade.dao.mongo;

/**
 * @author xujianhu
 * @date 2023-04-26 10:54
 * @Description: 退款订单mongo库操作类
 */
public interface OrderRefundRepository {
    /**
     * 通过订单号查询订单信息
     *
     * @param tid
     * @param storeId
     * @param appName
     * @return
     */
    Object queryByTid(String tid, String storeId, String appName);

    /**
     * 通过订单号查询所有订单信息
     *
     * @param tid
     * @param storeId
     * @param appName
     * @return
     */
    Object queryAllByTid(String tid, String storeId, String appName);
}

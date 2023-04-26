package cn.loveapp.operation.trade.dao.mongo;

/**
 * @author xujianhu
 * @date 2023-04-26 10:06
 * @Description: 子单mongo库操作类
 */
public interface SubOrderRepository {
    /**
     * 通过订单号查询订单信息
     *
     * @param tid
     * @param storeId
     * @param appName
     * @return
     */
    Object queryAllByTid(String tid, String storeId, String appName);
}

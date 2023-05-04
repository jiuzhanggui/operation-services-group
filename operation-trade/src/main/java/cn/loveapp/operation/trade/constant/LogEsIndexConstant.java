package cn.loveapp.operation.trade.constant;

import com.ruoyi.operation.common.constant.CommonPlatformConstants;
import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-05-03 16:31
 * @Description: 日志查询ES索引常量类
 */
@Data
public class LogEsIndexConstant {
    /**
     * newuser-service 服务日志索引前缀
     */
    public static final String ORDER_NEWUSER_SERVICE_INDEX_PREFIX = "order-newuser-service-";

    /**
     * newuser-scheduler 服务日志索引前缀
     */
    public static final String ORDER_NEWUSER_SCHEDULER_INDEX_PREFIX = "order-newuser-scheduler-";

    /**
     * newuser-soldget-tao 服务日志索引前缀
     */
    public static final String ORDER_NEWUSER_SOLDGET_TAO_INDEX_PREFIX = "order-newuser-soldget-tao-";

    /**
     * newuser-soldget-multi 服务日志索引前缀
     */
    public static final String ORDER_NEWUSER_SOLDGET_MULTI_INDEX_PREFIX = "order-newuser-soldget-multi-";

    /**
     * newuser-fullinfo-tao 服务日志索引前缀
     */
    public static final String ORDER_NEWUSER_FULLINFO_TAO_INDEX_PREFIX = "order-newuser-fullinfo-tao-";

    /**
     * newuser-fullinfo-multi 服务日志索引前缀
     */
    public static final String ORDER_NEWUSER_FULLINFO_MULTI_INDEX_PREFIX = "order-newuser-fullinfo-multi-";

    /**
     * 退款推送
     */
    public static final String NEW_USER_REFUND_GET = "order-newuser-refundget-";

    /**
     * mc-fullinfo-tao 服务日志索引前缀
     */
    public static final String ORDER_MC_FULLINFO_TAO_INDEX_PREFIX = "order-mc-fullinfo-tao-";

    /**
     * mc-fullinfo-multi 服务日志索引前缀
     */
    public static final String ORDER_MC_FULLINFO_MULTI_INDEX_PREFIX = "order-mc-fullinfo-multi-";

    /**
     * mc-router-tao 服务日志索引前缀
     */
    public static final String ORDER_MC_ROUTER_TAO_INDEX_PREFIX = "order-mc-router-tao-";

    /**
     * mc-router-multi 服务日志索引前缀
     */
    public static final String ORDER_MC_ROUTER_MULTI_INDEX_PREFIX = "order-mc-router-tao-";

    /**
     * tmc-trade 服务日志索引前缀
     */
    public static final String TMC_TRADE = "tmcsub-trade-";

    /**
     * tmc-pdd 服务日志索引前缀
     */
    public static final String TMC_PDD = "tmcsub-pdd-";

    /**
     * tmc-kwshop 服务日志索引前缀
     */
    public static final String TMC_KWSHOP = "tmcsub-kwaishop";

    /**
     * tmc-doudian 服务日志索引前缀
     */
    public static final String TMC_DOUDIAN = "tmcsub-doudian-";

    /**
     * tmc-1688 服务日志索引前缀
     */
    public static final String TMC_1688 = "tmcsub-1688-";

    /**
     * rds-consumer-tao
     */
    public static final String RDS_CONSUMER_TAO = "order-rds-consumer-tao-";

    /**
     * rds-consumer-pdd
     */
    public static final String RDS_CONSUMER_PDD = "order-rds-consumer-pdd-";

    /**
     * rds-scheduler-tao
     */
    public static final String RDS_SCHEDULER_TAO = "order-rds-scheduler-tao-";

    /**
     * rds-scheduler-pdd
     */
    public static final String RDS_SCHEDULER_PDD = "order-rds-scheduler-pdd-";

    /**
     * 获取Mc-RouterIndexPrefix
     *
     * @param storeId
     * @return
     */
    public static String getMcRouterTaoIndexPrefix(String storeId) {
        return CommonPlatformConstants.PLATFORM_TAO.equals(storeId)
            ? LogEsIndexConstant.ORDER_MC_ROUTER_TAO_INDEX_PREFIX
            : LogEsIndexConstant.ORDER_MC_ROUTER_MULTI_INDEX_PREFIX;
    }

    /**
     * 获取TmcSubIndexPrefix
     *
     * @param storeId
     * @return
     */
    public static String getTmcSubIndexPrefix(String storeId) {
        String indexPrefix;
        if (CommonPlatformConstants.PLATFORM_DOUDIAN.equals(storeId)) {
            indexPrefix = LogEsIndexConstant.TMC_DOUDIAN;
        } else if (CommonPlatformConstants.PLATFORM_PDD.equals(storeId)) {
            indexPrefix = LogEsIndexConstant.TMC_PDD;
        } else if (CommonPlatformConstants.PLATFORM_1688.equals(storeId)) {
            indexPrefix = LogEsIndexConstant.TMC_1688;
        } else if (CommonPlatformConstants.PLATFORM_KWAISHOP.equals(storeId)) {
            indexPrefix = LogEsIndexConstant.TMC_KWSHOP;
        } else {
            indexPrefix = LogEsIndexConstant.TMC_TRADE;
        }
        return indexPrefix;
    }

    /**
     * 通过storeId获取SoldGetGetIndexPrefix
     *
     * @param storeId
     * @return
     */
    public static String getSoldGetIndexPrefix(String storeId) {
        return CommonPlatformConstants.PLATFORM_TAO.equals(storeId)
            ? LogEsIndexConstant.ORDER_NEWUSER_SOLDGET_TAO_INDEX_PREFIX
            : LogEsIndexConstant.ORDER_NEWUSER_SOLDGET_MULTI_INDEX_PREFIX;
    }

    /**
     * 通过storeId获取RdsConsumerIndexPrefix(目前只有tao和pdd)
     *
     * @param storeId
     * @return
     */
    public static String getRdsConsumerIndexPrefix(String storeId) {
        return CommonPlatformConstants.PLATFORM_TAO.equals(storeId) ? LogEsIndexConstant.RDS_CONSUMER_TAO
            : LogEsIndexConstant.RDS_CONSUMER_PDD;
    }

    /**
     * 通过storeId获取RdsSchedulerIndexPrefix(目前只有tao和pdd)
     *
     * @param storeId
     * @return
     */
    public static String getRdsSchedulerIndexPrefix(String storeId) {
        return CommonPlatformConstants.PLATFORM_TAO.equals(storeId) ? LogEsIndexConstant.RDS_SCHEDULER_TAO
            : LogEsIndexConstant.RDS_SCHEDULER_PDD;
    }

    /**
     * 通过storeId获取FullInfoIndexPrefix
     *
     * @param storeId
     * @return
     */
    public static String getMcFullInfoIndexPrefix(String storeId) {
        return CommonPlatformConstants.PLATFORM_TAO.equals(storeId)
            ? LogEsIndexConstant.ORDER_MC_FULLINFO_TAO_INDEX_PREFIX
            : LogEsIndexConstant.ORDER_MC_FULLINFO_MULTI_INDEX_PREFIX;
    }
}

package cn.loveapp.operation.trade.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-04-24 17:06
 * @Description: 数据落库状态记录表(AyOrderPullRecord)实体类
 */
@Data
public class UserProductionInfoExt {
    private static final long serialVersionUID = -13290625055810639L;

    private Integer id;
    /**
     * 商家id
     */
    private String sellerId;
    /**
     * 用户nick
     */
    private String sellerNick;
    /**
     * 店铺id
     */
    private String storeId;

    private String corpId;
    /**
     * 存单状态
     */
    private Integer dbStatus;
    /**
     * top状态
     */
    private String topStatus;
    /**
     * 入库时，淘宝近三个月的订单数量
     */
    private Integer topTradeCount;
    /**
     * topsession状态
     */
    private Integer sessionStatus;
    /**
     * 降级标
     */
    private String downgradeTag;
    /**
     * 数据库ID
     */
    private Integer dbId;
    /**
     * 搜索库id
     */
    private Integer searchdbId;

    private Integer listId;

    /**
     * 拉单状态
     */
    private Integer pullStatus;

    private Integer apiStatus;

    /**
     * 用户灰度标志
     */
    private Integer grayLevel;

    /**
     * 拉单开始时间
     */
    private LocalDateTime pullStartDateTime;

    /**
     * 拉单结束时间
     */
    private LocalDateTime pullEndDateTime;

    /**
     * 拉单到最后一笔订单完成标志
     */
    private Boolean pullEndPoint;

    /**
     * 已开通的应用
     */
    private String openedAppNames;

    /**
     * 1688 memberId 或 tradeSupplier的supplierId
     */
    private String memberId;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 最后修改时间
     */
    private LocalDateTime gmtModified;

    public static final Integer DB_WAIT = 101;
    public static final Integer DB_DOING = 102;
    public static final Integer DB_FAILED = -101;
    public static final Integer DB_FAILED_BY_WEB = -102;
    public static final Integer DB_FAILED_BY_SESSION = -103;
    public static final Integer DB_DONE = 10;

    public static final Integer API_STATUS_FAILED_BY_SESSION = -201;
    public static final Integer API_STATUS_SUCCESS = 10;

    /**
     * check api status信息
     * 
     * @param apiStatus
     * @return
     */
    public static Boolean checkApiStatus(Integer apiStatus) {
        return Objects.equals(API_STATUS_FAILED_BY_SESSION, apiStatus);
    }

    public Boolean isSuccess() {
        return topStatus.equals(DB_DONE.toString()) && dbStatus.equals(DB_DONE) && pullStatus.equals(DB_DONE);
    }

}

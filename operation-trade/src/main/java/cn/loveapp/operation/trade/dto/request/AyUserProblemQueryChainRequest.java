package cn.loveapp.operation.trade.dto.request;

import cn.loveapp.operation.trade.dto.response.ProblemAnalysisResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @author xujianhu
 * @date 2023-05-03 16:21
 * @Description: 爱用日志链请求体
 */
@Data
public class AyUserProblemQueryChainRequest {

    /**
     * 用户昵称
     */
    private String sellerNick;

    /**
     * 用户ID
     */
    private String sellerId;

    /**
     * 用户订单号
     */
    private String tid;

    /**
     * 平台ID
     */
    private String storeId;

    /**
     * 应用昵称
     */
    private String appName;

    /**
     * 用户秘钥
     */
    private String topSession;

    /**
     * 问题分析响应体
     */
    private ProblemAnalysisResponse problemAnalysisResponse;

    /**
     * 是否是淘宝退款订单
     */
    private Boolean isTaoBaoRefundOrder;

    /**
     * 是否是退款订单
     */
    private Boolean isRefundOrder;

    /**
     * 平台订单状态
     */
    private String platformStatus;

    /**
     * 爱用订单状态
     */
    private String nowStatus;

    /**
     * 爱用退款订单子单集合（子单号，退款状态）
     */
    private Map<String, String> nowRefundStatusMap;

    /**
     * 日志查询开始时间
     */
    private Date searchLogStartTime;

    /**
     * 退款订单的修改时间
     */
    private Date refundOrderModified;

    /**
     * 拉单开始时间
     */
    private LocalDateTime pullStartDateTime;
}

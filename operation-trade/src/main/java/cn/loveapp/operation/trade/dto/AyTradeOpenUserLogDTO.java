package cn.loveapp.operation.trade.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-04-24 17:51
 * @Description:
 */
@Data
public class AyTradeOpenUserLogDTO {
    /**
     * 开户id表示
     */
    private Integer openUserId;

    /**
     * 卖家主昵称
     */
    private String sellerNick;

    /**
     * 10:已开通,-101:授权失败,-102:已到期
     */
    private Integer status;

    /**
     * 开通规则,1:全开通
     */
    private Integer type;

    /**
     * 规则Id
     */
    private Integer ruleId;

    /**
     * 原因
     */
    private String reason;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModify;

    /**
     * 用户来源应用
     */
    private String appName;
}

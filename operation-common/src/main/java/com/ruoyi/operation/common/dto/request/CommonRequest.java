package com.ruoyi.operation.common.dto.request;

import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-03-17 09:52
 * @Description: 公共请求体
 */
@Data
public class CommonRequest {

    /**
     * 卖家昵称
     */
    private String sellerNick;

    /**
     * 卖家ID
     */
    private String sellerId;

    /**
     * 平台
     */
    private String platformId;

    /**
     * 应用名称
     */
    private String appName;

}

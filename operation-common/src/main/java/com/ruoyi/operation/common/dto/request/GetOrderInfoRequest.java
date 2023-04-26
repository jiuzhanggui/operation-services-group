package com.ruoyi.operation.common.dto.request;

import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-04-24 17:59
 * @Description: 获取订单信息请求体
 */
@Data
public class GetOrderInfoRequest extends CommonRequest{

    /**
     * 订单号
     */
    private String tid;
}

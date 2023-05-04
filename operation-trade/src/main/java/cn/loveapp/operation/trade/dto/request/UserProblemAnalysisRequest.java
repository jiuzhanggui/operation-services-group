package cn.loveapp.operation.trade.dto.request;

import com.ruoyi.operation.common.dto.request.CommonRequest;

import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-05-03 16:14
 * @Description: 用户问题分析请求体
 */
@Data
public class UserProblemAnalysisRequest extends CommonRequest {
    /**
     * 订单号
     */
    private String tid;
}

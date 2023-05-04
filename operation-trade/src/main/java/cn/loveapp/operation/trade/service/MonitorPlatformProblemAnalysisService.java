package cn.loveapp.operation.trade.service;

import cn.loveapp.operation.trade.dto.request.UserProblemAnalysisRequest;
import cn.loveapp.operation.trade.dto.response.ProblemAnalysisResponse;

/**
 * @author xujianhu
 * @date 2023-05-03 16:19
 * @Description: 运维可视化问题分析服务接口
 */
public interface MonitorPlatformProblemAnalysisService {
    /**
     * 获取用户问题分析结果
     * @param request
     * @param problemAnalysisResponse
     */
    void getUserProblemAnalysisResult(UserProblemAnalysisRequest request,
                                      ProblemAnalysisResponse problemAnalysisResponse);

    /**
     * 获取订单问题分析结果
     * @param request
     * @param problemAnalysisResponse
     */
    void getOrderProblemAnalysisResult(UserProblemAnalysisRequest request,
                                       ProblemAnalysisResponse problemAnalysisResponse);
}

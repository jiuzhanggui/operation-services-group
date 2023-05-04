package com.ruoyi.web.controller.trade;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.operation.common.constant.ResponseConstant;
import com.ruoyi.operation.common.web.CommonApiResponse;

import cn.loveapp.operation.trade.dto.request.UserProblemAnalysisRequest;
import cn.loveapp.operation.trade.dto.response.ProblemAnalysisResponse;
import cn.loveapp.operation.trade.service.MonitorPlatformProblemAnalysisService;
import cn.loveapp.operation.trade.utils.RequestParamUtil;

/**
 * @author xujianhu
 * @date 2023-05-03 16:12
 * @Description: 问题分析控制器
 */
@RestController
@RequestMapping(value = "operations/analysis")
public class ProblemAnalysisController {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProblemAnalysisController.class);

    @Autowired
    private MonitorPlatformProblemAnalysisService monitorPlatformProblemAnalysisService;

    // @Autowired
    // private MonitorPlatformRequestParamProcessing monitorPlatformRequestParamProcessing;

    @RequestMapping(value = "/user.problem.analysis", method = RequestMethod.POST)
    public AjaxResult userProblemAnalysis(@RequestBody UserProblemAnalysisRequest request) {
        if (StringUtils.isEmpty(request.getSellerNick())) {
            return AjaxResult.warn(ResponseConstant.PARAMSE_RROR.getMessage());
        }

        RequestParamUtil.checkoutUserInfoRequestParams(request);
        // monitorPlatformRequestParamProcessing.examineParams(request);
        // 开始诊断分析用户信息
        ProblemAnalysisResponse problemAnalysisResponse = new ProblemAnalysisResponse();
        problemAnalysisResponse.setParams(new ArrayList<>());
        LOGGER.info(request.getSellerNick(), request.getTid(), "开始分析用户信息");

        monitorPlatformProblemAnalysisService.getUserProblemAnalysisResult(request, problemAnalysisResponse);
        return AjaxResult.success(problemAnalysisResponse);
    }

    @RequestMapping(value = "/order.problem.analysis", method = RequestMethod.POST)
    public CommonApiResponse<ProblemAnalysisResponse>
        orderProblemAnalysis(@RequestBody UserProblemAnalysisRequest request) {
        if (StringUtils.isAnyEmpty(request.getSellerNick(), request.getTid())) {
            return CommonApiResponse.failed(ResponseConstant.PARAMSE_RROR.getCode(),
                ResponseConstant.PARAMSE_RROR.getMessage());
        }

        // monitorPlatformRequestParamProcessing.examineParams(request);
        if (request.getSellerId() == null) {
            return CommonApiResponse.failed(ResponseConstant.USER_NOT_EXIST.getCode(),
                ResponseConstant.USER_NOT_EXIST.getMessage());
        }

        // 开始诊断分析订单信息
        ProblemAnalysisResponse problemAnalysisResponse = new ProblemAnalysisResponse();
        problemAnalysisResponse.setParams(new ArrayList<>());
        LOGGER.info(request.getSellerNick(), request.getTid(), "开始分析订单");

        monitorPlatformProblemAnalysisService.getOrderProblemAnalysisResult(request, problemAnalysisResponse);
        return CommonApiResponse.success(problemAnalysisResponse);
    }
}

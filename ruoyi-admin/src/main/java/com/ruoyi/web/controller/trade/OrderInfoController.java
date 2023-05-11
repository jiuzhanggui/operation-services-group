package com.ruoyi.web.controller.trade;

import com.ruoyi.operation.common.constant.CommonPlatformConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.operation.common.constant.ResponseConstant;
import com.ruoyi.operation.common.dto.request.GetOrderInfoRequest;
import com.ruoyi.operation.common.web.CommonApiResponse;

import cn.loveapp.operation.trade.dao.es.CommonAyTradeSearchESDao;
import cn.loveapp.operation.trade.dao.mongo.OrderRefundRepository;
import cn.loveapp.operation.trade.dao.mongo.OrderRepository;
import cn.loveapp.operation.trade.dao.mongo.SubOrderRepository;
import cn.loveapp.operation.trade.dto.request.UserProblemAnalysisRequest;

/**
 * @author xujianhu
 * @date 2023-04-24 17:39
 * @Description: 订单信息控制器
 */
@RestController
@RequestMapping(value = "operation/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SubOrderRepository subOrderRepository;

    @Autowired
    private OrderRefundRepository orderRefundRepository;

    @Autowired
    private CommonAyTradeSearchESDao commonAyTradeSearchESDao;

    @RequestMapping(value = "/getEsOrderInfo")
    public AjaxResult getEsOrderInfo(@RequestBody UserProblemAnalysisRequest request) {
        String tid = request.getTid();
        String platformId = request.getPlatformId();
        String appName = request.getAppName();
        if (StringUtils.isAnyEmpty(tid, platformId, appName)) {
            if (CommonPlatformConstants.PLATFORM_TAO.equals(platformId)) {
                appName = null;
            } else {
                return AjaxResult.warn(ResponseConstant.PARAMSE_RROR.getMessage());
            }
        }

        Object orderInfo = commonAyTradeSearchESDao.getByTid(tid, platformId, appName);

        return AjaxResult.success(orderInfo);
    }

    @RequestMapping(value = "/getOrderInfo")
    public AjaxResult getTcOrderInfo(@RequestBody GetOrderInfoRequest request) {

        if (StringUtils.isEmpty(request.getTid())) {
            return AjaxResult.warn(ResponseConstant.PARAMSE_RROR.getMessage());
        }

        String tid = request.getTid().trim();
        String platformId = request.getPlatformId();
        String appName = request.getAppName();
        return AjaxResult.success(orderRepository.queryAllByTid(tid, platformId, appName));
    }

    @RequestMapping(value = "/getSubOrderInfo")
    public AjaxResult getTcSubOrderInfo(@RequestBody GetOrderInfoRequest request) {

        if (StringUtils.isEmpty(request.getTid())) {
            return AjaxResult.warn(ResponseConstant.PARAMSE_RROR.getMessage());
        }

        String tid = request.getTid().trim();
        String platformId = request.getPlatformId();
        String appName = request.getAppName();

        return AjaxResult.success(subOrderRepository.queryAllByTid(tid, platformId, appName));
    }

    @RequestMapping(value = "/getRefundOrderInfo")
    public AjaxResult getRefundOrderInfo(@RequestBody GetOrderInfoRequest request) {

        if (StringUtils.isEmpty(request.getTid())) {
            return AjaxResult.warn(ResponseConstant.PARAMSE_RROR.getMessage());
        }

        String tid = request.getTid().trim();
        String platformId = request.getPlatformId();
        String appName = request.getAppName();
        return AjaxResult.success(orderRefundRepository.queryAllByTid(tid, platformId, appName));
    }
}

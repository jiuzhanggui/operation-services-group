package com.ruoyi.web.controller.trade;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.operation.common.constant.ResponseConstant;
import com.ruoyi.operation.common.dto.request.GetOrderInfoRequest;
import com.ruoyi.operation.common.web.CommonApiResponse;

import cn.loveapp.operation.trade.dao.mongo.OrderRefundRepository;
import cn.loveapp.operation.trade.dao.mongo.OrderRepository;
import cn.loveapp.operation.trade.dao.mongo.SubOrderRepository;

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

    @RequestMapping(value = "/getOrderInfo")
    public CommonApiResponse<Object> getTcOrderInfo(@RequestBody GetOrderInfoRequest request) {

        if (StringUtils.isEmpty(request.getTid())) {
            return CommonApiResponse.failed(ResponseConstant.PARAMSE_RROR.getCode(),
                ResponseConstant.PARAMSE_RROR.getMessage());
        }

        String tid = request.getTid().trim();
        String platformId = request.getPlatformId();
        String appName = request.getAppName();
        return CommonApiResponse.success(orderRepository.queryAllByTid(tid, platformId, appName));
    }

    @RequestMapping(value = "/getSubOrderInfo")
    public CommonApiResponse<Object> getTcSubOrderInfo(@RequestBody GetOrderInfoRequest request) {

        if (StringUtils.isEmpty(request.getTid())) {
            return CommonApiResponse.failed(ResponseConstant.PARAMSE_RROR.getCode(),
                ResponseConstant.PARAMSE_RROR.getMessage());
        }

        String tid = request.getTid().trim();
        String platformId = request.getPlatformId();
        String appName = request.getAppName();

        return CommonApiResponse.success(subOrderRepository.queryAllByTid(tid, platformId, appName));
    }

    @RequestMapping(value = "/getRefundOrderInfo")
    public CommonApiResponse<Object> getRefundOrderInfo(@RequestBody GetOrderInfoRequest request) {

        if (StringUtils.isEmpty(request.getTid())) {
            return CommonApiResponse.failed(ResponseConstant.PARAMSE_RROR.getCode(),
                ResponseConstant.PARAMSE_RROR.getMessage());
        }

        String tid = request.getTid().trim();
        String platformId = request.getPlatformId();
        String appName = request.getAppName();
        return CommonApiResponse.success(orderRefundRepository.queryAllByTid(tid, platformId, appName));
    }
}

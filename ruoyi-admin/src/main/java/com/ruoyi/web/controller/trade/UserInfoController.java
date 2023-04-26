package com.ruoyi.web.controller.trade;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.operation.common.constant.ResponseConstant;
import com.ruoyi.operation.common.dto.request.CommonRequest;
import com.ruoyi.operation.common.web.CommonApiResponse;

import cn.loveapp.operation.trade.dto.AyTradeOpenUserLogDTO;
import cn.loveapp.operation.trade.dto.OrderSearchTradeInfoDTO;
import cn.loveapp.operation.trade.dto.UserFullInfoDTO;
import cn.loveapp.operation.trade.dto.UserProductionInfoExtDTO;
import cn.loveapp.operation.trade.dto.request.GetUserProductInfoRequest;
import cn.loveapp.operation.trade.service.AyTradeOpenUserLogService;
import cn.loveapp.operation.trade.service.OrderSearchTradeService;
import cn.loveapp.operation.trade.service.UserInfoService;
import cn.loveapp.operation.trade.service.UserProductionInfoExtService;
import cn.loveapp.operation.trade.utils.RequestParamUtil;
import io.swagger.annotations.ApiOperation;

/**
 * @author xujianhu
 * @date 2023-03-14 21:11
 * @Description: 用户信息控制器
 */
@RestController
@RequestMapping("/operation/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private OrderSearchTradeService orderSearchTradeService;

    @Autowired
    private UserProductionInfoExtService userProductionInfoExtService;

    @Autowired
    private AyTradeOpenUserLogService ayTradeOpenUserLogService;

    @ApiOperation(value = "获取用户信息列表")
    @RequestMapping(value = "/userProductInfo.list.get")
    public CommonApiResponse<List<UserFullInfoDTO>> getUserProductInfo(@RequestBody GetUserProductInfoRequest request) {
        if (StringUtils.isEmpty(request.getSellerNick())) {
            return CommonApiResponse.failed(ResponseConstant.PARAMSE_RROR.getCode(),
                ResponseConstant.PARAMSE_RROR.getMessage());
        }
        // 处理请求参数(去空格)
        RequestParamUtil.checkoutUserInfoRequestParams(request);

        UserFullInfoDTO userFullInfoDto = userInfoService.getUserFullInfo(request.getSellerNick(), null,
            request.getPlatformId(), request.getAppName());
        List<UserFullInfoDTO> params = new ArrayList<>();

        // 如果没查到直接返回
        if (userFullInfoDto == null) {
            return CommonApiResponse.success(params);
        }

        String accessToken = userFullInfoDto.getAccessToken();
        String refreshToken = userFullInfoDto.getRefreshToken();

        userFullInfoDto.setAccessToken(StringUtils.isEmpty(accessToken) ? null : getEncryptedStr(accessToken));
        userFullInfoDto.setRefreshToken(StringUtils.isEmpty(refreshToken) ? null : getEncryptedStr(refreshToken));
        params.add(userFullInfoDto);
        return CommonApiResponse.success(params);
    }

    @ApiOperation(value = "获取订购记录列表")
    @RequestMapping("/orderSearchInfo.list.get")
    public CommonApiResponse<List<OrderSearchTradeInfoDTO>> getOrderSearchInfo(@RequestBody CommonRequest request) {
        if (StringUtils.isEmpty(request.getSellerNick())) {
            return CommonApiResponse.failed(ResponseConstant.PARAMSE_RROR.getCode(),
                ResponseConstant.PARAMSE_RROR.getMessage());
        }

        // 处理请求参数(去空格)
        RequestParamUtil.checkoutUserInfoRequestParams(request);

        List<OrderSearchTradeInfoDTO> orderSearchTradeInfoDTOList =
            orderSearchTradeService.getAllOrderSearchBySellerNick(request.getSellerNick(), request.getSellerId(),
                request.getPlatformId(), request.getAppName());

        return CommonApiResponse.success(orderSearchTradeInfoDTOList);
    }

    @ApiOperation(value = "获取订购记录列表")
    @RequestMapping("/extInfo.list.get")
    public CommonApiResponse<List<UserProductionInfoExtDTO>> getExtUserInfo(@RequestBody CommonRequest request) {
        if (StringUtils.isEmpty(request.getSellerNick())) {
            return CommonApiResponse.failed(ResponseConstant.PARAMSE_RROR.getCode(),
                ResponseConstant.PARAMSE_RROR.getMessage());
        }

        // 处理请求参数(去空格)
        RequestParamUtil.checkoutUserInfoRequestParams(request);
        String sellerNick = request.getSellerNick();
        String platformId = request.getPlatformId();

        return CommonApiResponse.success(userProductionInfoExtService.queryListBySellerNick(sellerNick, platformId));
    }

    @ApiOperation(value = "获取开户记录日志列表")
    @RequestMapping("/openUserInfoLog.list.get")
    public CommonApiResponse<List<AyTradeOpenUserLogDTO>> getUserOpenUserLogInfo(@RequestBody CommonRequest request) {

        if (StringUtils.isEmpty(request.getSellerNick())) {
            return CommonApiResponse.failed(ResponseConstant.PARAMSE_RROR.getCode(),
                ResponseConstant.PARAMSE_RROR.getMessage());
        }

        // 处理请求参数(去空格)
        RequestParamUtil.checkoutUserInfoRequestParams(request);
        String sellerNick = request.getSellerNick();

        return CommonApiResponse.success(ayTradeOpenUserLogService.getAllOpenUserLogBySellerNick(sellerNick));
    }

    /**
     * 对私密字符进行加密
     *
     * @param accessToken
     * @return
     */
    public String getEncryptedStr(String accessToken) {
        String prefixStr = accessToken.substring(0, 4);
        String suffixStr = accessToken.substring(accessToken.length() - 6);
        String substring = accessToken.substring(4, accessToken.length() - 6);
        String encryptedStr = substring.replaceAll(substring, "**********");
        return prefixStr + encryptedStr + suffixStr;
    }

}

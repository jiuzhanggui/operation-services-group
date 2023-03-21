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

import cn.loveapp.operation.trade.dto.OrderSearchTradeInfoDto;
import cn.loveapp.operation.trade.dto.UserFullInfoDto;
import cn.loveapp.operation.trade.dto.request.GetUserProductInfoRequest;
import cn.loveapp.operation.trade.service.OrderSearchTradeService;
import cn.loveapp.operation.trade.service.UserInfoService;
import cn.loveapp.operation.trade.utils.RequestParamUtil;

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

    @RequestMapping(value = "/userProductInfo.list.get")
    public CommonApiResponse<List<UserFullInfoDto>> getUserProductInfo(@RequestBody GetUserProductInfoRequest request) {
        if (StringUtils.isEmpty(request.getSellerNick())) {
            return CommonApiResponse.failed(ResponseConstant.PARAMSE_RROR.getCode(),
                ResponseConstant.PARAMSE_RROR.getMessage());
        }
        // 入参处理
        RequestParamUtil.checkoutUserInfoRequestParams(request);

        UserFullInfoDto userFullInfoDto = userInfoService.getUserFullInfo(request.getSellerNick(), null,
            request.getPlatformId(), request.getAppName());
        List<UserFullInfoDto> params = new ArrayList<>();

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

    @RequestMapping("/orderSearchInfo.list.get")
    public CommonApiResponse<List<OrderSearchTradeInfoDto>> getOrderSearchInfo(@RequestBody CommonRequest request) {
        if (StringUtils.isEmpty(request.getSellerNick())) {
            return CommonApiResponse.failed(ResponseConstant.PARAMSE_RROR.getCode(),
                ResponseConstant.PARAMSE_RROR.getMessage());
        }

        // 入参处理
        RequestParamUtil.checkoutUserInfoRequestParams(request);

        List<OrderSearchTradeInfoDto> orderSearchTradeInfoDtoList =
            orderSearchTradeService.getAllOrderSearchBySellerNick(request.getSellerNick(), request.getSellerId(),
                request.getPlatformId(), request.getAppName());

        return CommonApiResponse.success(orderSearchTradeInfoDtoList);
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

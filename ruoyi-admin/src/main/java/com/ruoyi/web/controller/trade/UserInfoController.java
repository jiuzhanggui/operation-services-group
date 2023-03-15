//package com.ruoyi.web.controller.trade;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cn.loveapp.operation.common.dto.UserFullInfoDto;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import cn.loveapp.common.web.CommonApiResponse;
//import cn.loveapp.operation.common.dto.CommonRequest;
//import cn.loveapp.operation.common.service.UserCenterService;
//import cn.loveapp.operations.trade.constant.ResponseConstant;
//import cn.loveapp.operations.trade.dto.GetUserProductInfoRequest;
//import cn.loveapp.uac.response.UserFullInfoResponse;
//
///**
// * @author xujianhu
// * @date 2023-03-14 21:11
// * @Description: 用户信息控制器
// */
//public class UserInfoController {
//
//    @Autowired
//    private UserCenterService userCenterService;
//
//    @RequestMapping(value = "/userProductInfo.list.get")
//    public CommonApiResponse<List<UserFullInfoDto>> getUserProductInfo(@RequestBody GetUserProductInfoRequest request) {
//        if (StringUtils.isEmpty(request.getSellerNick())) {
//            return CommonApiResponse.failed(ResponseConstant.PARAMSE_RROR.getCode(),
//                ResponseConstant.PARAMSE_RROR.getMessage());
//        }
//        // 入参处理
//        checkoutUserInfoRequestParams(request);
//
//        UserFullInfoResponse userFullInfo = userCenterService.getUserFullInfo(request.getSellerNick(), null,
//            request.getPlatformId(), request.getAppName());
//
//        String accessToken = userFullInfo.getAccessToken();
//        if (StringUtils.isNotEmpty(accessToken)) {
//            userFullInfo.setAccessToken(getEncryptedStr(accessToken));
//        }
//
//        String refreshToken = userFullInfo.getRefreshToken();
//        if (StringUtils.isNotEmpty(refreshToken)) {
//            userFullInfo.setRefreshToken(getEncryptedStr(refreshToken));
//        }
//
//        UserFullInfoDto userFullInfoDto = new UserFullInfoDto();
//        BeanUtils.copyProperties(userFullInfo, userFullInfoDto);
//
//        List<UserFullInfoDto> params = new ArrayList<>();
//        params.add(userFullInfoDto);
//        return CommonApiResponse.success(params);
//    }
//
//    /**
//     * 对私密字符进行加密
//     *
//     * @param accessToken
//     * @return
//     */
//    public String getEncryptedStr(String accessToken) {
//        String prefixStr = accessToken.substring(0, 4);
//        String suffixStr = accessToken.substring(accessToken.length() - 6);
//        String substring = accessToken.substring(4, accessToken.length() - 6);
//        String encryptedStr = substring.replaceAll(substring, "**********");
//        return prefixStr + encryptedStr + suffixStr;
//    }
//
//    /**
//     * 用户信息请求参数检查
//     *
//     * @param request
//     */
//    public void checkoutUserInfoRequestParams(CommonRequest request) {
//        request.setSellerNick(request.getSellerNick().trim());
//    }
//}

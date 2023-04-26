package cn.loveapp.operation.trade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ruoyi.operation.common.dto.request.UserFullInfoRequest;
import com.ruoyi.operation.common.utils.HttpUtil;

import cn.loveapp.operation.trade.config.RequestAddressConfig;
import cn.loveapp.operation.trade.dto.UserFullInfoDTO;
import cn.loveapp.operation.trade.service.UserInfoService;

/**
 * @author xujianhu
 * @date 2023-03-20 11:21
 * @Description: 用户信息服务接口实现
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private RequestAddressConfig requestAddressConfig;

    @Override
    public UserFullInfoDTO getUserFullInfo(String sellerNick, String sellerId, String platformId, String appName) {

        UserFullInfoRequest request = new UserFullInfoRequest();
        request.setSellerNick(sellerNick);
        request.setSellerId(sellerId);
        request.setPlatformId(platformId);
        request.setAppName(appName);

        String responseStr = HttpUtil.urlHttp(requestAddressConfig.getUacUrl(),
            requestAddressConfig.getUacGetUserFullInfoPath(), JSON.toJSONString(request));

        UserFullInfoDTO userFullInfoDto = JSON.parseObject(responseStr, UserFullInfoDTO.class);

        return userFullInfoDto;
    }
}

package cn.loveapp.operation.trade.service;

import cn.loveapp.operation.trade.dto.UserFullInfoDTO;

/**
 * @author xujianhu
 * @date 2023-03-20 11:19
 * @Description: 用户信息服务
 */
public interface UserInfoService {

    /**
     * 获取用户完整信息
     *
     * @param sellerNick
     * @param sellerId
     * @param platformId
     * @param appName
     * @return
     */
    UserFullInfoDTO getUserFullInfo(String sellerNick, String sellerId, String platformId, String appName);
}

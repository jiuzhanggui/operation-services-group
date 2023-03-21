package cn.loveapp.operation.trade.utils;

import com.ruoyi.operation.common.dto.request.CommonRequest;

/**
 * @author xujianhu
 * @date 2023-03-20 16:05
 * @Description: 请求参数处理工具类
 */
public class RequestParamUtil {

    /**
     * 用户信息请求参数检查
     *
     * @param request
     */
    public static void checkoutUserInfoRequestParams(CommonRequest request) {
        request.setSellerNick(request.getSellerNick().trim());
    }
}

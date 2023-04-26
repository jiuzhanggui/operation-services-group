package cn.loveapp.operation.trade.service;

import java.util.List;

import cn.loveapp.operation.trade.dto.UserProductionInfoExtDTO;
import cn.loveapp.operation.trade.entity.UserProductionInfoExt;

/**
 * @author xujianhu
 * @date 2023-04-24 17:10
 * @Description: 用户EXT信息服务接口
 */
public interface UserProductionInfoExtService {

    /**
     * 依据sellerNick获取用户数据记录
     *
     * @param sellerNick
     * @param platformId
     * @return
     */
    List<UserProductionInfoExtDTO> queryListBySellerNick(String sellerNick, String platformId);

}

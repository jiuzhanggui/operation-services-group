package cn.loveapp.operation.trade.dao.mysql;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.loveapp.operation.trade.entity.UserProductionInfoExt;

/**
 * @author xujianhu
 * @date 2023-04-24 17:13
 * @Description: 数据落库状态记录表数据库访问层
 */
public interface UserProductionInfoExtDao {
    List<UserProductionInfoExt> queryListBySellerNick(@Param("sellerNick") String sellerNick,
        @Param("storeId") String platformId);
}

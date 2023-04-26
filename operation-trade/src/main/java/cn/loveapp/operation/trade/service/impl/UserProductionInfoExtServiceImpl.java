package cn.loveapp.operation.trade.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;

import cn.loveapp.operation.trade.dao.mysql.UserProductionInfoExtDao;
import cn.loveapp.operation.trade.dto.UserProductionInfoExtDTO;
import cn.loveapp.operation.trade.entity.UserProductionInfoExt;
import cn.loveapp.operation.trade.service.UserProductionInfoExtService;

/**
 * @author xujianhu
 * @date 2023-04-24 17:12
 * @Description: 用户EXT信息服务接口实现类
 */
@DataSource(value = DataSourceType.DREAM_DATA_SOURCE)
@Service
public class UserProductionInfoExtServiceImpl implements UserProductionInfoExtService {

    @Autowired
    private UserProductionInfoExtDao userProductionInfoExtDao;

    @Override
    public List<UserProductionInfoExtDTO> queryListBySellerNick(String sellerNick, String platformId) {
        List<UserProductionInfoExt> userProductionInfoExts =
            userProductionInfoExtDao.queryListBySellerNick(sellerNick, platformId);

        ArrayList<UserProductionInfoExtDTO> userProductionInfoExtDTOList = new ArrayList<>();
        for (UserProductionInfoExt userProductionInfoExt : userProductionInfoExts) {
            UserProductionInfoExtDTO userProductionInfoExtDTO = new UserProductionInfoExtDTO();
            BeanUtils.copyProperties(userProductionInfoExt, userProductionInfoExtDTO);
            userProductionInfoExtDTOList.add(userProductionInfoExtDTO);
        }
        return userProductionInfoExtDTOList;
    }
}

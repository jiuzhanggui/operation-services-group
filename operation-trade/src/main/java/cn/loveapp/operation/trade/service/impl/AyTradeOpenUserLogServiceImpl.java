package cn.loveapp.operation.trade.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;

import cn.loveapp.operation.trade.dao.mysql.AyTradeOpenUserLogDao;
import cn.loveapp.operation.trade.dto.AyTradeOpenUserLogDTO;
import cn.loveapp.operation.trade.entity.AyTradeOpenUserLog;
import cn.loveapp.operation.trade.service.AyTradeOpenUserLogService;

/**
 * @author xujianhu
 * @date 2023-04-24 17:52
 * @Description: (ay_trade_open_user_log)开户记录日志表服务实现类
 */
@DataSource(value = DataSourceType.DREAM_DATA_SOURCE)
@Service
public class AyTradeOpenUserLogServiceImpl implements AyTradeOpenUserLogService {

    @Autowired
    private AyTradeOpenUserLogDao ayTradeOpenUserLogDao;

    @Override
    public List<AyTradeOpenUserLogDTO> getAllOpenUserLogBySellerNick(String sellerNick) {
        List<AyTradeOpenUserLog> ayTradeOpenUserLogs = ayTradeOpenUserLogDao.getAllBySellerNick(sellerNick);
        List<AyTradeOpenUserLogDTO> ayTradeOpenUserLogDTOList = new ArrayList<>();
        for (AyTradeOpenUserLog ayTradeOpenUserLog : ayTradeOpenUserLogs) {
            AyTradeOpenUserLogDTO ayTradeOpenUserLogDTO = new AyTradeOpenUserLogDTO();
            BeanUtils.copyProperties(ayTradeOpenUserLog, ayTradeOpenUserLogDTO);
            ayTradeOpenUserLogDTOList.add(ayTradeOpenUserLogDTO);

        }
        return ayTradeOpenUserLogDTOList;
    }
}

package cn.loveapp.operation.trade.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.loveapp.operation.trade.constant.LogEsIndexConstant;
import cn.loveapp.operation.trade.constant.ParamStatusTagConstant;
import cn.loveapp.operation.trade.dao.es.MonitorAyLogSearchESDao;
import cn.loveapp.operation.trade.dto.AyUserProblemQueryChainDTO;
import cn.loveapp.operation.trade.dto.request.AyUserProblemQueryChainRequest;
import cn.loveapp.operation.trade.dto.response.ProblemAnalysisResponse;
import cn.loveapp.operation.trade.service.AyUserProblemQueryService;

/**
 * @author xujianhu
 * @date 2023-05-03 16:26
 * @Description: 爱用NewUser-service日志查询服务接口
 */
@Service
public class AyNewUserServiceLogQueryServiceImpl implements AyUserProblemQueryService {
    public static final Logger LOGGER = LoggerFactory.getLogger(AyNewUserServiceLogQueryServiceImpl.class);

    /**
     * NewUser-Service 异常日志字段
     */
    public static final String RDS_SUB_ERR = "该子帐号无此操作权限";

    @Autowired
    private MonitorAyLogSearchESDao monitorAyLogSearchESDao;

    @Override
    public AyUserProblemQueryChainRequest logQueryDispose(AyUserProblemQueryChainDTO ayUserProblemQueryChainDTO) {
        AyUserProblemQueryChainRequest request = ayUserProblemQueryChainDTO.ayUserProblemQueryChainRequest();
        String sellerNick = request.getSellerNick();
        ProblemAnalysisResponse problemAnalysisResponse = request.getProblemAnalysisResponse();
        ProblemAnalysisResponse.Param param;
        List<String> messageParts = new ArrayList<>();
        messageParts.add(sellerNick);
        messageParts.add(RDS_SUB_ERR);
        List<String> newUserSchedulerMessage = monitorAyLogSearchESDao.getLogMessageList(sellerNick, messageParts,
            LogEsIndexConstant.ORDER_NEWUSER_SERVICE_INDEX_PREFIX);
        if (newUserSchedulerMessage.size() > 0) {
            param = new ProblemAnalysisResponse.Param("NewUser-service日志", "子账号无此权限，需要主账号开通权限",
                ParamStatusTagConstant.ERRORTAG);
            problemAnalysisResponse.getParams().add(param);
            return request;
        }
        return ayUserProblemQueryChainDTO.proceed(request);
    }

}

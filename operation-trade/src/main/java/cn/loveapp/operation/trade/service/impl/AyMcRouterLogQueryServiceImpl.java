package cn.loveapp.operation.trade.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.loveapp.operation.trade.constant.LogEsIndexConstant;
import cn.loveapp.operation.trade.constant.ParamStatusTagConstant;
import cn.loveapp.operation.trade.dao.es.MonitorAyLogSearchESDao;
import cn.loveapp.operation.trade.dto.AyUserProblemQueryChainDTO;
import cn.loveapp.operation.trade.dto.request.AyUserProblemQueryChainRequest;
import cn.loveapp.operation.trade.dto.response.ProblemAnalysisResponse;
import cn.loveapp.operation.trade.service.AyUserProblemQueryService;

/**
 * @author xujianhu
 * @date 2023-05-03 17:20
 * @Description: 爱用MC-Router日志查询服务接口
 */
@Service
public class AyMcRouterLogQueryServiceImpl implements AyUserProblemQueryService {
    public static final Logger LOGGER = LoggerFactory.getLogger(AyMcRouterLogQueryServiceImpl.class);

    @Autowired
    private MonitorAyLogSearchESDao monitorAyLogSearchESDao;

    @Override
    public AyUserProblemQueryChainRequest logQueryDispose(AyUserProblemQueryChainDTO ayUserProblemQueryChainDTO) {
        AyUserProblemQueryChainRequest request = ayUserProblemQueryChainDTO.ayUserProblemQueryChainRequest();

        String sellerNick = request.getSellerNick();
        String tid = request.getTid();
        String storeId = request.getStoreId();
        Date searchLogStartTime = request.getSearchLogStartTime();
        String platformStatus = request.getPlatformStatus();
        ProblemAnalysisResponse problemAnalysisResponse = request.getProblemAnalysisResponse();
        ProblemAnalysisResponse.Param param;

        // 查询mc-router推送日志
        LOGGER.info(sellerNick, tid, "开始查询MC-Router日志");

        List<String> messageParts = new ArrayList<>();
        messageParts.add(sellerNick);
        messageParts.add(tid);
        messageParts.add(platformStatus);

        List<String> routerMessages = monitorAyLogSearchESDao.getLogMessageList(sellerNick, messageParts,
            searchLogStartTime, LogEsIndexConstant.getMcRouterTaoIndexPrefix(storeId));

        LOGGER.info(sellerNick, tid, "MC-Router中日志查询结果为: " + JSON.toJSONString(routerMessages));
        if (routerMessages.size() > 0) {
            param = new ProblemAnalysisResponse.Param("MC-Router日志", "收到状态为: " + platformStatus + "的消息推送",
                ParamStatusTagConstant.CORRECTTAG);
            problemAnalysisResponse.getParams().add(param);
        } else {
            param = new ProblemAnalysisResponse.Param("MC-Router日志", "没有收到状态为: " + platformStatus + "的消息推送",
                ParamStatusTagConstant.ERRORTAG);
            problemAnalysisResponse.getParams().add(param);
        }

        return ayUserProblemQueryChainDTO.proceed(request);
    }
}

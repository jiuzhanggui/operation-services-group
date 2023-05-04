package cn.loveapp.operation.trade.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ruoyi.operation.common.utils.DateUtil;

import cn.loveapp.operation.trade.config.MonitorPlatformBaseConfig;
import cn.loveapp.operation.trade.constant.LogEsIndexConstant;
import cn.loveapp.operation.trade.constant.ParamStatusTagConstant;
import cn.loveapp.operation.trade.dao.es.MonitorAyLogSearchESDao;
import cn.loveapp.operation.trade.dto.AyUserProblemQueryChainDTO;
import cn.loveapp.operation.trade.dto.request.AyUserProblemQueryChainRequest;
import cn.loveapp.operation.trade.dto.response.ProblemAnalysisResponse;
import cn.loveapp.operation.trade.service.AyUserProblemQueryService;

/**
 * @author xujianhu
 * @date 2023-05-03 16:36
 * @Description: 爱用SoldGet日志查询服务接口
 */
@Service
public class AySoldGetLogQueryServiceImpl implements AyUserProblemQueryService {
    public static final Logger LOGGER = LoggerFactory.getLogger(AySoldGetLogQueryServiceImpl.class);

    /**
     * 拉单中断，session失效标识
     */
    public static final String SOLD_GET_SESSION_ERR = "soldGet 失败, 结束拉单: topsession失效";

    /**
     * 拉单成功标识
     */
    public static final String SOLD_GET_SUCCESS = "结束拉取sold.get订单";

    @Autowired
    private MonitorPlatformBaseConfig monitorPlatformBaseConfig;

    @Autowired
    private MonitorAyLogSearchESDao monitorAyLogSearchESDao;

    @Override
    public AyUserProblemQueryChainRequest logQueryDispose(AyUserProblemQueryChainDTO ayUserProblemQueryChainDTO) {
        AyUserProblemQueryChainRequest request = ayUserProblemQueryChainDTO.ayUserProblemQueryChainRequest();
        String sellerNick = request.getSellerNick();
        String storeId = request.getStoreId();
        LocalDateTime pullStartDateTime = request.getPullStartDateTime();
        ProblemAnalysisResponse problemAnalysisResponse = request.getProblemAnalysisResponse();
        ProblemAnalysisResponse.Param param;

        // 设定日志查询开始时间(确保能够查询到导致拉单失败的原因)
        LocalDateTime searchLogStatTime = pullStartDateTime.minusMinutes(2);

        if (pullStartDateTime.plusDays(monitorPlatformBaseConfig.getLogSaveDays()).isAfter(LocalDateTime.now())) {
            LOGGER.info(sellerNick, null, "开始查询SoldGet日志");

            List<String> messageParts = new ArrayList<>();
            messageParts.add(SOLD_GET_SUCCESS);

            List<String> soldGetEndMessage = monitorAyLogSearchESDao.getLogMessageList(sellerNick, messageParts,
                DateUtil.convertLocalDateTimetoDate(searchLogStatTime),
                LogEsIndexConstant.getSoldGetIndexPrefix(storeId));
            LOGGER.info(sellerNick, null, "SoldGet日志查询结果为: " + JSON.toJSONString(soldGetEndMessage));
            if (soldGetEndMessage.size() > 0) {
                param = new ProblemAnalysisResponse.Param("SoldGet日志", "成功拉取订单", ParamStatusTagConstant.CORRECTTAG);
                problemAnalysisResponse.getParams().add(param);
            } else {
                param = new ProblemAnalysisResponse.Param("SoldGet日志", "拉单没有正常结束", ParamStatusTagConstant.ERRORTAG);
                problemAnalysisResponse.getParams().add(param);

                messageParts = new ArrayList<>();
                messageParts.add(SOLD_GET_SESSION_ERR);

                List<String> soldGetTopSessionMessage = monitorAyLogSearchESDao.getLogMessageList(sellerNick,
                    messageParts, DateUtil.convertLocalDateTimetoDate(searchLogStatTime),
                    LogEsIndexConstant.getSoldGetIndexPrefix(storeId));
                if (soldGetTopSessionMessage.size() > 0) {
                    param =
                        new ProblemAnalysisResponse.Param("SoldGet日志", "TopSession失效", ParamStatusTagConstant.ERRORTAG);
                    problemAnalysisResponse.getParams().add(param);
                }
            }
        } else {
            param = new ProblemAnalysisResponse.Param("SoldGet日志", "已超过最大日志保存天数", ParamStatusTagConstant.ERRORTAG);
            problemAnalysisResponse.getParams().add(param);
        }

        return ayUserProblemQueryChainDTO.proceed(request);
    }

}

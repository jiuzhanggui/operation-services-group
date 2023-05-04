package cn.loveapp.operation.trade.service.impl;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.loveapp.operation.trade.constant.ParamStatusTagConstant;
import cn.loveapp.operation.trade.dto.AyUserProblemQueryChainDTO;
import cn.loveapp.operation.trade.dto.UserProductionInfoExtDTO;
import cn.loveapp.operation.trade.dto.request.AyUserProblemQueryChainRequest;
import cn.loveapp.operation.trade.dto.response.ProblemAnalysisResponse;
import cn.loveapp.operation.trade.entity.UserProductionInfoExt;
import cn.loveapp.operation.trade.service.AyUserProblemQueryService;
import cn.loveapp.operation.trade.service.UserProductionInfoExtService;

/**
 * @author xujianhu
 * @date 2023-05-03 16:23
 * @Description: 爱用用户表问题查询服务实现类
 */
@Service
public class AyUserQueryServiceImpl implements AyUserProblemQueryService {

    public static final Logger LOGGER = LoggerFactory.getLogger(AyUserQueryServiceImpl.class);

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private UserProductionInfoExtService userProductionInfoExtService;

    // @Autowired
    // private MessageApiPlatformHandleService messageCenterService;

    @Override
    public AyUserProblemQueryChainRequest logQueryDispose(AyUserProblemQueryChainDTO ayUserProblemQueryChainDTO) {
        AyUserProblemQueryChainRequest request = ayUserProblemQueryChainDTO.ayUserProblemQueryChainRequest();
        String sellerNick = request.getSellerNick();
        String storeId = request.getStoreId();
        String appName = request.getAppName();
        String sellerId = request.getSellerId();
        ProblemAnalysisResponse problemAnalysisResponse = request.getProblemAnalysisResponse();
        // 查询Ext表进行参数状态判断
        List<UserProductionInfoExtDTO> extList =
            userProductionInfoExtService.queryListBySellerNick(sellerNick, storeId);
        ProblemAnalysisResponse.Param param;
        if (extList.size() == 0) {
            param = new ProblemAnalysisResponse.Param("Ext表", "用户数据不存在", ParamStatusTagConstant.ERRORTAG);
            problemAnalysisResponse.getParams().add(param);
            return request;
        } else if (extList.size() > 1) {
            param = new ProblemAnalysisResponse.Param("Ext表", "数据异常,拥有多条记录", ParamStatusTagConstant.ERRORTAG);
            problemAnalysisResponse.getParams().add(param);
            return request;
        } else {
            // 信息存在
            param = new ProblemAnalysisResponse.Param("Ext表", "数据正常", ParamStatusTagConstant.CORRECTTAG);
            problemAnalysisResponse.getParams().add(param);
            UserProductionInfoExtDTO extInfo = extList.get(0);
            // 检查拉单状态
            Integer pullStatus = extInfo.getPullStatus();
            if (UserProductionInfoExt.DB_DONE.equals(pullStatus)) {
                param =
                    new ProblemAnalysisResponse.Param("拉单状态", "状态正常: " + pullStatus, ParamStatusTagConstant.CORRECTTAG);
                problemAnalysisResponse.getParams().add(param);
            } else if (UserProductionInfoExt.DB_DOING.equals(pullStatus)) {
                param = new ProblemAnalysisResponse.Param("拉单状态", "状态正常,正在拉单: " + pullStatus,
                    ParamStatusTagConstant.CORRECTTAG);
                problemAnalysisResponse.getParams().add(param);
            } else {
                param =
                    new ProblemAnalysisResponse.Param("拉单状态", "状态异常: " + pullStatus, ParamStatusTagConstant.ERRORTAG);
                problemAnalysisResponse.getParams().add(param);
            }

            if (extInfo.getPullStartDateTime() == null) {
                param =
                    new ProblemAnalysisResponse.Param("拉单开始时间", "状态异常，拉单开始时间为Null", ParamStatusTagConstant.ERRORTAG);
                problemAnalysisResponse.getParams().add(param);
                param =
                    new ProblemAnalysisResponse.Param("拉单结束时间", "状态异常，拉单结束时间为Null", ParamStatusTagConstant.ERRORTAG);
                problemAnalysisResponse.getParams().add(param);
                return request;
            } else {
                param = new ProblemAnalysisResponse.Param("拉单开始时间", formatter.format(extInfo.getPullStartDateTime()),
                    ParamStatusTagConstant.CORRECTTAG);
                problemAnalysisResponse.getParams().add(param);
                param = new ProblemAnalysisResponse.Param("拉单结束时间", formatter.format(extInfo.getPullEndDateTime()),
                    ParamStatusTagConstant.CORRECTTAG);
                problemAnalysisResponse.getParams().add(param);
                request.setPullStartDateTime(extInfo.getPullStartDateTime());
            }

            // MessageRequest messageRequest = new MessageRequest();
            // request.setSellerId(sellerId);
            // request.setSellerNick(sellerNick);
            //
            // try {
            // boolean tmc = messageCenterService.isSubscribeUserMessageService(messageRequest, storeId, appName);
            // boolean rds = messageCenterService.isSubscribeUserDbService(messageRequest, storeId, appName);
            // if (tmc) {
            // param = new ProblemAnalysisResponse.Param("TMC", "开通成功", ParamStatusTagConstant.CORRECTTAG);
            // problemAnalysisResponse.getParams().add(param);
            // } else {
            // param = new ProblemAnalysisResponse.Param("TMC", "开通失败", ParamStatusTagConstant.ERRORTAG);
            // problemAnalysisResponse.getParams().add(param);
            // }
            //
            // if (rds) {
            // param = new ProblemAnalysisResponse.Param("RDS", "开通成功", ParamStatusTagConstant.CORRECTTAG);
            // problemAnalysisResponse.getParams().add(param);
            // } else {
            // param = new ProblemAnalysisResponse.Param("RDS", "开通失败", ParamStatusTagConstant.ERRORTAG);
            // problemAnalysisResponse.getParams().add(param);
            // }
            // } catch (Exception e) {
            // LOGGER.logError(sellerNick, null, "获取推送状态失败" + e.getMessage());
            // }
        }
        return ayUserProblemQueryChainDTO.proceed(request);
    }
}

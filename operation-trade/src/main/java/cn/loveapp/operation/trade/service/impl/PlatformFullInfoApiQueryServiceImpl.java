//package cn.loveapp.operation.trade.service.impl;
//
//import cn.loveapp.operation.trade.config.MonitorPlatformBaseConfig;
//import cn.loveapp.operation.trade.constant.ParamStatusTagConstant;
//import cn.loveapp.operation.trade.dto.AyUserProblemQueryChainDTO;
//import cn.loveapp.operation.trade.dto.request.AyUserProblemQueryChainRequest;
//import cn.loveapp.operation.trade.dto.response.ProblemAnalysisResponse;
//import cn.loveapp.operation.trade.service.AyUserProblemQueryService;
//import com.alibaba.fastjson.JSON;
//import com.ruoyi.operation.common.utils.DateUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
///**
// * @author xujianhu
// * @date 2023-05-03 17:15
// * @Description: 平台FullInfoAPI查询服务接口实现类
// */
//public class PlatformFullInfoApiQueryServiceImpl implements AyUserProblemQueryService {
//    public static final LoggerHelper LOGGER = LoggerHelper.getLogger(PlatformFullInfoApiQueryServiceImpl.class);
//
//    /**
//     * 状态标识-不是退款订单
//     */
//    public static final String NO_REFUND_ORDER = "NO_REFUND";
//
//    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//    @Autowired
//    private TradeApiPlatformHandleService tradeApiPlatformHandleService;
//
//    @Autowired
//    private TaobaoFullInfoAppConfig taobaoFullInfoAppConfig;
//
//    @Autowired
//    private MonitorPlatformBaseConfig monitorPlatformBaseConfig;
//
//    @Override
//    public AyUserProblemQueryChainRequest logQueryDispose(AyUserProblemQueryChainDTO ayUserProblemQueryChainDTO) {
//        AyUserProblemQueryChainRequest request = ayUserProblemQueryChainDTO.ayUserProblemQueryChainRequest();
//        String sellerNick = request.getSellerNick();
//        String tid = request.getTid();
//        String storeId = request.getStoreId();
//        String appName = request.getAppName();
//        String topSession = request.getTopSession();
//        ProblemAnalysisResponse problemAnalysisResponse = request.getProblemAnalysisResponse();
//        FullInfoRequest fullInfoRequest = new FullInfoRequest();
//        fullInfoRequest.setTid(tid);
//        fullInfoRequest.setSellerNick(sellerNick);
//        fullInfoRequest.setTopSession(topSession);
//        if (CommonPlatformConstants.PLATFORM_TAO.equals(storeId)) {
//            fullInfoRequest.setApiFields(taobaoFullInfoAppConfig.getFileds());
//        }
//        FullinfoResponse fullinfoResponse = null;
//        try {
//            fullinfoResponse = tradeApiPlatformHandleService.fullInfo(fullInfoRequest, storeId, appName);
//            LOGGER.logInfo(sellerNick, tid, "fullinfo请求返回体为: " + JSON.toJSON(fullinfoResponse.getTrade().getOrders()));
//        } catch (Exception e) {
//            LOGGER.logError(sellerNick, tid, "调取淘宝fullinfo失败: " + e.getMessage());
//        }
//        if (fullinfoResponse == null || fullinfoResponse.getTrade() == null) {
//            ProblemAnalysisResponse.Param param =
//                    new ProblemAnalysisResponse.Param("平台fullInfo订单信息", "订单信息不存在", ParamStatusTagConstant.ERRORTAG);
//            problemAnalysisResponse.getParams().add(param);
//            return request;
//        }
//
//        Date created = fullinfoResponse.getTrade().getCreated();
//        // 处理日志查询的开始时间，保证日志不丢失
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(created);
//        cal.add(Calendar.MINUTE, -monitorPlatformBaseConfig.getLogSaveDays().intValue());
//        Date logExpireTime = cal.getTime();
//        if (created.after(logExpireTime)) {
//            request.setSearchLogStartTime(created);
//        } else {
//            request.setSearchLogStartTime(logExpireTime);
//        }
//
//        String platformStatus = fullinfoResponse.getTrade().getStatus();
//        request.setPlatformStatus(platformStatus);
//
//        ProblemAnalysisResponse.Param param;
//        LocalDateTime orderCreated = DateUtil.parseDate(created).plusDays(90);
//
//        if (orderCreated.isBefore(LocalDateTime.now())) {
//            param = new ProblemAnalysisResponse.Param("订单创建时间",
//                    " 3个月以前订单,创建时间为: " + formatter.format(DateUtil.parseDate(created)), ParamStatusTagConstant.ERRORTAG);
//            problemAnalysisResponse.getParams().add(param);
//        } else {
//            param = new ProblemAnalysisResponse.Param("订单创建时间",
//                    " 3个月内订单,创建时间为: " + formatter.format(DateUtil.parseDate(created)), ParamStatusTagConstant.CORRECTTAG);
//            problemAnalysisResponse.getParams().add(param);
//        }
//
//        if (!request.getIsRefundOrder()) {
//            param = new ProblemAnalysisResponse.Param("订单类型", "非退款订单", ParamStatusTagConstant.CORRECTTAG);
//            problemAnalysisResponse.getParams().add(param);
//            // 不是退款订单
//            if (platformStatus.equals(request.getNowStatus())) {
//                param = new ProblemAnalysisResponse.Param("订单状态",
//                        "状态正常,now: " + request.getNowStatus() + " platform: " + platformStatus,
//                        ParamStatusTagConstant.CORRECTTAG);
//                problemAnalysisResponse.getParams().add(param);
//            } else {
//                param = new ProblemAnalysisResponse.Param("订单状态",
//                        "状态异常,now: " + request.getNowStatus() + " platform: " + platformStatus,
//                        ParamStatusTagConstant.ERRORTAG);
//                problemAnalysisResponse.getParams().add(param);
//            }
//
//        } else {
//            param = new ProblemAnalysisResponse.Param("订单类型", "退款订单", ParamStatusTagConstant.CORRECTTAG);
//            problemAnalysisResponse.getParams().add(param);
//            List<Order> orders = fullinfoResponse.getTrade().getOrders();
//            Map<String, String> nowRefundStatusMap = request.getNowRefundStatusMap();
//            ArrayList<String> refundOrderList = new ArrayList<>();
//            boolean isRefundStatusException = false;
//            for (Order order : orders) {
//                String nowRefundStatus = nowRefundStatusMap.get(order.getOidStr());
//                if (!nowRefundStatus.equals(order.getRefundStatus())) {
//                    refundOrderList.add(order.getOidStr());
//                }
//
//                if (refundOrderList.size() > 0) {
//                    isRefundStatusException = true;
//                }
//
//            }
//
//            if (isRefundStatusException) {
//                param = new ProblemAnalysisResponse.Param("退款状态", "状态异常,异常的子单为: " + JSON.toJSONString(refundOrderList),
//                        ParamStatusTagConstant.ERRORTAG);
//                problemAnalysisResponse.getParams().add(param);
//
//            } else {
//                param = new ProblemAnalysisResponse.Param("退款状态", "状态正常", ParamStatusTagConstant.CORRECTTAG);
//                problemAnalysisResponse.getParams().add(param);
//            }
//            return request;
//        }
//
//        return ayUserProblemQueryChainDTO.proceed(request);
//    }
//}

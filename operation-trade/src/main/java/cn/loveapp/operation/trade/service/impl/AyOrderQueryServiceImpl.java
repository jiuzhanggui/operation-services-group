//package cn.loveapp.operation.trade.service.impl;
//
//import java.util.HashMap;
//import java.util.List;
//
//import cn.loveapp.operation.trade.dao.mongo.SubOrderRepository;
//import cn.loveapp.operation.trade.dto.response.ProblemAnalysisResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import cn.loveapp.operation.trade.dto.AyUserProblemQueryChainDTO;
//import cn.loveapp.operation.trade.dto.request.AyUserProblemQueryChainRequest;
//import cn.loveapp.operation.trade.service.AyUserProblemQueryService;
//
///**
// * @author xujianhu
// * @date 2023-05-03 16:44
// * @Description: 用订单表问题查询服务实现类
// */
//@Service
//public class AyOrderQueryServiceImpl implements AyUserProblemQueryService {
//    public static final Logger LOGGER = LoggerFactory.getLogger(AyUserProblemQueryService.class);
//
//    /**
//     * 状态标识-不是退款订单
//     */
//    public static final String NO_REFUND_ORDER = "NO_REFUND";
//    @Autowired
//    private MonitorAyTradeSearchESDao commonAyTradeSearchESDao;
//
//    @Autowired
//    private SubOrderRepository subOrderRepository;
//
//    @Override
//    public AyUserProblemQueryChainRequest logQueryDispose(AyUserProblemQueryChainDTO ayUserProblemQueryChainDTO) {
//        AyUserProblemQueryChainRequest request = ayUserProblemQueryChainDTO.ayUserProblemQueryChainRequest();
//        String sellerNick = request.getSellerNick();
//        String sellerId = request.getSellerId();
//        String tid = request.getTid();
//        String storeId = request.getStoreId();
//        String appName = request.getAppName();
//        request.setNowRefundStatusMap(new HashMap<>());
//        ProblemAnalysisResponse problemAnalysisResponse = request.getProblemAnalysisResponse();
//        ProblemAnalysisResponse.Param param;
//
//        LOGGER.info(sellerNick, tid, "开始分析ES存单");
//        AyTradeSearchES ayTradeSearchEs = AyTradeSearchES.of(sellerId, null, storeId, appName, tid);
//        ayTradeSearchEs = commonAyTradeSearchESDao.getById(ayTradeSearchEs);
//
//        Object subOrderObject = subOrderRepository.queryAllByTid(
//                request.getTid(), request.getStoreId(), request.getAppName());
//
//        if (ayTradeSearchEs == null) {
//            LOGGER.info(sellerNick, tid, "ES数据库中没有订单信息");
//            param = new ProblemAnalysisResponse.Param("ES数据库", "订单信息不存在", ParamStatusTagConstant.ERRORTAG);
//            problemAnalysisResponse.getParams().add(param);
//            request.setIsRefundOrder(false);
//        } else {
//            request.setNowStatus(ayTradeSearchEs.getTaoStatus());
//            if (ayTradeSearchEs.getIsRefund()) {
//                HashMap<String, String> nowRefundStatusMap = new HashMap<>();
//                request.setIsRefundOrder(true);
//                for (TcSubOrder tcSubOrder : tcSubOrders) {
//                    nowRefundStatusMap.put(tcSubOrder.getOid(), tcSubOrder.getRefundStatus());
//                    request.setNowRefundStatusMap(nowRefundStatusMap);
//                }
//            } else {
//                request.setIsRefundOrder(false);
//            }
//        }
//        return ayUserProblemQueryChainDTO.proceed(request);
//    }
//}

package cn.loveapp.operation.trade.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cn.loveapp.operation.trade.dto.UserFullInfoDTO;
import cn.loveapp.operation.trade.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.loveapp.operation.trade.dto.request.AyUserProblemQueryChainRequest;
import cn.loveapp.operation.trade.dto.request.UserProblemAnalysisRequest;
import cn.loveapp.operation.trade.dto.response.ProblemAnalysisResponse;
import cn.loveapp.operation.trade.service.MonitorPlatformProblemAnalysisService;

/**
 * @author xujianhu
 * @date 2023-05-03 16:19
 * @Description: 运维可视化问题分析服务接口实现类
 */
@Service
public class MonitorPlatformProblemAnalysisServiceImpl implements MonitorPlatformProblemAnalysisService {

    public static final Logger LOGGER = LoggerFactory.getLogger(MonitorPlatformProblemAnalysisServiceImpl.class);

    @Autowired
    private AyUserQueryServiceImpl ayUserQueryService;

    @Autowired
    private AyNewUserServiceLogQueryServiceImpl ayNewUserServiceLogQueryService;

    @Autowired
    private AySoldGetLogQueryServiceImpl aySoldGetLogQueryService;

    @Autowired
    private AyUserProblemQueryChainServiceImpl ayUserProblemQueryChainService;

//    @Autowired
//    private AyOrderQueryServiceImpl ayOrderQueryService;
//
//    @Autowired
//    private PlatformFullInfoApiQueryServiceImpl platformFullInfoApiQueryService;

    @Autowired
    private AyTmcSubLogQueryServiceImpl ayTmcSubLogQueryService;

    @Autowired
    private AyRdsLogQueryServiceImpl rdsLogQueryService;

    @Autowired
    private AyMcRouterLogQueryServiceImpl mcRouterLogQueryService;

    @Autowired
    private AyMcFullInfoLogQueryServiceImpl fullInfoLogQueryService;

    @Autowired
    private UserInfoService userService;

    @Override
    public void getUserProblemAnalysisResult(UserProblemAnalysisRequest request,
        ProblemAnalysisResponse problemAnalysisResponse) {

        AyUserProblemQueryChainRequest ayUserProblemQueryChainRequest = new AyUserProblemQueryChainRequest();
        ayUserProblemQueryChainRequest.setSellerNick(request.getSellerNick());
        ayUserProblemQueryChainRequest.setAppName(request.getAppName());
        ayUserProblemQueryChainRequest.setStoreId(request.getPlatformId());
        ayUserProblemQueryChainRequest.setProblemAnalysisResponse(problemAnalysisResponse);

        ayUserProblemQueryChainService.addProblemQueryChain(ayUserQueryService);
        ayUserProblemQueryChainService.addProblemQueryChain(ayNewUserServiceLogQueryService);
        ayUserProblemQueryChainService.addProblemQueryChain(aySoldGetLogQueryService);
        ayUserProblemQueryChainService.execute(ayUserProblemQueryChainRequest);

        // 排序，将异常的参数放在集合前面
        paramsSortByParamTag(problemAnalysisResponse);

    }

    @Override
    public void getOrderProblemAnalysisResult(UserProblemAnalysisRequest request,
        ProblemAnalysisResponse problemAnalysisResponse) {
        UserFullInfoDTO userFullInfo = userService.getUserFullInfo(request.getSellerNick(), request.getSellerId(),
                request.getPlatformId(), request.getAppName());

        AyUserProblemQueryChainRequest ayUserProblemQueryChainRequest = new AyUserProblemQueryChainRequest();
        ayUserProblemQueryChainRequest.setSellerNick(request.getSellerNick());
        ayUserProblemQueryChainRequest.setTid(request.getTid());
        ayUserProblemQueryChainRequest.setSellerId(request.getSellerId());
        ayUserProblemQueryChainRequest.setAppName(request.getAppName());
        ayUserProblemQueryChainRequest.setStoreId(request.getPlatformId());
        ayUserProblemQueryChainRequest.setProblemAnalysisResponse(problemAnalysisResponse);
        ayUserProblemQueryChainRequest.setTopSession(userFullInfo.getAccessToken());

//        ayUserProblemQueryChainService.addProblemQueryChain(ayOrderQueryService);
//        ayUserProblemQueryChainService.addProblemQueryChain(platformFullInfoApiQueryService);
        ayUserProblemQueryChainService.addProblemQueryChain(ayTmcSubLogQueryService);
        ayUserProblemQueryChainService.addProblemQueryChain(rdsLogQueryService);
        ayUserProblemQueryChainService.addProblemQueryChain(mcRouterLogQueryService);
        ayUserProblemQueryChainService.addProblemQueryChain(fullInfoLogQueryService);
        ayUserProblemQueryChainService.execute(ayUserProblemQueryChainRequest);

        // 排序
        paramsSortByParamTag(problemAnalysisResponse);
    }

    /**
     * 对问题结果进行排序优先显示错误结果)
     *
     * @param problemAnalysisResponse
     */
    private void paramsSortByParamTag(ProblemAnalysisResponse problemAnalysisResponse) {
        List<ProblemAnalysisResponse.Param> params = problemAnalysisResponse.getParams();
        ArrayList<ProblemAnalysisResponse.Param> collect =
            params.stream().sorted(Comparator.comparing(ProblemAnalysisResponse.Param::getParamStatusTab))
                .collect(Collectors.toCollection(ArrayList::new));
        problemAnalysisResponse.setParams(collect);
    }
}

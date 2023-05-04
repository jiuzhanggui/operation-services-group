package cn.loveapp.operation.trade.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.loveapp.operation.trade.dto.AyUserProblemQueryChainDTO;
import cn.loveapp.operation.trade.dto.request.AyUserProblemQueryChainRequest;
import cn.loveapp.operation.trade.service.AyUserProblemQueryService;

/**
 * @author xujianhu
 * @date 2023-05-03 16:42
 * @Description: 用户问题查询链实现类
 */
@Service
public class AyUserProblemQueryChainServiceImpl {
    private ArrayList<AyUserProblemQueryService> problemQueryChainList;

    public AyUserProblemQueryChainServiceImpl() {
        problemQueryChainList = new ArrayList<AyUserProblemQueryService>();
    }

    /**
     * 添加任务查询链
     *
     * @param ayUserProblemQueryService
     */
    public void addProblemQueryChain(AyUserProblemQueryService ayUserProblemQueryService) {
        problemQueryChainList.add(ayUserProblemQueryService);
    }

    /**
     * 任务查询链入口
     *
     * @param request
     * @return
     */
    public AyUserProblemQueryChainRequest execute(AyUserProblemQueryChainRequest request) {
        List<AyUserProblemQueryService> ayUserProblemQueryServices = new ArrayList<>();
        ayUserProblemQueryServices.addAll(problemQueryChainList);
        problemQueryChainList.clear();
        AyUserProblemQueryChainDTO ayUserProblemQueryChainDTO =
            new AyUserProblemQueryChainDTO(request, ayUserProblemQueryServices, 0);
        return ayUserProblemQueryChainDTO.proceed(request);
    }
}

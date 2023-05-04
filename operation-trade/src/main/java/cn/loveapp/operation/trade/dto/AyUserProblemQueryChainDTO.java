package cn.loveapp.operation.trade.dto;

import java.util.List;

import cn.loveapp.operation.trade.dto.request.AyUserProblemQueryChainRequest;
import cn.loveapp.operation.trade.service.AyUserProblemQueryService;

/**
 * @author xujianhu
 * @date 2023-05-03 16:22
 * @Description: 问题查询链传输体
 */
public class AyUserProblemQueryChainDTO {
    private AyUserProblemQueryChainRequest request;
    private List<AyUserProblemQueryService> ayUserProblemQueryList;
    private Integer index;

    public AyUserProblemQueryChainDTO(AyUserProblemQueryChainRequest request,
        List<AyUserProblemQueryService> ayUserProblemQueryList, Integer index) {
        this.request = request;
        this.ayUserProblemQueryList = ayUserProblemQueryList;
        this.index = index;
    }

    public AyUserProblemQueryChainRequest ayUserProblemQueryChainRequest() {
        return request;
    }

    public AyUserProblemQueryChainRequest proceed(AyUserProblemQueryChainRequest request) {
        AyUserProblemQueryChainRequest problemQueryChainRequest = null;
        if (ayUserProblemQueryList.size() > index) {
            AyUserProblemQueryChainDTO ayLogChainService =
                new AyUserProblemQueryChainDTO(request, ayUserProblemQueryList, index + 1);
            AyUserProblemQueryService ayUserProblemQueryService = ayUserProblemQueryList.get(index);
            problemQueryChainRequest = ayUserProblemQueryService.logQueryDispose(ayLogChainService);
        }
        return problemQueryChainRequest;
    }
}

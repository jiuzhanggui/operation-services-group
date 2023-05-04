package cn.loveapp.operation.trade.service;

import cn.loveapp.operation.trade.dto.AyUserProblemQueryChainDTO;
import cn.loveapp.operation.trade.dto.request.AyUserProblemQueryChainRequest;

/**
 * @author xujianhu
 * @date 2023-05-03 16:20
 * @Description: 爱用日志查询服务接口
 */
public interface AyUserProblemQueryService {
    /**
     * 处理日志查询请求
     *
     * @param ayUserProblemQueryChainDTO
     * @return
     */
    AyUserProblemQueryChainRequest logQueryDispose(AyUserProblemQueryChainDTO ayUserProblemQueryChainDTO);
}

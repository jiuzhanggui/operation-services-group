package cn.loveapp.operation.degradation.service;

import cn.loveapp.operation.degradation.entity.DegradationTask;

/**
 * @author xujianhu
 * @date 2023-04-26 17:10
 * @Description: 降级接口配置操作服务接口
 */
public interface DegradationExecuteService {
    /**
     * 执行降级任务操作
     *
     * @param degradationTask
     * @return
     */
    DegradationTask executeDegradationTask(DegradationTask degradationTask);
}

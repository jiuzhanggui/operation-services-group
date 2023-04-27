package cn.loveapp.operation.degradation.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;

import cn.loveapp.operation.degradation.dto.DegradationListResponse;
import cn.loveapp.operation.degradation.entity.DegradationTask;

/**
 * @author xujianhu
 * @date 2023-04-26 15:21
 * @Description: 降级任务方案表(DegradationTask)表服务接口
 */
public interface DegradationTaskService {
    /**
     * 获取所有的任务列表
     *
     * @param
     * @return
     */
    DegradationListResponse pageDegradationTask(String taskName, Pageable pageable);

    /**
     * 通过taskName查询单条任务信息
     *
     * @param name
     * @return
     */
    DegradationTask getDegradationTask(String name);

    /**
     * 新增任务
     *
     * @param degradationTask
     */
    void setDegradationTask(DegradationTask degradationTask);

    /**
     * 修改任务信息
     *
     * @param degradationTask
     * @return
     */
    int updateDegradationTask(DegradationTask degradationTask);

    /**
     * 删除任务
     *
     * @param name
     * @return
     */
    int removeDegradationTask(String name);

    /**
     * 修改定时降级任务时间
     *
     * @param name
     * @param startTime
     * @param endTime
     * @return
     */
    int updateDegradationTimer(String name, LocalDateTime startTime, LocalDateTime endTime);
}

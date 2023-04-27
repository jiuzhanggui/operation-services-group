package cn.loveapp.operation.degradation.service;

import cn.loveapp.operation.degradation.entity.DegradationTask;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xujianhu
 * @date 2023-04-26 17:07
 * @Description: 降级任务任定时服务接口
 */
public interface DegradationTaskSchedulerService {
    /**
     * 获取当前任务
     *
     * @return
     */
    List<String> getTaskList();

    /**
     * 添加任务
     *
     * @param degradationTask
     * @param taskName
     * @param taskTime
     */
    void addTask(DegradationTask degradationTask, String taskName, LocalDateTime taskTime);

    /**
     * 关闭任务
     *
     * @param taskName
     * @return
     */
    boolean stopTask(String taskName);
}

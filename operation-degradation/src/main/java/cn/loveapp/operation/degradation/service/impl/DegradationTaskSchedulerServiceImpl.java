package cn.loveapp.operation.degradation.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.spring.SpringUtils;

import cn.loveapp.operation.degradation.entity.DegradationTask;
import cn.loveapp.operation.degradation.entity.DegradationTaskLog;
import cn.loveapp.operation.degradation.service.DegradationExecuteService;
import cn.loveapp.operation.degradation.service.DegradationTaskLogService;
import cn.loveapp.operation.degradation.service.DegradationTaskSchedulerService;
import cn.loveapp.operation.degradation.service.DegradationTaskService;

/**
 * @author xujianhu
 * @date 2023-04-26 17:07
 * @Description: 降级定时任务服务实现类
 */
@Service
public class DegradationTaskSchedulerServiceImpl implements DegradationTaskSchedulerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DegradationTaskSchedulerServiceImpl.class);

    // private final ThreadPoolTaskScheduler syncScheduler;

    private final ScheduledExecutorService executor = SpringUtils.getBean("scheduledExecutorService");

    @Autowired
    private DegradationExecuteService degradationExecuteService;

    @Autowired
    private DegradationTaskService degradationTaskService;

    @Autowired
    private DegradationTaskLogService degradationTaskLogService;
    public Map<String, ScheduledFuture<?>> taskMap = new ConcurrentHashMap<>();
    public List<String> taskList = new CopyOnWriteArrayList<String>();

    // public DegradationTaskSchedulerServiceImpl(ThreadPoolTaskScheduler syncScheduler) {
    // this.syncScheduler = syncScheduler;
    // }
    /**
     * 查看已开启但还未执行的动态任务
     *
     * @return
     */
    @Override
    public List<String> getTaskList() {
        return taskList;
    }

    /**
     * 添加一个动态任务
     *
     * @param degradationTask
     * @param taskName
     * @param taskTime
     */
    @Override
    public void addTask(DegradationTask degradationTask, String taskName, LocalDateTime taskTime) {
        // 此处的逻辑是 如果当前已经有这个名字的任务存在，先删除之前的，再添加现在的。（即重复就覆盖）
        if (null != taskMap.get(taskName)) {
            stopTask(taskName);
        }

        Date startTime = Date.from(taskTime.atZone(ZoneId.systemDefault()).toInstant());

        ScheduledFuture<?> schedule = executor.schedule(createTaskRunner(degradationTask, taskName, taskTime),
            startTime.getTime(), TimeUnit.MILLISECONDS);
        // ScheduledFuture<?> schedule =
        // syncScheduler.schedule(createTaskRunner(degradationTask, taskName, taskTime), startTime);
        taskMap.put(taskName, schedule);
        taskList.add(taskName);

        degradationTaskService.updateDegradationTask(degradationTask);
        DegradationTaskLog degradationTaskLog = new DegradationTaskLog();
        degradationTaskLog.setTaskName(degradationTask.getName());
        degradationTaskLog.setContent("降级任务：" + degradationTask.getName() + "开启动态定时任务:[" + taskName + "]");
        degradationTaskLogService.addDegradationTaskLog(degradationTaskLog);

    }

    /**
     * 运行任务
     *
     * @param degradationTask
     * @param taskName
     * @param startTime
     * @return
     */
    private Runnable createTaskRunner(DegradationTask degradationTask, String taskName, LocalDateTime startTime) {
        return () -> {
            LOGGER.info(taskName, "动态定时任务开始执行");
            DegradationTaskLog degradationTaskLog = new DegradationTaskLog();
            degradationTaskLog.setTaskName(degradationTask.getName());
            try {
                degradationTaskLog.setContent("动态定时任务[" + taskName + "]开始执行");
                degradationTaskLogService.addDegradationTaskLog(degradationTaskLog);
                degradationExecuteService.executeDegradationTask(degradationTask);
                Thread.sleep(3);
            } catch (Exception e) {
                LOGGER.error(taskName, "动态定时任务执行异常");
                degradationTaskLog.setContent("动态定时任务[" + taskName + "]执行异常");
                degradationTaskLogService.addDegradationTaskLog(degradationTaskLog);
                e.printStackTrace();
            }

            LOGGER.info(taskName, "动态定时任务执行完毕");
            degradationTaskLog.setContent("动态定时任务[" + taskName + "]执行完毕");
            degradationTaskLogService.addDegradationTaskLog(degradationTaskLog);
        };
    }

    /**
     * 停止任务
     *
     * @param taskName
     * @return
     */
    @Override
    public boolean stopTask(String taskName) {
        if (null == taskMap.get(taskName)) {
            return false;
        }
        ScheduledFuture<?> scheduledFuture = taskMap.get(taskName);
        scheduledFuture.cancel(true);
        taskMap.remove(taskName);
        taskList.remove(taskName);
        return true;
    }
}

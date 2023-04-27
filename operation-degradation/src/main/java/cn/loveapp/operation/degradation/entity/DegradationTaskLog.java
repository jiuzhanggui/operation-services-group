package cn.loveapp.operation.degradation.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xujianhu
 * @date 2023-04-26 17:16
 * @Description: 降级任务日志表(Degradation_task_log)实体类
 */
@Data
public class DegradationTaskLog {

    private long id;
    /**
     * 任务名
     */
    private String taskName;
    /**
     * 任务日志
     */
    private String content;
    /**
     * 执行任务时间
     */
    private LocalDateTime gmtCreate;
}

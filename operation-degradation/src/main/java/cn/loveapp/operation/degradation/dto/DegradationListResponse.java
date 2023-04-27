package cn.loveapp.operation.degradation.dto;

import cn.loveapp.operation.degradation.entity.DegradationTask;
import lombok.Data;

import java.util.List;

/**
 * @author xujianhu
 * @date 2023-04-26 15:11
 * @Description: 任务列表响应体
 */
@Data
public class DegradationListResponse {
    /**
     * 任务列表集合
     */
    private List<DegradationTask> degradationTaskList;

    /**
     * 列表最大数
     */
    private Integer total;
}

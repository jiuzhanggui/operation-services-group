package cn.loveapp.operation.degradation.dto.response;

import cn.loveapp.operation.degradation.entity.DegradationTaskLog;
import lombok.Data;

import java.util.List;

/**
 * @author xujianhu
 * @date 2023-04-26 17:15
 * @Description: 任务日志列表响应体
 */
@Data
public class DegradationLogListResponse {

    /**
     * 任务日志列表集合
     */
    private List<DegradationTaskLog> content;

    /**
     * 列表最大数
     */
    private Integer total;
}

package cn.loveapp.operation.degradation.dto.request;

import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-04-26 15:39
 * @Description: 降级任务公共请求体
 */
@Data
public class BaseDegradationRequest {
    /**
     * 任务名
     */
    private String taskName;
}

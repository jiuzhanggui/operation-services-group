package cn.loveapp.operation.degradation.dto.request;

import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-04-26 17:02
 * @Description: 降级控制请求体
 */
@Data
public class DegradationControlRequest {
    /**
     * 任务名
     */
    private String taskName;
    /**
     * 开关启用状态
     */
    private String switchStatus;
}

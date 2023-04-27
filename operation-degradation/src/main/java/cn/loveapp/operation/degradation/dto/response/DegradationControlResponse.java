package cn.loveapp.operation.degradation.dto.response;

import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-04-26 17:03
 * @Description: 降级控制降响应体
 */
@Data
public class DegradationControlResponse {

    /**
     * 任务名
     */
    private String taskName;
    /**
     * 开关启用状态
     */
    private Integer switchStatus;
}

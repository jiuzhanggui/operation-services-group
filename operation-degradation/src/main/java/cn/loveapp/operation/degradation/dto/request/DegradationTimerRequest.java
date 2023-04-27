package cn.loveapp.operation.degradation.dto.request;

import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-04-27 14:05
 * @Description: 定时降级操作请求体
 */
@Data
public class DegradationTimerRequest extends BaseDegradationRequest {

    /**
     * 定时降级任务开启时间
     */
    private String startTime;

    /**
     * 定时降级任务关闭时间
     */
    private String endTime;
}

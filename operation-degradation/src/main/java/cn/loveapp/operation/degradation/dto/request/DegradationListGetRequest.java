package cn.loveapp.operation.degradation.dto.request;

import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-04-26 15:17
 * @Description: 获取任务列表请求体
 */
@Data
public class DegradationListGetRequest extends BaseDegradationRequest {
    /**
     * 当前页
     */
    private Integer pageSize;

    /**
     * 分页查询开始位置
     */
    private Integer startIndex;

    /**
     * 开关启用状态
     */
    private String switchStatus;
}

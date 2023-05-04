package cn.loveapp.operation.degradation.dto.request;

import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-04-30 16:34
 * @Description: 任务日志列表请求体
 */
@Data
public class DegradationLogListRequest {
    /**
     * 任务名
     */
    private String taskName;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 分页查询开始位置
     */
    private Integer startIndex;

    /**
     * 分页查询每页最大显示条数
     */
    private Integer pageSize;
}

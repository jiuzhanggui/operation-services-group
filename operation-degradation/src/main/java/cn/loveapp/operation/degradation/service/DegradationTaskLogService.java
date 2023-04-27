package cn.loveapp.operation.degradation.service;

import cn.loveapp.operation.degradation.dto.response.DegradationLogListResponse;
import cn.loveapp.operation.degradation.entity.DegradationTaskLog;
import org.springframework.data.domain.Pageable;

/**
 * @author xujianhu
 * @date 2023-04-26 17:14
 * @Description: 降级任务日志表(DegradationTaskLog)表服务接口
 */
public interface DegradationTaskLogService {

    /**
     * 通过name进行分页查询
     *
     * @param taskName
     * @param pageable
     * @return
     */
    DegradationLogListResponse pageDegradationTaskLog(String taskName, Pageable pageable);

    /**
     * 新增日志
     *
     * @param degradationTaskLog 实例对象
     */
    void addDegradationTaskLog(DegradationTaskLog degradationTaskLog);
}

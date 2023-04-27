package cn.loveapp.operation.degradation.dao.mysql;

import cn.loveapp.operation.degradation.entity.DegradationTaskLog;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author xujianhu
 * @date 2023-04-26 17:17
 * @Description: 降级任务日志表(DegradationTaskLog)表数据库访问层
 */
public interface DegradationTaskLogDao {
    /**
     * 通过任务名查询数据
     *
     * @param taskName
     * @param pageable
     * @return
     */
    List<DegradationTaskLog> queryByTaskName(String taskName, Pageable pageable);

    /**
     * 新增数据
     *
     * @param degradationTaskLog 实例对象
     * @return 影响行数
     */
    int insert(DegradationTaskLog degradationTaskLog);

    /**
     * 获取总条数
     *
     * @param taskName
     * @return
     */
    int queryTotal(String taskName);
}

package cn.loveapp.operation.degradation.dao.mysql;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import cn.loveapp.operation.degradation.entity.DegradationTask;

/**
 * @author xujianhu
 * @date 2023-04-26 15:23
 * @Description: 降级任务方案表(DegradationTask)表数据库访问层
 */
@Mapper
public interface DegradationTaskDao {
    /**
     * 查询所有
     *
     * @param pageable
     * @return
     */
    List<DegradationTask> findAll(@Param("name") String name, @Param("pageable") Pageable pageable);

    /**
     * 通过ID查询单条数据
     *
     * @param name
     *            任务名
     * @return 实例对象
     */
    DegradationTask queryByName(String name);

    /**
     * 新增数据
     *
     * @param degradationTask
     *            实例对象
     * @return 影响行数
     */
    int insert(DegradationTask degradationTask);

    /**
     * 修改数据
     *
     * @param degradationTask
     *            实例对象
     * @return 影响行数
     */
    int updateByName(DegradationTask degradationTask);

    /**
     * 通过taskName删除数据
     *
     * @param name
     * @return
     */
    int deleteByName(String name);

    /**
     * 获取总条数
     *
     * @return
     */
    int queryTotal(@Param("name") String name);

    /**
     * 修改定时任务时间
     *
     * @param name
     * @param startTime
     * @param endTime
     * @return
     */
    int updateTimer(@Param("name") String name, @Param("startTime") LocalDateTime startTime,
        @Param("endTime") LocalDateTime endTime);
}

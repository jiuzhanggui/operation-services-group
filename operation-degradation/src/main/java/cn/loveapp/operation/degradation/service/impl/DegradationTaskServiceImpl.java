package cn.loveapp.operation.degradation.service.impl;

import java.time.LocalDateTime;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.loveapp.operation.degradation.dao.mysql.DegradationTaskDao;
import cn.loveapp.operation.degradation.dto.DegradationListResponse;
import cn.loveapp.operation.degradation.entity.DegradationTask;
import cn.loveapp.operation.degradation.service.DegradationTaskService;

/**
 * @author xujianhu
 * @date 2023-04-26 15:21
 * @Description: 降级任务方案表(DegradationTask)表服务实现类
 */
@Service
@DataSource(value = DataSourceType.DREAM_DATA_SOURCE)
public class DegradationTaskServiceImpl implements DegradationTaskService {

    @Autowired
    private DegradationTaskDao degradationTaskDao;

    @Override
    public DegradationListResponse pageDegradationTask(String taskName, Pageable pageable) {
        DegradationListResponse degradationListResponse = new DegradationListResponse();
        degradationListResponse.setDegradationTaskList(degradationTaskDao.findAll(taskName, pageable));
        degradationListResponse.setTotal(degradationTaskDao.queryTotal(taskName));
        return degradationListResponse;
    }

    @Override
    public DegradationTask getDegradationTask(String name) {
        return degradationTaskDao.queryByName(name);
    }

    @Override
    public void setDegradationTask(DegradationTask degradationTask) {
        degradationTaskDao.insert(degradationTask);
    }

    @Override
    public int updateDegradationTask(DegradationTask degradationTask) {
        return degradationTaskDao.updateByName(degradationTask);
    }

    @Override
    public int removeDegradationTask(String name) {
        return degradationTaskDao.deleteByName(name);
    }

    @Override
    public int updateDegradationTimer(String name, LocalDateTime startTime, LocalDateTime endTime) {
        return degradationTaskDao.updateTimer(name, startTime, endTime);
    }
}

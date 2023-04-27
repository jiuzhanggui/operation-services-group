package cn.loveapp.operation.degradation.service.impl;

import cn.loveapp.operation.degradation.entity.DegradationTaskLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import cn.loveapp.operation.degradation.dao.mysql.DegradationTaskLogDao;
import cn.loveapp.operation.degradation.dto.response.DegradationLogListResponse;
import cn.loveapp.operation.degradation.service.DegradationTaskLogService;
import org.springframework.stereotype.Service;

/**
 * @author xujianhu
 * @date 2023-04-26 17:15
 * @Description: 降级任务日志表(DegradationTaskLog)表服务实现类
 */
@Service
public class DegradationTaskLogServiceImpl implements DegradationTaskLogService {

    @Autowired
    private DegradationTaskLogDao degradationTaskLogDao;

    /**
     * 通过taskName数据
     * 
     * @param taskName
     * @param pageable
     * @return
     */
    @Override
    public DegradationLogListResponse pageDegradationTaskLog(String taskName, Pageable pageable) {
        DegradationLogListResponse degradationLogListResponse = new DegradationLogListResponse();
        degradationLogListResponse.setContent(degradationTaskLogDao.queryByTaskName(taskName, pageable));
        degradationLogListResponse.setTotal(degradationTaskLogDao.queryTotal(taskName));
        return degradationLogListResponse;
    }

    /**
     * 新增数据
     *
     * @param degradationTaskLogDao
     *            实例对象
     */
    @Override
    public void addDegradationTaskLog(DegradationTaskLog degradationTaskLogDao) {
        this.degradationTaskLogDao.insert(degradationTaskLogDao);
    }
}

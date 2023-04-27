package cn.loveapp.operation.degradation.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import com.ctrip.framework.apollo.openapi.dto.NamespaceGrayDelReleaseDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenItemDTO;
import com.ruoyi.common.utils.StringUtils;

import cn.loveapp.operation.degradation.config.DegradationConfiguration;
import cn.loveapp.operation.degradation.constant.DegradedStateConstant;
import cn.loveapp.operation.degradation.entity.DegradationTask;
import cn.loveapp.operation.degradation.entity.DegradationTaskLog;
import cn.loveapp.operation.degradation.service.DegradationExecuteService;
import cn.loveapp.operation.degradation.service.DegradationTaskLogService;
import cn.loveapp.operation.degradation.service.DegradationTaskService;

import java.util.Date;

/**
 * @author xujianhu
 * @date 2023-04-26 17:10
 * @Description: 降级接口配置操作实现类
 */
@Service
public class DegradationExecuteServiceImpl implements DegradationExecuteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DegradationExecuteServiceImpl.class);

    @Autowired
    private DegradationTaskLogService degradationTaskLogService;

    @Autowired
    private DegradationTaskService degradationTaskService;

    @Autowired
    private DegradationConfiguration degradationConfiguration;

    @Autowired
    private ApolloOpenApiClient apolloOpenApiClient;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public DegradationTask executeDegradationTask(DegradationTask degradationTask) {

        DegradationTask.DegradationTaskParameter degradationTaskConfig =
            degradationTask.getDDegradationTaskConfig(degradationTask.getConfigurationParameter());
        DegradationTask stateInfo = null;
        if (DegradedStateConstant.CONFIGURATION_INTERFACE_USER_REDIS
            .equals(degradationTaskConfig.getConfigurationInterface())) {
            stateInfo =
                executeRedisInteriorDegradationTaks(degradationTask, degradationConfiguration.getUserRedisTemplate());
        }
        if (DegradedStateConstant.CONFIGURATION_INTERFACE_STRING_REDIS
            .equals(degradationTaskConfig.getConfigurationInterface())) {
            stateInfo =
                executeRedisInteriorDegradationTaks(degradationTask, degradationConfiguration.getUserRedisTemplate());
        }
        if (DegradedStateConstant.CONFIGURATION_INTERFACE_MYSQL
            .equals(degradationTaskConfig.getConfigurationInterface())) {
            stateInfo = executeMysqlInteriorDegradationTask(degradationTask);
        }
        if (DegradedStateConstant.CONFIGURATION_INTERFACE_APOLLO
            .equals(degradationTaskConfig.getConfigurationInterface())) {
            stateInfo = executeApolloInteriorDegradationTask(degradationTask);
        }
        return stateInfo;

    }

    /**
     * 降级任务处理-mysql
     *
     * @param degradationTask
     * @return
     */
    private DegradationTask executeMysqlInteriorDegradationTask(DegradationTask degradationTask) {
        DegradationTask.DegradationTaskParameter degradationTaskConfig =
            degradationTask.getDDegradationTaskConfig(degradationTask.getConfigurationParameter());
        int update;
        String sql = degradationTaskConfig.getKey();
        try {
            update = jdbcTemplate.update(sql);
        } catch (DataAccessException e) {
            LOGGER.error("mysql语句错误", e);
            update = 0;
        }
        DegradationTaskLog degradationTaskLog = new DegradationTaskLog();
        degradationTaskLog.setTaskName(degradationTask.getName());
        if (update > 0) {
            // 添加日志
            degradationTaskLog
                .setContent("降级任务[" + degradationTask.getName() + "]执行成功\r\nSQL：" + sql + "\r\n结果：" + update);
            LOGGER.info(degradationTask.getName(), null, "降级操作执行启动");
        } else {
            degradationTaskLog.setContent("降级任务[" + degradationTask.getName() + "]执行失败");
            return null;
        }
        degradationTaskLogService.addDegradationTaskLog(degradationTaskLog);
        return degradationTask;
    }

    /**
     * Apollo配置处理
     *
     * @param degradationTask
     * @return
     */
    private DegradationTask executeApolloInteriorDegradationTask(DegradationTask degradationTask) {
        DegradationTask.DegradationTaskParameter degradationTaskConfig =
            degradationTask.getDDegradationTaskConfig(degradationTask.getConfigurationParameter());
        String env = "dev";
        // apollo中项目id
        String appId = degradationTaskConfig.getProjectId();
        // 操作的用户
        String opUser = degradationTaskConfig.getUserName();
        // 集群名称
        String cluster = degradationTaskConfig.getAddressName();
        // namespace名称
        String namespace = degradationTaskConfig.getNameSpace();
        String key = degradationTaskConfig.getKey();

        OpenItemDTO changeBeforeItem = apolloOpenApiClient.getItem(appId, env, cluster, namespace, key);
        System.out.println(changeBeforeItem.getValue());

        if (DegradedStateConstant.DEGRADATION_SWITCH_OFF.equals(degradationTask.getSwitchStatus())) {
            // 开启降级
            changeBeforeItem.setValue(degradationTaskConfig.getOpenStatusParameter());
            degradationTask.setSwitchStatus(DegradedStateConstant.DEGRADATION_SWITCH_ON);
            LOGGER.info(degradationTask.getName(), "降级启动");
        } else {
            // 恢复
            changeBeforeItem.setValue(degradationTaskConfig.getCloseStatusParameter());
            degradationTask.setSwitchStatus(DegradedStateConstant.DEGRADATION_SWITCH_OFF);
            LOGGER.info(degradationTask.getName(), "降级恢复");
        }

        changeBeforeItem.setDataChangeCreatedTime(new Date());
        apolloOpenApiClient.createOrUpdateItem(appId, env, cluster, namespace, changeBeforeItem);
        // 刷新说明
        NamespaceGrayDelReleaseDTO namespaceGrayDelReleaseDTO = new NamespaceGrayDelReleaseDTO();
        namespaceGrayDelReleaseDTO.setReleaseTitle(System.currentTimeMillis() + "-release");
        namespaceGrayDelReleaseDTO.setReleaseComment("auto release");
        namespaceGrayDelReleaseDTO.setReleasedBy(opUser);
        apolloOpenApiClient.publishNamespace(appId, env, cluster, namespace, namespaceGrayDelReleaseDTO);

        OpenItemDTO changeAfterItem = apolloOpenApiClient.getItem(appId, env, cluster, namespace, key);
        if (!changeBeforeItem.getValue().equals(changeAfterItem.getValue())) {
            LOGGER.info(degradationTask.getName(), "降级验证成功");
            int i = degradationTaskService.updateDegradationTask(degradationTask);
            // 添加日志
            DegradationTaskLog degradationTaskLog = new DegradationTaskLog();
            degradationTaskLog.setTaskName(degradationTask.getName());
            if (i > 0) {
                degradationTaskLog.setContent("降级任务[" + degradationTask.getName() + "]此时状态为"
                    + (DegradedStateConstant.DEGRADATION_SWITCH_OFF.equals(degradationTask.getSwitchStatus()) ? "未降级"
                        : "已降级"));
            } else {
                degradationTaskLog.setContent("降级任务[" + degradationTask.getName() + "]此时状态未发生改变");
            }
            degradationTaskLogService.addDegradationTaskLog(degradationTaskLog);
            return degradationTask;
        } else {
            LOGGER.info(degradationTask.getName(), "降级验证失败");
            return null;
        }

    }

    /**
     * redis配置处理
     *
     * @param degradationTask
     * @return
     */
    private DegradationTask executeRedisInteriorDegradationTaks(DegradationTask degradationTask,
        StringRedisTemplate stringRedisTemplate) {
        DegradationTask.DegradationTaskParameter degradationTaskConfig =
            degradationTask.getDDegradationTaskConfig(degradationTask.getConfigurationParameter());
        try {
            // 降级
            if (DegradedStateConstant.DEGRADATION_SWITCH_OFF.equals(degradationTask.getSwitchStatus())) {
                if (!StringUtils.isEmpty(degradationTaskConfig.getNameSpace())) {

                    Object changeBeforeValue = stringRedisTemplate.opsForHash()
                        .get(degradationTaskConfig.getNameSpace(), degradationTaskConfig.getKey());
                    stringRedisTemplate.opsForHash().put(degradationTaskConfig.getNameSpace(),
                        degradationTaskConfig.getKey(), degradationTaskConfig.getOpenStatusParameter());
                    Object changeAfterValue = stringRedisTemplate.opsForHash().get(degradationTaskConfig.getNameSpace(),
                        degradationTaskConfig.getKey());
                    if (!changeBeforeValue.equals(changeAfterValue)) {
                        LOGGER.info(degradationTask.getName(), "降级启动验证成功");
                    } else {
                        LOGGER.info(degradationTask.getName(), "降级启动验证失败");
                        return null;
                    }
                } else {
                    String changeBeforeValue = stringRedisTemplate.opsForValue().get(degradationTaskConfig.getKey());
                    stringRedisTemplate.opsForValue().set(degradationTaskConfig.getKey(),
                        degradationTaskConfig.getOpenStatusParameter());
                    String changeAfterValue = stringRedisTemplate.opsForValue().get(degradationTaskConfig.getKey());

                    if (changeAfterValue == null || changeBeforeValue == null) {
                        return null;
                    } else {
                        if (!changeBeforeValue.equals(changeAfterValue)) {
                            LOGGER.info(degradationTask.getName(), "降级恢复验证成功");
                        } else {
                            LOGGER.info(degradationTask.getName(), "降级恢复验证失败");
                            return null;
                        }
                    }
                }
                degradationTask.setSwitchStatus(DegradedStateConstant.DEGRADATION_SWITCH_ON);
                LOGGER.info(degradationTask.getName(), null, "降级启动");

            } else {
                // 恢复
                if (!StringUtils.isEmpty(degradationTaskConfig.getNameSpace())) {
                    Object changeBeforeValue = stringRedisTemplate.opsForHash()
                        .get(degradationTaskConfig.getNameSpace(), degradationTaskConfig.getKey());
                    stringRedisTemplate.opsForHash().put(degradationTaskConfig.getNameSpace(),
                        degradationTaskConfig.getKey(), degradationTaskConfig.getCloseStatusParameter());
                    Object changeAfterValue = stringRedisTemplate.opsForHash().get(degradationTaskConfig.getNameSpace(),
                        degradationTaskConfig.getKey());
                    if (!changeBeforeValue.toString().equals(changeAfterValue.toString())) {
                        LOGGER.info(degradationTask.getName(), null, "降级恢复验证成功");
                    } else {
                        LOGGER.info(degradationTask.getName(), null, "降级恢复验证失败");
                        return null;
                    }
                } else {
                    String changeBeforeValue = stringRedisTemplate.opsForValue().get(degradationTaskConfig.getKey());
                    stringRedisTemplate.opsForValue().set(degradationTaskConfig.getKey(),
                        degradationTaskConfig.getCloseStatusParameter());
                    String changeAfterValue = stringRedisTemplate.opsForValue().get(degradationTaskConfig.getKey());
                    if (changeAfterValue == null || changeBeforeValue == null) {
                        return null;
                    } else {
                        if (!changeBeforeValue.equals(changeAfterValue)) {
                            LOGGER.info(degradationTask.getName(), null, "降级恢复验证成功");
                        } else {
                            LOGGER.info(degradationTask.getName(), null, "降级恢复验证失败");
                            return null;
                        }
                    }
                }
                degradationTask.setSwitchStatus(DegradedStateConstant.DEGRADATION_SWITCH_OFF);
                LOGGER.info(degradationTask.getName(), null, "降级恢复");
            }
        } catch (Exception e) {
            LOGGER.error("redis连接错误", e);
            return null;
        }
        int i = degradationTaskService.updateDegradationTask(degradationTask);
        // 添加日志
        DegradationTaskLog degradationTaskLog = new DegradationTaskLog();
        degradationTaskLog.setTaskName(degradationTask.getName());
        if (i > 0) {
            degradationTaskLog.setContent("降级任务[" + degradationTask.getName() + "]此时状态为"
                + (DegradedStateConstant.DEGRADATION_SWITCH_OFF.equals(degradationTask.getSwitchStatus()) ? "未降级"
                    : "已降级"));
        } else {
            degradationTaskLog.setContent("降级任务[" + degradationTask.getName() + "]此时状态未发生改变");
        }
        degradationTaskLogService.addDegradationTaskLog(degradationTaskLog);
        return degradationTask;
    }
}

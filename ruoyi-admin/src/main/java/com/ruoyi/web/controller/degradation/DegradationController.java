package com.ruoyi.web.controller.degradation;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import cn.loveapp.operation.degradation.dto.response.DegradationLogListResponse;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.framework.datasource.DynamicDataSourceContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import com.ruoyi.operation.common.web.CommonApiResponse;
import com.ruoyi.operation.common.web.CommonApiStatus;

import cn.loveapp.operation.degradation.constant.DegradedStateConstant;
import cn.loveapp.operation.degradation.dto.DegradationListResponse;
import cn.loveapp.operation.degradation.dto.request.*;
import cn.loveapp.operation.degradation.dto.response.DegradationControlResponse;
import cn.loveapp.operation.degradation.entity.DegradationTask;
import cn.loveapp.operation.degradation.entity.DegradationTaskLog;
import cn.loveapp.operation.degradation.service.DegradationExecuteService;
import cn.loveapp.operation.degradation.service.DegradationTaskLogService;
import cn.loveapp.operation.degradation.service.DegradationTaskSchedulerService;
import cn.loveapp.operation.degradation.service.DegradationTaskService;

/**
 * @author xujianhu
 * @date 2023-04-26 15:08
 * @Description: 运维平台降级操作控制器
 */
@Validated
@RestController
@RequestMapping("operation/degradation")
public class DegradationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DegradationController.class);
    private static final String OPEN_TASK_SUFFIX = "-on";
    private static final String CLOSE_TASK_SUFFIX = "-off";

    @Autowired
    private DegradationTaskService degradationTaskService;

    @Autowired
    private DegradationExecuteService degradationExecuteService;

    @Autowired
    private DegradationTaskSchedulerService degradationTaskSchedulerService;

    @Autowired
    private DegradationTaskLogService degradationTaskLogService;

    /**
     * 获取任务列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/degradation.list.get", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult taskList(@RequestBody DegradationListGetRequest request) {
        Pageable pageRequest = PageRequest.of(request.getStartIndex(), request.getPageSize());
        return AjaxResult
            .success(degradationTaskService.pageDegradationTask(request.getTaskName(), pageRequest));
    }

    /**
     * 添加任务
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/degradation.task.add", method = RequestMethod.POST)
    public AjaxResult addTask(@RequestBody DegradationAddRequest request) {
        DegradationTask.DegradationTaskParameter degradationTaskParameter =
            DegradationTask.DegradationTaskParameter.of(request);
        String degradationConfigConfigInfo = JSON.toJSONString(degradationTaskParameter);
        DegradationTask degradationTaskByDb = degradationTaskService.getDegradationTask(request.getTaskName());
        if (degradationTaskByDb == null) {
            DegradationTask degradationTask = new DegradationTask();
            degradationTask.setName(request.getTaskName());
            degradationTask.setDescription(request.getDescription());
            degradationTask.setSwitchStatus(DegradedStateConstant.DEGRADATION_SWITCH_OFF);
            degradationTask.setTimerStatus(DegradedStateConstant.TIMER_STATUS_NULL);
            degradationTask.setConfigurationParameter(degradationConfigConfigInfo);
            degradationTaskService.setDegradationTask(degradationTask);
            return AjaxResult.success("添加成功");
        } else {
            return AjaxResult.warn("任务已存在");
        }
    }

    /**
     * 删除任务
     *
     * @param request
     * @return
     */
    @RequestMapping("/degradation.task.delete")
    public AjaxResult deleteTask(@RequestBody BaseDegradationRequest request) {
        if (degradationTaskService.removeDegradationTask(request.getTaskName()) > 0) {
            return AjaxResult.success("删除成功");
        } else {
            return AjaxResult.warn("删除失败");
        }
    }

    /**
     * 手动设置降级开关
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/degradation.task.control", method = RequestMethod.POST)
    public AjaxResult
        manualControlDegradation(@RequestBody DegradationControlRequest request) {
        // 查询当前任务状态信息
        DegradationTask degradationSchemeInfo = degradationTaskService.getDegradationTask(request.getTaskName());
        if (degradationSchemeInfo.getSwitchStatus() != Integer.parseInt(request.getSwitchStatus())) {
            // 判断是否存在定时任务
            List<String> taskList = degradationTaskSchedulerService.getTaskList();
            // for (String taskName : taskList) {
            if (taskList.contains(request.getTaskName() + OPEN_TASK_SUFFIX)) {
                degradationTaskSchedulerService.stopTask(request.getTaskName() + OPEN_TASK_SUFFIX);
                degradationTaskService.updateDegradationTimer(request.getTaskName(), LocalDateTime.now(), null);
            }
            if (taskList.contains(request.getTaskName() + CLOSE_TASK_SUFFIX)) {
                degradationTaskSchedulerService.stopTask(request.getTaskName() + CLOSE_TASK_SUFFIX);
                degradationTaskService.updateDegradationTimer(request.getTaskName(), null, LocalDateTime.now());
            }
            // }
            DegradationTask stateInfo = degradationExecuteService.executeDegradationTask(degradationSchemeInfo);
            if (stateInfo == null) {
                return AjaxResult.warn("降级验证未通过，请检查参数");
            } else {
                DegradationControlResponse degradationControlResponse = new DegradationControlResponse();
                degradationControlResponse.setTaskName(stateInfo.getName());
                degradationControlResponse.setSwitchStatus(stateInfo.getSwitchStatus());
                return AjaxResult.success(degradationControlResponse);
            }

        } else {
            return AjaxResult.warn("任务已经开启");
        }
    }

    /**
     * 设置任务自动降级和恢复的时间
     *
     * @param degradationTimerRequest
     * @return
     */
    @RequestMapping(value = "/degradation.task.timer", method = RequestMethod.POST)
    public AjaxResult settingDegradeTask(@RequestBody DegradationTimerRequest degradationTimerRequest) {

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DegradationTask degradationTaskInfo =
            degradationTaskService.getDegradationTask(degradationTimerRequest.getTaskName());

        DegradationTask degradationTask = new DegradationTask();
        DegradationTask.DegradationTaskParameter configParameter =
            degradationTask.getDDegradationTaskConfig(degradationTaskInfo.getConfigurationParameter());

        if (DegradedStateConstant.CONFIGURATION_INTERFACE_MYSQL.equals(configParameter.getConfigurationInterface())) {
            return AjaxResult.error(CommonApiStatus.ServerError.code(), "mysql无法开启定时任务，请手动开启");
        } else {
            if (StringUtil.isNotEmpty(degradationTimerRequest.getStartTime())
                && StringUtil.isNotEmpty(degradationTimerRequest.getEndTime())) {
                if (Timestamp.valueOf(degradationTimerRequest.getStartTime()).getTime() > Timestamp
                    .valueOf(LocalDateTime.now()).getTime()) {
                    degradationTaskSchedulerService.addTask(degradationTaskInfo,
                        degradationTaskInfo.getName() + OPEN_TASK_SUFFIX,
                        LocalDateTime.parse(degradationTimerRequest.getStartTime(), dateFormat));
                    degradationTaskInfo
                        .setStartTime(LocalDateTime.parse(degradationTimerRequest.getStartTime(), dateFormat));
                } else {
                    LOGGER.error("连接时间小于当前时间");
                    return AjaxResult.warn("定时任务开始时间小于当前时间");
                }

                if (Timestamp.valueOf(degradationTimerRequest.getEndTime()).getTime() > Timestamp
                    .valueOf(degradationTimerRequest.getStartTime()).getTime()) {
                    degradationTaskSchedulerService.addTask(degradationTaskInfo,
                        degradationTaskInfo.getName() + CLOSE_TASK_SUFFIX,
                        LocalDateTime.parse(degradationTimerRequest.getEndTime(), dateFormat));
                    degradationTaskInfo
                        .setEndTime(LocalDateTime.parse(degradationTimerRequest.getEndTime(), dateFormat));
                } else {
                    LOGGER.info("结束任务时间小于开始任务时间");
                    return AjaxResult.warn("定时任务结束时间大于任务开始时间");
                }
            } else {
                if (StringUtil.isNotEmpty(degradationTimerRequest.getStartTime())) {
                    if (DegradedStateConstant.DEGRADATION_SWITCH_OFF.equals(degradationTaskInfo.getSwitchStatus())) {
                        degradationTaskSchedulerService.addTask(degradationTaskInfo,
                            degradationTaskInfo.getName() + OPEN_TASK_SUFFIX,
                            LocalDateTime.parse(degradationTimerRequest.getStartTime(), dateFormat));
                        degradationTaskInfo
                            .setStartTime(LocalDateTime.parse(degradationTimerRequest.getStartTime(), dateFormat));
                    } else {
                        return AjaxResult.warn("任务已经开启，无需定时开始");
                    }
                }
                if (StringUtil.isNotEmpty(degradationTimerRequest.getEndTime())) {
                    if (DegradedStateConstant.DEGRADATION_SWITCH_ON.equals(degradationTaskInfo.getSwitchStatus())) {
                        degradationTaskSchedulerService.addTask(degradationTaskInfo,
                            degradationTaskInfo.getName() + CLOSE_TASK_SUFFIX,
                            LocalDateTime.parse(degradationTimerRequest.getEndTime(), dateFormat));
                        degradationTaskInfo
                            .setStartTime(LocalDateTime.parse(degradationTimerRequest.getEndTime(), dateFormat));
                    } else {
                        return AjaxResult.warn("任务已经关闭，无需定时关闭");
                    }
                }
            }
        }
        degradationTaskService.updateDegradationTask(degradationTaskInfo);
        return AjaxResult.success("定时任务开启");
    }

    /**
     * 取消降级任务
     *
     * @param degradationTimerRequest
     * @return
     */
    @RequestMapping(value = "/degradation.task.canceltimer", method = RequestMethod.POST)
    public AjaxResult cancelDegradeTask(@RequestBody DegradationTimerRequest degradationTimerRequest) {

        List<String> taskList = degradationTaskSchedulerService.getTaskList();
        String taskName = degradationTimerRequest.getTaskName();
        if (taskList.contains(taskName + OPEN_TASK_SUFFIX) || taskList.contains(taskName + CLOSE_TASK_SUFFIX)) {
            DegradationTaskLog degradationTaskLog = new DegradationTaskLog();
            degradationTaskLog.setTaskName(taskName);
            if (taskList.contains(taskName + OPEN_TASK_SUFFIX)) {
                degradationTaskSchedulerService.stopTask(taskName + OPEN_TASK_SUFFIX);
                degradationTaskLog.setContent("动态定时任务 [" + taskName + OPEN_TASK_SUFFIX + "] ,取消定时任务成功");
                degradationTaskLogService.addDegradationTaskLog(degradationTaskLog);
            }
            if (taskList.contains(taskName + CLOSE_TASK_SUFFIX)) {
                degradationTaskSchedulerService.stopTask(taskName + CLOSE_TASK_SUFFIX);
                degradationTaskLog.setContent("动态定时任务 [" + taskName + CLOSE_TASK_SUFFIX + "] ,取消定时任务成功");
                degradationTaskLogService.addDegradationTaskLog(degradationTaskLog);
            }
            // 取消任务的同时将保存的定时时间设为当前时间
            degradationTaskService.updateDegradationTimer(taskName, LocalDateTime.now(), LocalDateTime.now());

            return AjaxResult.success("取消定时任务成功");
        } else {
            return AjaxResult.warn("该降级无定时任务");
        }
    }


    /**
     * 获取任务日志
     *
     * @param degradationLogListRequest
     * @return
     */
    @RequestMapping(value = "/degradation.log.get", method = RequestMethod.POST)
    public AjaxResult
    taskLogList(@RequestBody DegradationLogListRequest degradationLogListRequest) {
        Pageable pageRequest =
                PageRequest.of(degradationLogListRequest.getStartIndex(), degradationLogListRequest.getPageSize());
        return AjaxResult.success(
                degradationTaskLogService.pageDegradationTaskLog(degradationLogListRequest.getTaskName(), pageRequest));
    }
}

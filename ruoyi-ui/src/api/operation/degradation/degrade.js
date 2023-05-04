// 订单相关的API
import request from "@/utils/request";

/**
 * 获取降级任务列表
 * @param params
 * @returns {AxiosPromise}
 */
export function getDegradationTaskListAPI(params) {
  return request({
    url: `operation/degradation/degradation.list.get`,
    method: 'post',
    data: params,
  })
}

/**
 * 新增降级任务
 * @param params
 * @returns {AxiosPromise}
 */
export function addDegradationTaskAPI(params) {
  return request({
    url: `operation/degradation/degradation.task.add`,
    method: 'post',
    data: params,
  })
}

/**
 * 删除降级任务
 * @param params
 * @returns {AxiosPromise}
 */
export function deleteDegradationTaskAPI(params) {
  return request({
    url: `operation/degradation/degradation.task.delete`,
    method: 'post',
    data: params,
  })
}

/**
 * 降级任务控制器（开启、关闭降级任务）
 * @param params
 * @returns {AxiosPromise}
 */
export function degradationTaskControl(params) {
  return request({
    url: `operation/degradation/degradation.task.control`,
    method: 'post',
    data: params,
  })
}

/**
 * 设置定时任务
 * @param params
 * @returns {AxiosPromise}
 */
export function degradationTaskTimer(params) {
  return request({
    url: `operation/degradation/degradation.task.timer`,
    method: 'post',
    data: params,
  })
}

/**
 * 取消任务定时器
 * @param params
 * @returns {AxiosPromise}
 */
export function cancelDegradationTaskTimer(params) {
  return request({
    url: `operation/degradation/degradation.task.canceltimer`,
    method: 'post',
    data: params,
  })
}

/**
 * 降级日志获取
 * @param params
 * @returns {AxiosPromise}
 */
export function getDegradationTaskLog(params) {
  return request({
    url: `operation/degradation/degradation.log.get`,
    method: 'post',
    data: params,
  })
}

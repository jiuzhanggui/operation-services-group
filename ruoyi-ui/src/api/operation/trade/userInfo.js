// 用户信息相关API
import request from "@/utils/request";

/**
 * EXT表请求API
 * @param params
 * @returns {AxiosPromise}
 */
export function getExInfoDataAPI(params) {
  return request({
    url: `operation/userInfo/extInfo.list.get`,
    method: 'post',
    data: params,
  })
}

/**
 * 用户信息表请求API
 * @param params
 * @returns {AxiosPromise}
 */
export function getUserProductDataAPI(params) {
  return request({
    url: `operation/userInfo/userProductInfo.list.get`,
    method: 'post',
    data: params,
  })
}

/**
 * 订购信息获取请求API
 * @param params
 * @returns {AxiosPromise}
 */
export function getOrderSearchDataAPI(params) {
  return request({
    url: `operation/userInfo/orderSearchInfo.list.get`,
    method: 'post',
    data: params,
  })
}

/**
 * 用户开户记录请求API
 * @param params
 * @returns {AxiosPromise}
 */
export function getOpenUserDataAPI(params) {
  return request({
    url: `operation/userInfo/openUserInfoLog.list.get`,
    method: 'post',
    data: params,
  })
}

// 订单相关的API
import request from "@/utils/request";

/**
 * ES数据请求API
 * @param params
 * @returns {AxiosPromise}
 */
export function getEsInfoAPI(params) {
  return request({
    url: `operation/orderInfo/getEsOrderInfo`,
    method: 'post',
    data: params,
  })
}

/**
 * 主单数据请求API
 * @param params
 * @returns {AxiosPromise}
 */
export function getOrderInfoAPI(params) {
  return request({
    url: `operation/orderInfo/getOrderInfo`,
    method: 'post',
    data: params,
  })
}

/**
 * 子单数据请求API
 * @param params
 * @returns {AxiosPromise}
 */
export function getSuOrderInfoAPI(params) {
  return request({
    url: `operation/orderInfo/getSubOrderInfo`,
    method: 'post',
    data: params,
  })
}

/**
 * 退款订单数据请求API
 * @param params
 * @returns {AxiosPromise}
 */
export function getRefundOrderInfoAPI(params) {
  return request({
    url: `operation/orderInfo/getRefundOrderInfo`,
    method: 'post',
    data: params,
  })
}

/**
 * 淘宝FullInfo请求API
 * @param params
 * @returns {AxiosPromise}
 */
export function getFullInfoAPI(params) {
  return http.post(`${request}/orderinfo/orderfullinfo.list.get`, params)
}

/**
 * 淘宝退款请求API
 * @param params
 * @returns {AxiosPromise}
 */
export function getRefundGetInfoAPI(params) {
  return http.post(`${request}/orderinfo/refundorderapiinfo.list.get`, params)
}

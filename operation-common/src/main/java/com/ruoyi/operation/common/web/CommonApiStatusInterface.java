package com.ruoyi.operation.common.web;

/**
 * @author xujianhu
 * @date 2023-03-20 10:42
 * @Description: 通用响应接口
 */
public interface CommonApiStatusInterface {

    /**
     * 编码
     * 
     * @return
     */
    int code();

    /**
     * 信息
     * 
     * @return
     */
    String message();
}

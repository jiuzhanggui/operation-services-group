package com.ruoyi.operation.common.web;

import org.springframework.http.HttpStatus;

/**
 * @author xujianhu
 * @date 2023-03-20 10:42
 * @Description: 响应枚举类
 */
public enum CommonApiStatus implements CommonApiStatusInterface {
    /**
     * 请求成功
     */
    Success(HttpStatus.OK.value(), "成功"),

    /**
     * 通用业务异常
     */
    Failed(1000, "业务失败"),

    /**
     * 请求参数错误
     */
    RequestParamError(HttpStatus.BAD_REQUEST.value(), "参数错误"),

    /**
     * 缺少授权信息
     */
    UnauthorizedError(HttpStatus.UNAUTHORIZED.value(), "缺少授权信息"),

    /**
     * 鉴权失败
     */
    ForbiddenError(HttpStatus.FORBIDDEN.value(), "鉴权失败"),

    /**
     * 请求失败
     */
    ServerError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统异常，请稍后再试");

    private int code;
    private String message;

    CommonApiStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}

package com.ruoyi.operation.common.constant;

/**
 * @author xujianhu
 * @date 2023-03-20 10:57
 * @Description: 请求体响应常量
 */
public enum ResponseConstant {
    /**
     * 请求参数异常返回体
     */
    PARAMSE_RROR(9999, "参数错误"),

    /**
     * 调用平台API异常返回体
     */
    INVOK_PLATFROM_API_ERROR(9998, "调用平台API错误"),

    /**
     * 调用uac异常
     */
    REQUEST_UAC_ERROR(9997, "调用UAC异常"),

    /**
     * 用户session为空
     */
    USER_SESSION_ISNUll(9996, "用户session为空"),

    /**
     * 用户不存在
     */
    USER_NOT_EXIST(9995, "用户不存在"),

    /**
     * 用户不存在
     */
    USER_EXPIRE(9994, "用户账号失效"),

    /**
     * token失效
     */
    TOKEN_LOSE(9993, "token失效");

    /**
     * 响应返回码
     */
    private Integer code;

    /**
     * 响应返回体
     */
    private String message;

    ResponseConstant(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

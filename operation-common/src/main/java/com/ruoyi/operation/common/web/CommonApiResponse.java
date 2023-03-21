package com.ruoyi.operation.common.web;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author xujianhu
 * @date 2023-03-20 10:44
 * @Description: 通用http返回体
 */
@Data
public class CommonApiResponse<T> {
    protected Integer code;

    @JSONField(name = "sub_code")
    @JsonProperty(value = "sub_code")
    protected Integer subCode;

    protected String message;

    @JSONField(name = "sub_message")
    @JsonProperty(value = "sub_message")
    protected String subMessage;

    protected T body;

    public CommonApiResponse(Integer code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public CommonApiResponse(Integer code, String message, Integer subCode, String subMessage, T body) {
        this.code = code;
        this.message = message;
        this.subCode = subCode;
        this.subMessage = subMessage;
        this.body = body;
    }

    public CommonApiResponse(Integer code, String message) {
        this(code, message, null);
    }

    public CommonApiResponse(Integer code, String message, Integer subCode, String subMessage) {
        this(code, message, subCode, subMessage, null);
    }

    public static <T> CommonApiResponse<T> success(T body) {
        return new CommonApiResponse<>(CommonApiStatus.Success.code(), CommonApiStatus.Success.message(), body);
    }

    public static <T> CommonApiResponse<T> success() {
        return CommonApiResponse.of(CommonApiStatus.Success);
    }

    public static <T> CommonApiResponse<T> failed(int subCode, String subMessage) {
        return new CommonApiResponse<>(CommonApiStatus.Failed.code(), CommonApiStatus.Failed.message(), subCode,
            subMessage);
    }

    public static <T> CommonApiResponse<T> failed(int subCode, String subMessage, T body) {
        return new CommonApiResponse<>(CommonApiStatus.Failed.code(), CommonApiStatus.Failed.message(), subCode,
            subMessage, body);
    }

    public static <T> CommonApiResponse<T> failed(CommonApiStatusInterface subStatus) {
        return new CommonApiResponse<>(CommonApiStatus.Failed.code(), CommonApiStatus.Failed.message(),
            subStatus.code(), subStatus.message());
    }

    public static <T> CommonApiResponse<T> failed(CommonApiStatusInterface subStatus, T body) {
        return new CommonApiResponse<>(CommonApiStatus.Failed.code(), CommonApiStatus.Failed.message(),
            subStatus.code(), subStatus.message(), body);
    }

    public static <T> CommonApiResponse<T> serverError() {
        return CommonApiResponse.failed(CommonApiStatus.ServerError);
    }

    public static <T> CommonApiResponse<T> of(CommonApiStatusInterface commonApiStatus) {
        return new CommonApiResponse<>(commonApiStatus.code(), commonApiStatus.message());
    }

    public static <T> CommonApiResponse<T> of(int code, String message) {
        return new CommonApiResponse<>(code, message);
    }

    public static <T> CommonApiResponse<T> of(int code, String message, T body) {
        return new CommonApiResponse<>(code, message, body);
    }

    public static <T> CommonApiResponse<T> of(int code, String message, int subCode, String subMessage) {
        return new CommonApiResponse<>(code, message, subCode, subMessage);
    }

    public static <T> CommonApiResponse<T> of(int code, String message, int subCode, String subMessage, T body) {
        return new CommonApiResponse<>(code, message, subCode, subMessage, body);
    }

    /**
     * 是否成功
     *
     * @return
     */
    @JSONField(serialize = false, deserialize = false)
    public boolean isSuccess() {
        return CommonApiStatus.Success.code() == code && subCode == null;
    }
}

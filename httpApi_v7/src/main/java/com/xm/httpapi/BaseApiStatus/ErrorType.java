package com.xm.httpapi.BaseApiStatus;


/**
 * 错误编号
 */

public enum ErrorType {
    /**
     * 请求成功
     */
    SUCCESS("请求成功", 0),
    /**
     * 解析对象为空
     */
    EMPTY_BEAN("解析对象为空", 1004),
    /**
     *
     */
    NETWORK_ERROR("当前网络不可用!", -1),
    /**
     *
     */
    SERVICE_ERROR("服务器异常，请稍后重试", 8000),
    /**
     *
     */
    TIME_OUT("网络超时，请检查您的网络状态", 8100),
    /**
     *
     */
    TIME_ERROR("网络连接异常，请检查您的网络状态", 8200),
    /**
     *
     */
    TIME_CANCEL("网络中断，请检查您的网络状态", 8300);

    private int code;
    private String message;

    ErrorType(String message, int code) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

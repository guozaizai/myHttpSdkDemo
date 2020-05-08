package com.xm.httpapi.BaseApiStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * 自定义的服务器异常
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class ServerException extends RuntimeException {
    private int code;
    private String message;

    public ServerException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}

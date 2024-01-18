package com.odn.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T> {
    private int errorCode;
    private String errorMessage;
    private T result;

    public BaseResponse() {
        this.errorCode = Constants.ErrorCode.UNKNOWN_ERROR;
        this.errorMessage = "";
        this.result = null;
    }

    public BaseResponse(String errorMessage) {
        this.errorCode = Constants.ErrorCode.UNKNOWN_ERROR;
        this.errorMessage = errorMessage;
        this.result = null;
    }

    public BaseResponse(int errorCode, String errorMessage, T result) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.result = result;
    }
}
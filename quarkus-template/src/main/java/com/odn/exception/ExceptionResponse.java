package com.odn.exception;

import com.odn.common.BaseResponse;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ExceptionResponse extends BaseResponse<String> {

    public ExceptionResponse(String message) {
        super(message);
    }

    public ExceptionResponse(int errorCode, String message, String uuid) {
        super(errorCode, message, uuid);
    }

}

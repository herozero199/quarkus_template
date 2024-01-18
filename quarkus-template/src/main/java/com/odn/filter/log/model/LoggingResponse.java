package com.odn.filter.log.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.core.MultivaluedMap;

@Getter
@Setter
@AllArgsConstructor
public class LoggingResponse {
    private int status;
    private MultivaluedMap<String, Object> headers;
    private String requestId;
    private String responseBody;

    @Override
    public String toString() {
        return "LoggingResponse{" +
                "status=" + status +
                ", headers=" + headers +
                ", requestId='" + requestId + '\'' +
                ", responseBody='" + responseBody + '\'' +
                '}';
    }
}

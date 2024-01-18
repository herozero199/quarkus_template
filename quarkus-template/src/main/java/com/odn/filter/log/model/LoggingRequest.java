package com.odn.filter.log.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.core.MultivaluedMap;

@Getter
@Setter
@AllArgsConstructor
public class LoggingRequest {
    private MultivaluedMap<String, String> headers;
    private String endpoint;
    private String requestId;
    private String requestBody;

    @Override
    public String toString() {
        return "LoggingRequest{" +
                "headers=" + headers +
                ", endpoint='" + endpoint + '\'' +
                ", requestId='" + requestId + '\'' +
                ", requestBody='" + requestBody + '\'' +
                '}';
    }
}

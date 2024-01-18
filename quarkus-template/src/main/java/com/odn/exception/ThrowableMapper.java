package com.odn.exception;

import com.odn.common.Constants;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ResourceBundle;
import java.util.UUID;

@Provider
@Slf4j
public class ThrowableMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable e) {
        String errorId = UUID.randomUUID().toString();
        log.error("errorId[{}]", errorId, e);
        String defaultErrorMessage = ResourceBundle.getBundle("Messages").getString("System.error");
        defaultErrorMessage += " Error id: " + errorId;
        ExceptionResponse errorResponse = new ExceptionResponse(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                defaultErrorMessage, null);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
    }

}
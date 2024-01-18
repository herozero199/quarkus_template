package com.odn.exception;

import com.odn.common.Constants;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.UUID;

@Provider
@Slf4j
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

    @Override
    public Response toResponse(ServiceException e) {
        //String errorId = UUID.randomUUID().toString();
        //log.error("errorId[{}]", errorId, e);
        ExceptionResponse errorResponse = new ExceptionResponse(Response.Status.BAD_REQUEST.getStatusCode(),
                e.getMessage(), null);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }

}
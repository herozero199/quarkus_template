package com.odn.exception;

import com.odn.common.BaseResponse;
import com.odn.common.Constants;
import io.micrometer.core.instrument.util.StringUtils;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.stream.Collectors;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        List<ErrorResponse.ErrorMessage> errorMessages = e.getConstraintViolations().stream()
                .map(constraintViolation -> new ErrorResponse.ErrorMessage(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage()))
                .collect(Collectors.toList());

        String errorMessage = "";

        for (ErrorResponse.ErrorMessage element : errorMessages) {
            String m = element.getMessage();
            errorMessage += StringUtils.isNotBlank(errorMessage) ? "; " + m : m;
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(new BaseResponse<>(Response.Status.BAD_REQUEST.getStatusCode(),
                errorMessage, null)).build();
    }

}
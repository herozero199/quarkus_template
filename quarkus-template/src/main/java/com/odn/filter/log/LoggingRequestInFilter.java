package com.odn.filter.log;

import com.odn.filter.log.model.LoggingRequest;
import com.odn.filter.log.model.LoggingResponse;
import org.jboss.logging.Logger;

import java.io.*;
import java.util.UUID;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Integer.MAX_VALUE)
public class LoggingRequestInFilter extends BaseLoggingFilter
        implements ContainerRequestFilter, ContainerResponseFilter {

    @Inject
    Logger logger;


    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        UriInfo uriInfo = containerRequestContext.getUriInfo();
//        String requestUri = uriInfo.getRequestUri().toString();
        String path = uriInfo.getPath();
        String requestId = UUID.randomUUID().toString();
        containerRequestContext.setProperty(ENTITY_REQUEST_ID, requestId);
        StringBuilder sb = new StringBuilder();
        if (containerRequestContext.hasEntity()) {
            containerRequestContext.setEntityStream(logInboundEntity(sb, containerRequestContext.getEntityStream(), DEFAULT_CHARSET));
            // Ghi log dữ liệu body của request để lấy trong aroundWriteTo. Nếu không log tập trung request và response thì bỏ qua
            // containerRequestContext.setProperty(ENTITY_STREAM_PROPERTY, sb.toString());
        }
        logger.info(new LoggingRequest(containerRequestContext.getHeaders(), path, requestId, sb.toString()));
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        // You can also log other response information from responseContext object
        // For example, headers: responseContext.getHeaders(), entity: responseContext.getEntity() etc.
        String responseBody = null;
        String method = containerRequestContext.getMethod();
        if ((HttpMethod.PUT.equals(method) || HttpMethod.POST.equals(method)) &&
                null != containerResponseContext.getEntity()) {
            responseBody = containerResponseContext.getEntity().toString();
        }
        String requestId = containerRequestContext.getProperty(ENTITY_REQUEST_ID) == null ? "request_id_NULL" :
                containerRequestContext.getProperty(ENTITY_REQUEST_ID).toString();
        logger.info(new LoggingResponse(containerResponseContext.getStatus(),
                containerResponseContext.getHeaders(),
                requestId,
                responseBody));
    }

//    @Override
//    public void aroundWriteTo(WriterInterceptorContext writerInterceptorContext) throws IOException, WebApplicationException {
//        writerInterceptorContext.proceed();
//        String responseBody = null;
//        if (null != writerInterceptorContext.getEntity()) {
//            responseBody = writerInterceptorContext.getEntity().toString();
//        }
//        logger.info(new LoggingResponse(writerInterceptorContext.getHeaders(),
//                writerInterceptorContext.getProperty(ENTITY_REQUEST_ID).toString(),
//                responseBody));
//    }
}
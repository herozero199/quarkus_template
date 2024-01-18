package com.odn.filter.log;

import javax.annotation.Priority;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.*;

@Priority(Integer.MIN_VALUE)
public class LoggingRequestOutFilter extends BaseLoggingFilter
        implements ClientRequestFilter, ClientResponseFilter, WriterInterceptor {

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        if (requestContext.hasEntity()) {
            final OutputStream stream = new LoggingOutputStream(requestContext.getEntityStream());
            requestContext.setEntityStream(stream);
            requestContext.setProperty(ENTITY_STREAM_PROPERTY, stream);
        }
    }

    @Override
    public void filter(ClientRequestContext requestContext,
                       ClientResponseContext responseContext) throws IOException {
        final StringBuilder sb = new StringBuilder();
        if (responseContext.hasEntity()) {
            responseContext.setEntityStream(logInboundEntity(sb, responseContext.getEntityStream(),
                    DEFAULT_CHARSET));
            log(sb);
        }
    }

    @Override
    public void aroundWriteTo(WriterInterceptorContext context)
            throws IOException, WebApplicationException {
        final LoggingOutputStream stream = (LoggingOutputStream) context.getProperty(ENTITY_STREAM_PROPERTY);
        context.proceed();
        if (stream != null) {
            log(stream.getStringBuilder(DEFAULT_CHARSET));
        }
    }
}
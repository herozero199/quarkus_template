package com.odn.filter.log;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class BaseLoggingFilter {
    @Inject
    Logger logger;
    private final int maxEntitySize = 1024 * 8;
    protected static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    protected static final String ENTITY_STREAM_PROPERTY = "entityStream";
    protected static final String ENTITY_REQUEST_ID = "REQUEST_ID";

    protected void log(StringBuilder sb) {
        logger.info(sb.toString());
    }
    protected InputStream logInboundEntity(final StringBuilder b, InputStream stream, final Charset charset) throws IOException {
        if (!stream.markSupported()) {
            stream = new BufferedInputStream(stream);
        }
        stream.mark(maxEntitySize + 1);
        final byte[] entity = new byte[maxEntitySize + 1];
        final int entitySize = stream.read(entity);
        int minEntitySize = Math.min(entitySize, maxEntitySize);
        if (minEntitySize <= 0) {
            return stream;
        }
        b.append(new String(entity, 0, minEntitySize, charset));
        if (entitySize > maxEntitySize) {
            b.append("...more...");
        }
        b.append('\n');
        stream.reset();
        return stream;
    }



}

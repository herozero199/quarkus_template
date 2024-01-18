package com.odn.filter.log;

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class LoggingOutputStream extends FilterOutputStream {
    private final int maxEntitySize = 1024 * 8;
    private final StringBuilder sb = new StringBuilder();
    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    LoggingOutputStream(OutputStream out) {
        super(out);
    }

    StringBuilder getStringBuilder(Charset charset) {
        // write entity to the builder
        final byte[] entity = baos.toByteArray();

        sb.append(new String(entity, 0, entity.length, charset));
        if (entity.length > maxEntitySize) {
            sb.append("...more...");
        }
        sb.append('\n');

        return sb;
    }

    @Override
    public void write(final int i) throws IOException {
        if (baos.size() <= maxEntitySize) {
            baos.write(i);
        }
        out.write(i);
    }
}
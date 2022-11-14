package com.xunyunedu.util.ftp;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: yhc
 * @Date: 2020/10/15 17:25
 * @Description:
 */
public class StreamReuse {
    private InputStream input;

    public StreamReuse(InputStream input) {
        if (!input.markSupported()) {
            this.input = new BufferedInputStream(input);
        } else {
            this.input = input;
        }

    }

    public InputStream getInputStream() {
        this.input.mark(2147483647);
        return this.input;
    }

    public void markUsed() throws IOException {
        this.input.reset();
    }
}

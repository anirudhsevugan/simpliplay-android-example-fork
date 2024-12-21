package com.google.android.exoplayer2.upstream;

import java.io.IOException;

public interface LoadErrorHandlingPolicy {

    public final class LoadErrorInfo {
        public final IOException a;
        public final int b;

        public LoadErrorInfo(IOException iOException, int i) {
            this.a = iOException;
            this.b = i;
        }
    }

    int a(int i);

    long a(LoadErrorInfo loadErrorInfo);

    void a();

    long b(LoadErrorInfo loadErrorInfo);
}

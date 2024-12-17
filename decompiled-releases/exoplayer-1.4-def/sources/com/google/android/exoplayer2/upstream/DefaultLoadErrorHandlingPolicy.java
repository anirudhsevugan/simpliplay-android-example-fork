package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.Loader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DefaultLoadErrorHandlingPolicy implements LoadErrorHandlingPolicy {
    public DefaultLoadErrorHandlingPolicy() {
        this((byte) 0);
    }

    private DefaultLoadErrorHandlingPolicy(byte b) {
    }

    public final int a(int i) {
        return i == 7 ? 6 : 3;
    }

    public final long a(LoadErrorHandlingPolicy.LoadErrorInfo loadErrorInfo) {
        IOException iOException = loadErrorInfo.a;
        if (!(iOException instanceof HttpDataSource.InvalidResponseCodeException)) {
            return -9223372036854775807L;
        }
        int i = ((HttpDataSource.InvalidResponseCodeException) iOException).a;
        return (i == 403 || i == 404 || i == 410 || i == 416 || i == 500 || i == 503) ? 60000 : -9223372036854775807L;
    }

    public final void a() {
        LoadErrorHandlingPolicy$$CC.a();
    }

    public final long b(LoadErrorHandlingPolicy.LoadErrorInfo loadErrorInfo) {
        IOException iOException = loadErrorInfo.a;
        if ((iOException instanceof ParserException) || (iOException instanceof FileNotFoundException) || (iOException instanceof HttpDataSource.CleartextNotPermittedException) || (iOException instanceof Loader.UnexpectedLoaderException)) {
            return -9223372036854775807L;
        }
        return (long) Math.min((loadErrorInfo.b - 1) * 1000, 5000);
    }
}

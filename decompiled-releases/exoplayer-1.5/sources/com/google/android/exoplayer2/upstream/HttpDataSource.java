package com.google.android.exoplayer2.upstream;

import com.dreamers.exoplayercore.repack.aE;
import com.google.android.exoplayer2.upstream.DataSource;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface HttpDataSource extends DataSource {

    public final class CleartextNotPermittedException extends HttpDataSourceException {
        public CleartextNotPermittedException(IOException iOException) {
            super("Cleartext HTTP traffic not permitted. See https://exoplayer.dev/issues/cleartext-not-permitted", iOException);
        }
    }

    public interface Factory extends DataSource.Factory {
        HttpDataSource b();
    }

    public class HttpDataSourceException extends IOException {
        public HttpDataSourceException(IOException iOException) {
            super(iOException);
        }

        public HttpDataSourceException(String str) {
            super(str);
        }

        public HttpDataSourceException(String str, IOException iOException) {
            super(str, iOException);
        }
    }

    public final class InvalidResponseCodeException extends HttpDataSourceException {
        public final int a;
        public final Map b;

        public InvalidResponseCodeException(int i, Map map) {
            super(new StringBuilder(26).append("Response code: ").append(i).toString());
            this.a = i;
            this.b = map;
        }
    }

    public final class RequestProperties {
        private final Map a = new HashMap();
        private Map b;

        public final synchronized Map a() {
            if (this.b == null) {
                this.b = Collections.unmodifiableMap(new HashMap(this.a));
            }
            return this.b;
        }
    }

    static {
        aE aEVar = HttpDataSource$$Lambda$0.a;
    }
}

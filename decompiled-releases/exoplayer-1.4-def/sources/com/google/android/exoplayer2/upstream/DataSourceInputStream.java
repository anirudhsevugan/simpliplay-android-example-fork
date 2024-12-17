package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.util.Assertions;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.InputStream;

public final class DataSourceInputStream extends InputStream {
    private final DataSource a;
    private final DataSpec b;
    private final byte[] c;
    private boolean d = false;
    private boolean e = false;
    private long f;

    public DataSourceInputStream(DataSource dataSource, DataSpec dataSpec) {
        this.a = dataSource;
        this.b = dataSpec;
        this.c = new byte[1];
    }

    public final void a() {
        if (!this.d) {
            this.a.a(this.b);
            this.d = true;
        }
    }

    public final void close() {
        if (!this.e) {
            this.a.c();
            this.e = true;
        }
    }

    public final int read() {
        if (read(this.c) == -1) {
            return -1;
        }
        return this.c[0] & Ev3Constants.Opcode.TST;
    }

    public final int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public final int read(byte[] bArr, int i, int i2) {
        Assertions.b(!this.e);
        a();
        int a2 = this.a.a(bArr, i, i2);
        if (a2 == -1) {
            return -1;
        }
        this.f += (long) a2;
        return a2;
    }
}

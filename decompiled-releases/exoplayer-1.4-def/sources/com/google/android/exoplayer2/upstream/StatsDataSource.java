package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Collections;
import java.util.Map;

public final class StatsDataSource implements DataSource {
    public long a;
    public Uri b = Uri.EMPTY;
    public Map c = Collections.emptyMap();
    private final DataSource d;

    public StatsDataSource(DataSource dataSource) {
        this.d = (DataSource) Assertions.b((Object) dataSource);
    }

    public final int a(byte[] bArr, int i, int i2) {
        int a2 = this.d.a(bArr, i, i2);
        if (a2 != -1) {
            this.a += (long) a2;
        }
        return a2;
    }

    public final long a(DataSpec dataSpec) {
        this.b = dataSpec.a;
        this.c = Collections.emptyMap();
        long a2 = this.d.a(dataSpec);
        this.b = (Uri) Assertions.b((Object) a());
        this.c = b();
        return a2;
    }

    public final Uri a() {
        return this.d.a();
    }

    public final void a(TransferListener transferListener) {
        Assertions.b((Object) transferListener);
        this.d.a(transferListener);
    }

    public final Map b() {
        return this.d.b();
    }

    public final void c() {
        this.d.c();
    }
}

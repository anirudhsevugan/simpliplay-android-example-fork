package com.google.android.exoplayer2.source.chunk;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.StatsDataSource;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Map;

public abstract class Chunk implements Loader.Loadable {
    public final DataSpec e;
    public final int f;
    public final Format g;
    public final int h;
    public final Object i;
    public final long j;
    public final long k;
    protected final StatsDataSource l;

    public Chunk(DataSource dataSource, DataSpec dataSpec, int i2, Format format, int i3, Object obj, long j2, long j3) {
        this.l = new StatsDataSource(dataSource);
        this.e = (DataSpec) Assertions.b((Object) dataSpec);
        this.f = i2;
        this.g = format;
        this.h = i3;
        this.i = obj;
        this.j = j2;
        this.k = j3;
        LoadEventInfo.a();
    }

    public final long d() {
        return this.l.a;
    }

    public final Uri e() {
        return this.l.b;
    }

    public final Map f() {
        return this.l.c;
    }
}

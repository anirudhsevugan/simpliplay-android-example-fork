package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;

public abstract class BaseMediaChunk extends MediaChunk {
    public final long a;
    public final long b;
    BaseMediaChunkOutput c;
    int[] d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BaseMediaChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, Object obj, long j, long j2, long j3, long j4, long j5) {
        super(dataSource, dataSpec, format, i, obj, j, j2, j5);
        this.a = j3;
        this.b = j4;
    }

    public final int a(int i) {
        return ((int[]) Assertions.a((Object) this.d))[i];
    }

    /* access modifiers changed from: protected */
    public final BaseMediaChunkOutput c() {
        return (BaseMediaChunkOutput) Assertions.a((Object) this.c);
    }
}

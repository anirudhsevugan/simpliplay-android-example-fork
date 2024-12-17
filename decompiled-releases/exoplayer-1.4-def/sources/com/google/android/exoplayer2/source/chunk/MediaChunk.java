package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;

public abstract class MediaChunk extends Chunk {
    public final long m;

    public MediaChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, Object obj, long j, long j2, long j3) {
        super(dataSource, dataSpec, 1, format, i, obj, j, j2);
        Assertions.b((Object) format);
        this.m = j3;
    }

    public long g() {
        long j = this.m;
        if (j != -1) {
            return j + 1;
        }
        return -1;
    }

    public abstract boolean h();
}

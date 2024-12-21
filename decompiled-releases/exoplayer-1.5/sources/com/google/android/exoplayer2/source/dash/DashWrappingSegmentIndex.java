package com.google.android.exoplayer2.source.dash;

import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.source.dash.manifest.RangedUri;

public final class DashWrappingSegmentIndex implements DashSegmentIndex {
    private final ChunkIndex a;
    private final long b;

    public DashWrappingSegmentIndex(ChunkIndex chunkIndex, long j) {
        this.a = chunkIndex;
        this.b = j;
    }

    public final long a() {
        return 0;
    }

    public final long a(long j) {
        return this.a.e[(int) j] - this.b;
    }

    public final long a(long j, long j2) {
        return (long) this.a.b(j + this.b);
    }

    public final long b(long j, long j2) {
        return this.a.d[(int) j];
    }

    public final RangedUri b(long j) {
        int i = (int) j;
        return new RangedUri((String) null, this.a.c[i], (long) this.a.b[i]);
    }

    public final boolean b() {
        return true;
    }

    public final long c(long j) {
        return (long) this.a.a;
    }

    public final long c(long j, long j2) {
        return 0;
    }

    public final long d(long j, long j2) {
        return (long) this.a.a;
    }

    public final long e(long j, long j2) {
        return -9223372036854775807L;
    }
}

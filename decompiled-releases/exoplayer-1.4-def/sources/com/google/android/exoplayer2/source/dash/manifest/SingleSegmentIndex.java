package com.google.android.exoplayer2.source.dash.manifest;

import com.google.android.exoplayer2.source.dash.DashSegmentIndex;

final class SingleSegmentIndex implements DashSegmentIndex {
    private final RangedUri a;

    public SingleSegmentIndex(RangedUri rangedUri) {
        this.a = rangedUri;
    }

    public final long a() {
        return 0;
    }

    public final long a(long j) {
        return 0;
    }

    public final long a(long j, long j2) {
        return 0;
    }

    public final long b(long j, long j2) {
        return j2;
    }

    public final RangedUri b(long j) {
        return this.a;
    }

    public final boolean b() {
        return true;
    }

    public final long c(long j) {
        return 1;
    }

    public final long c(long j, long j2) {
        return 0;
    }

    public final long d(long j, long j2) {
        return 1;
    }

    public final long e(long j, long j2) {
        return -9223372036854775807L;
    }
}

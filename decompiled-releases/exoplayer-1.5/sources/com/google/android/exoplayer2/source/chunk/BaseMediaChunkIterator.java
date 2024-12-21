package com.google.android.exoplayer2.source.chunk;

import java.util.NoSuchElementException;

public abstract class BaseMediaChunkIterator implements MediaChunkIterator {
    private final long b;
    private final long c;
    private long d;

    public BaseMediaChunkIterator(long j, long j2) {
        this.b = j;
        this.c = j2;
        this.d = j - 1;
    }

    public final boolean a() {
        long j = this.d + 1;
        this.d = j;
        return !((j > this.c ? 1 : (j == this.c ? 0 : -1)) > 0);
    }

    /* access modifiers changed from: protected */
    public final void b() {
        long j = this.d;
        if (j < this.b || j > this.c) {
            throw new NoSuchElementException();
        }
    }

    /* access modifiers changed from: protected */
    public final long c() {
        return this.d;
    }
}

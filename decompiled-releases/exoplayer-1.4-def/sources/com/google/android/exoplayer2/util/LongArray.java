package com.google.android.exoplayer2.util;

import java.util.Arrays;

public final class LongArray {
    public int a;
    public long[] b;

    public LongArray() {
        this((byte) 0);
    }

    private LongArray(byte b2) {
        this.b = new long[32];
    }

    public final long a(int i) {
        if (i >= 0 && i < this.a) {
            return this.b[i];
        }
        throw new IndexOutOfBoundsException(new StringBuilder(46).append("Invalid index ").append(i).append(", size is ").append(this.a).toString());
    }

    public final void a(long j) {
        int i = this.a;
        long[] jArr = this.b;
        if (i == jArr.length) {
            this.b = Arrays.copyOf(jArr, i << 1);
        }
        long[] jArr2 = this.b;
        int i2 = this.a;
        this.a = i2 + 1;
        jArr2[i2] = j;
    }
}

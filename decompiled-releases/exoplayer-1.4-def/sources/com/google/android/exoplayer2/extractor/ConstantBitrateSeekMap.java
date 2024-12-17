package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.util.Util;

public class ConstantBitrateSeekMap implements SeekMap {
    private final long a;
    private final long b;
    private final int c;
    private final long d;
    private final int e;
    private final long f;

    public ConstantBitrateSeekMap(long j, long j2, int i, int i2) {
        long a2;
        this.a = j;
        this.b = j2;
        this.c = i2 == -1 ? 1 : i2;
        this.e = i;
        if (j == -1) {
            this.d = -1;
            a2 = -9223372036854775807L;
        } else {
            this.d = j - j2;
            a2 = a(j, j2, i);
        }
        this.f = a2;
    }

    private static long a(long j, long j2, int i) {
        return ((Math.max(0, j - j2) << 3) * 1000000) / ((long) i);
    }

    public final SeekMap.SeekPoints a(long j) {
        long j2 = this.d;
        if (j2 == -1) {
            return new SeekMap.SeekPoints(new SeekPoint(0, this.b));
        }
        int i = this.c;
        long a2 = this.b + Util.a((((((long) this.e) * j) / 8000000) / ((long) i)) * ((long) i), 0, j2 - ((long) i));
        long b2 = b(a2);
        SeekPoint seekPoint = new SeekPoint(b2, a2);
        if (b2 < j) {
            int i2 = this.c;
            if (((long) i2) + a2 < this.a) {
                long j3 = a2 + ((long) i2);
                return new SeekMap.SeekPoints(seekPoint, new SeekPoint(b(j3), j3));
            }
        }
        return new SeekMap.SeekPoints(seekPoint);
    }

    public final boolean a() {
        return this.d != -1;
    }

    public final long b() {
        return this.f;
    }

    public final long b(long j) {
        return a(j, this.b, this.e);
    }
}

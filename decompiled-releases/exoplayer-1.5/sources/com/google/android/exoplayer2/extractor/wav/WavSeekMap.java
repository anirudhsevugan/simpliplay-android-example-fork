package com.google.android.exoplayer2.extractor.wav;

import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.Util;

final class WavSeekMap implements SeekMap {
    private final WavHeader a;
    private final int b;
    private final long c;
    private final long d;
    private final long e;

    public WavSeekMap(WavHeader wavHeader, int i, long j, long j2) {
        this.a = wavHeader;
        this.b = i;
        this.c = j;
        long j3 = (j2 - j) / ((long) wavHeader.d);
        this.d = j3;
        this.e = b(j3);
    }

    private long b(long j) {
        return Util.b(j * ((long) this.b), 1000000, (long) this.a.c);
    }

    public final SeekMap.SeekPoints a(long j) {
        long a2 = Util.a((((long) this.a.c) * j) / (((long) this.b) * 1000000), 0, this.d - 1);
        long j2 = this.c + (((long) this.a.d) * a2);
        long b2 = b(a2);
        SeekPoint seekPoint = new SeekPoint(b2, j2);
        if (b2 >= j || a2 == this.d - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        long j3 = a2 + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(b(j3), this.c + (((long) this.a.d) * j3)));
    }

    public final boolean a() {
        return true;
    }

    public final long b() {
        return this.e;
    }
}

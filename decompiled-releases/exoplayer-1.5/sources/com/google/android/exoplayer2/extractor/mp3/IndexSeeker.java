package com.google.android.exoplayer2.extractor.mp3;

import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.Util;

final class IndexSeeker implements Seeker {
    final LongArray a;
    final LongArray b;
    long c;
    private final long d;

    public final SeekMap.SeekPoints a(long j) {
        int a2 = Util.a(this.a, j);
        SeekPoint seekPoint = new SeekPoint(this.a.a(a2), this.b.a(a2));
        if (seekPoint.b == j || a2 == this.a.a - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        int i = a2 + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(this.a.a(i), this.b.a(i)));
    }

    public final boolean a() {
        return true;
    }

    public final long b() {
        return this.c;
    }

    public final boolean b(long j) {
        LongArray longArray = this.a;
        return j - longArray.a(longArray.a - 1) < 100000;
    }

    public final long c() {
        return this.d;
    }

    public final long c(long j) {
        return this.a.a(Util.a(this.b, j));
    }
}

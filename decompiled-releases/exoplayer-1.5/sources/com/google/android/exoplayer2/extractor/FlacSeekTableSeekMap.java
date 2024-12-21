package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

public final class FlacSeekTableSeekMap implements SeekMap {
    private final FlacStreamMetadata a;
    private final long b;

    public FlacSeekTableSeekMap(FlacStreamMetadata flacStreamMetadata, long j) {
        this.a = flacStreamMetadata;
        this.b = j;
    }

    private SeekPoint a(long j, long j2) {
        return new SeekPoint((j * 1000000) / ((long) this.a.e), this.b + j2);
    }

    public final SeekMap.SeekPoints a(long j) {
        Assertions.a((Object) this.a.k);
        long[] jArr = this.a.k.a;
        long[] jArr2 = this.a.k.b;
        int a2 = Util.a(jArr, this.a.a(j), false);
        long j2 = 0;
        long j3 = a2 == -1 ? 0 : jArr[a2];
        if (a2 != -1) {
            j2 = jArr2[a2];
        }
        SeekPoint a3 = a(j3, j2);
        if (a3.b == j || a2 == jArr.length - 1) {
            return new SeekMap.SeekPoints(a3);
        }
        int i = a2 + 1;
        return new SeekMap.SeekPoints(a3, a(jArr[i], jArr2[i]));
    }

    public final boolean a() {
        return true;
    }

    public final long b() {
        return this.a.a();
    }
}

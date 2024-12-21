package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

public final class IndexSeekMap implements SeekMap {
    private final long[] a;
    private final long[] b;
    private final long c;
    private final boolean d;

    public IndexSeekMap(long[] jArr, long[] jArr2, long j) {
        Assertions.a(jArr.length == jArr2.length);
        int length = jArr2.length;
        boolean z = length > 0;
        this.d = z;
        if (!z || jArr2[0] <= 0) {
            this.a = jArr;
            this.b = jArr2;
        } else {
            int i = length + 1;
            long[] jArr3 = new long[i];
            this.a = jArr3;
            long[] jArr4 = new long[i];
            this.b = jArr4;
            System.arraycopy(jArr, 0, jArr3, 1, length);
            System.arraycopy(jArr2, 0, jArr4, 1, length);
        }
        this.c = j;
    }

    public final SeekMap.SeekPoints a(long j) {
        if (!this.d) {
            return new SeekMap.SeekPoints(SeekPoint.a);
        }
        int a2 = Util.a(this.b, j, true);
        SeekPoint seekPoint = new SeekPoint(this.b[a2], this.a[a2]);
        if (seekPoint.b == j || a2 == this.b.length - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        int i = a2 + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(this.b[i], this.a[i]));
    }

    public final boolean a() {
        return this.d;
    }

    public final long b() {
        return this.c;
    }
}

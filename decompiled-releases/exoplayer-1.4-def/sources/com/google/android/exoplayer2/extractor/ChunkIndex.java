package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public final class ChunkIndex implements SeekMap {
    public final int a;
    public final int[] b;
    public final long[] c;
    public final long[] d;
    public final long[] e;
    private final long f;

    public ChunkIndex(int[] iArr, long[] jArr, long[] jArr2, long[] jArr3) {
        this.b = iArr;
        this.c = jArr;
        this.d = jArr2;
        this.e = jArr3;
        int length = iArr.length;
        this.a = length;
        if (length > 0) {
            this.f = jArr2[length - 1] + jArr3[length - 1];
        } else {
            this.f = 0;
        }
    }

    public final SeekMap.SeekPoints a(long j) {
        int b2 = b(j);
        SeekPoint seekPoint = new SeekPoint(this.e[b2], this.c[b2]);
        if (seekPoint.b >= j || b2 == this.a - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        int i = b2 + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(this.e[i], this.c[i]));
    }

    public final boolean a() {
        return true;
    }

    public final int b(long j) {
        return Util.a(this.e, j, true);
    }

    public final long b() {
        return this.f;
    }

    public final String toString() {
        int i = this.a;
        String arrays = Arrays.toString(this.b);
        String arrays2 = Arrays.toString(this.c);
        String arrays3 = Arrays.toString(this.e);
        String arrays4 = Arrays.toString(this.d);
        return new StringBuilder(String.valueOf(arrays).length() + 71 + String.valueOf(arrays2).length() + String.valueOf(arrays3).length() + String.valueOf(arrays4).length()).append("ChunkIndex(length=").append(i).append(", sizes=").append(arrays).append(", offsets=").append(arrays2).append(", timeUs=").append(arrays3).append(", durationsUs=").append(arrays4).append(")").toString();
    }
}

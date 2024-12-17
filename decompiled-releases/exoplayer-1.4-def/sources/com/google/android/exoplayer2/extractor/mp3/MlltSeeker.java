package com.google.android.exoplayer2.extractor.mp3;

import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.metadata.id3.MlltFrame;
import com.google.android.exoplayer2.util.Util;

final class MlltSeeker implements Seeker {
    private final long[] a;
    private final long[] b;
    private final long c;

    private MlltSeeker(long[] jArr, long[] jArr2, long j) {
        this.a = jArr;
        this.b = jArr2;
        this.c = j == -9223372036854775807L ? C.b(jArr2[jArr2.length - 1]) : j;
    }

    private static Pair a(long j, long[] jArr, long[] jArr2) {
        double d;
        Long valueOf;
        Long valueOf2;
        int a2 = Util.a(jArr, j, true);
        long j2 = jArr[a2];
        long j3 = jArr2[a2];
        int i = a2 + 1;
        if (i == jArr.length) {
            valueOf = Long.valueOf(j2);
            valueOf2 = Long.valueOf(j3);
        } else {
            long j4 = jArr[i];
            long j5 = jArr2[i];
            if (j4 == j2) {
                d = 0.0d;
            } else {
                double d2 = (double) j;
                double d3 = (double) j2;
                Double.isNaN(d2);
                Double.isNaN(d3);
                double d4 = (double) (j4 - j2);
                Double.isNaN(d4);
                d = (d2 - d3) / d4;
            }
            double d5 = (double) (j5 - j3);
            Double.isNaN(d5);
            valueOf = Long.valueOf(j);
            valueOf2 = Long.valueOf(((long) (d * d5)) + j3);
        }
        return Pair.create(valueOf, valueOf2);
    }

    public static MlltSeeker a(long j, MlltFrame mlltFrame, long j2) {
        int length = mlltFrame.d.length;
        int i = length + 1;
        long[] jArr = new long[i];
        long[] jArr2 = new long[i];
        jArr[0] = j;
        long j3 = 0;
        jArr2[0] = 0;
        for (int i2 = 1; i2 <= length; i2++) {
            int i3 = i2 - 1;
            j += (long) (mlltFrame.a + mlltFrame.d[i3]);
            j3 += (long) (mlltFrame.b + mlltFrame.e[i3]);
            jArr[i2] = j;
            jArr2[i2] = j3;
        }
        return new MlltSeeker(jArr, jArr2, j2);
    }

    public final SeekMap.SeekPoints a(long j) {
        Pair a2 = a(C.a(Util.a(j, 0, this.c)), this.b, this.a);
        return new SeekMap.SeekPoints(new SeekPoint(C.b(((Long) a2.first).longValue()), ((Long) a2.second).longValue()));
    }

    public final boolean a() {
        return true;
    }

    public final long b() {
        return this.c;
    }

    public final long c() {
        return -1;
    }

    public final long c(long j) {
        return C.b(((Long) a(j, this.a, this.b).second).longValue());
    }
}

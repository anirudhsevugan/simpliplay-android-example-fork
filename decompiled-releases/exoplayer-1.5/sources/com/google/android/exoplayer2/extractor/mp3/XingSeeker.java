package com.google.android.exoplayer2.extractor.mp3;

import com.google.android.exoplayer2.audio.MpegAudioUtil;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

final class XingSeeker implements Seeker {
    private final long a;
    private final int b;
    private final long c;
    private final long d;
    private final long e;
    private final long[] f;

    private XingSeeker(long j, int i, long j2) {
        this(j, i, j2, -1, (long[]) null);
    }

    private XingSeeker(long j, int i, long j2, long j3, long[] jArr) {
        this.a = j;
        this.b = i;
        this.c = j2;
        this.f = jArr;
        this.d = j3;
        this.e = j3 != -1 ? j + j3 : -1;
    }

    private long a(int i) {
        return (this.c * ((long) i)) / 100;
    }

    public static XingSeeker a(long j, long j2, MpegAudioUtil.Header header, ParsableByteArray parsableByteArray) {
        int o;
        long j3 = j;
        MpegAudioUtil.Header header2 = header;
        int i = header2.g;
        int i2 = header2.d;
        int j4 = parsableByteArray.j();
        if ((j4 & 1) != 1 || (o = parsableByteArray.o()) == 0) {
            return null;
        }
        long b2 = Util.b((long) o, ((long) i) * 1000000, (long) i2);
        if ((j4 & 6) != 6) {
            return new XingSeeker(j2, header2.c, b2);
        }
        long h = parsableByteArray.h();
        long[] jArr = new long[100];
        for (int i3 = 0; i3 < 100; i3++) {
            jArr[i3] = (long) parsableByteArray.c();
        }
        if (j3 != -1) {
            long j5 = j2 + h;
            if (j3 != j5) {
                Log.c("XingSeeker", new StringBuilder(67).append("XING data size mismatch: ").append(j3).append(", ").append(j5).toString());
            }
        }
        return new XingSeeker(j2, header2.c, b2, h, jArr);
    }

    public final SeekMap.SeekPoints a(long j) {
        if (!a()) {
            return new SeekMap.SeekPoints(new SeekPoint(0, this.a + ((long) this.b)));
        }
        long a2 = Util.a(j, 0, this.c);
        double d2 = (double) a2;
        Double.isNaN(d2);
        double d3 = (double) this.c;
        Double.isNaN(d3);
        double d4 = (d2 * 100.0d) / d3;
        double d5 = 0.0d;
        if (d4 > 0.0d) {
            if (d4 >= 100.0d) {
                d5 = 256.0d;
            } else {
                int i = (int) d4;
                long[] jArr = (long[]) Assertions.a((Object) this.f);
                double d6 = (double) jArr[i];
                double d7 = i == 99 ? 256.0d : (double) jArr[i + 1];
                double d8 = (double) i;
                Double.isNaN(d8);
                Double.isNaN(d6);
                Double.isNaN(d6);
                d5 = d6 + ((d4 - d8) * (d7 - d6));
            }
        }
        double d9 = (double) this.d;
        Double.isNaN(d9);
        return new SeekMap.SeekPoints(new SeekPoint(a2, this.a + Util.a(Math.round((d5 / 256.0d) * d9), (long) this.b, this.d - 1)));
    }

    public final boolean a() {
        return this.f != null;
    }

    public final long b() {
        return this.c;
    }

    public final long c() {
        return this.e;
    }

    public final long c(long j) {
        double d2;
        long j2 = j - this.a;
        if (!a() || j2 <= ((long) this.b)) {
            return 0;
        }
        long[] jArr = (long[]) Assertions.a((Object) this.f);
        double d3 = (double) j2;
        Double.isNaN(d3);
        double d4 = (double) this.d;
        Double.isNaN(d4);
        double d5 = (d3 * 256.0d) / d4;
        int a2 = Util.a(jArr, (long) d5, true);
        long a3 = a(a2);
        long j3 = jArr[a2];
        int i = a2 + 1;
        long a4 = a(i);
        long j4 = a2 == 99 ? 256 : jArr[i];
        if (j3 == j4) {
            d2 = 0.0d;
        } else {
            double d6 = (double) j3;
            Double.isNaN(d6);
            double d7 = (double) (j4 - j3);
            Double.isNaN(d7);
            d2 = (d5 - d6) / d7;
        }
        double d8 = (double) (a4 - a3);
        Double.isNaN(d8);
        return a3 + Math.round(d2 * d8);
    }
}

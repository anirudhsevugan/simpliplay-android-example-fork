package com.google.android.exoplayer2.extractor.mp3;

import com.google.android.exoplayer2.audio.MpegAudioUtil;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

final class VbriSeeker implements Seeker {
    private final long[] a;
    private final long[] b;
    private final long c;
    private final long d;

    private VbriSeeker(long[] jArr, long[] jArr2, long j, long j2) {
        this.a = jArr;
        this.b = jArr2;
        this.c = j;
        this.d = j2;
    }

    public static VbriSeeker a(long j, long j2, MpegAudioUtil.Header header, ParsableByteArray parsableByteArray) {
        int i;
        long j3 = j;
        MpegAudioUtil.Header header2 = header;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        parsableByteArray2.e(10);
        int j4 = parsableByteArray.j();
        if (j4 <= 0) {
            return null;
        }
        int i2 = header2.d;
        long b2 = Util.b((long) j4, ((long) (i2 >= 32000 ? 1152 : 576)) * 1000000, (long) i2);
        int d2 = parsableByteArray.d();
        int d3 = parsableByteArray.d();
        int d4 = parsableByteArray.d();
        parsableByteArray2.e(2);
        long j5 = j2 + ((long) header2.c);
        long[] jArr = new long[d2];
        long[] jArr2 = new long[d2];
        int i3 = 0;
        long j6 = j2;
        while (i3 < d2) {
            int i4 = d3;
            jArr[i3] = (((long) i3) * b2) / ((long) d2);
            jArr2[i3] = Math.max(j6, j5);
            switch (d4) {
                case 1:
                    i = parsableByteArray.c();
                    break;
                case 2:
                    i = parsableByteArray.d();
                    break;
                case 3:
                    i = parsableByteArray.g();
                    break;
                case 4:
                    i = parsableByteArray.o();
                    break;
                default:
                    return null;
            }
            j6 += (long) (i * i4);
            i3++;
            d3 = i4;
        }
        if (!(j3 == -1 || j3 == j6)) {
            Log.c("VbriSeeker", new StringBuilder(67).append("VBRI data size mismatch: ").append(j3).append(", ").append(j6).toString());
        }
        return new VbriSeeker(jArr, jArr2, b2, j6);
    }

    public final SeekMap.SeekPoints a(long j) {
        int a2 = Util.a(this.a, j, true);
        SeekPoint seekPoint = new SeekPoint(this.a[a2], this.b[a2]);
        if (seekPoint.b >= j || a2 == this.a.length - 1) {
            return new SeekMap.SeekPoints(seekPoint);
        }
        int i = a2 + 1;
        return new SeekMap.SeekPoints(seekPoint, new SeekPoint(this.a[i], this.b[i]));
    }

    public final boolean a() {
        return true;
    }

    public final long b() {
        return this.c;
    }

    public final long c() {
        return this.d;
    }

    public final long c(long j) {
        return this.a[Util.a(this.b, j, true)];
    }
}

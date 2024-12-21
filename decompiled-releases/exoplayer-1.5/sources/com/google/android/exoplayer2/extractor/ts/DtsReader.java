package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.ParsableByteArray;

public final class DtsReader implements ElementaryStreamReader {
    private final ParsableByteArray a = new ParsableByteArray(new byte[18]);
    private final String b;
    private String c;
    private TrackOutput d;
    private int e = 0;
    private int f;
    private int g;
    private long h;
    private Format i;
    private int j;
    private long k;

    public DtsReader(String str) {
        this.b = str;
    }

    public final void a() {
        this.e = 0;
        this.f = 0;
        this.g = 0;
    }

    public final void a(long j2, int i2) {
        this.k = j2;
    }

    public final void a(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.a();
        this.c = trackIdGenerator.c();
        this.d = extractorOutput.a(trackIdGenerator.b(), 1);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00af, code lost:
        if (r12.a() <= 0) goto L_0x00e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00b1, code lost:
        r0 = r11.g << 8;
        r11.g = r0;
        r0 = r0 | r12.c();
        r11.g = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00c4, code lost:
        r0 = r11.a.a;
        r4 = r11.g;
        r0[0] = (byte) (r4 >>> 24);
        r0[1] = (byte) (r4 >> 16);
        r0[2] = (byte) (r4 >> 8);
        r0[3] = (byte) r4;
        r11.f = 4;
        r11.g = 0;
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00e3, code lost:
        if (r3 == false) goto L_0x0005;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00e5, code lost:
        r11.e = 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.android.exoplayer2.util.ParsableByteArray r12) {
        /*
            r11 = this;
            com.google.android.exoplayer2.extractor.TrackOutput r0 = r11.d
            com.google.android.exoplayer2.util.Assertions.a((java.lang.Object) r0)
        L_0x0005:
            int r0 = r12.a()
            if (r0 <= 0) goto L_0x00e9
            int r0 = r11.e
            r1 = 2
            r2 = 1
            r3 = 0
            switch(r0) {
                case 0: goto L_0x00ab;
                case 1: goto L_0x0048;
                case 2: goto L_0x0019;
                default: goto L_0x0013;
            }
        L_0x0013:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            r12.<init>()
            throw r12
        L_0x0019:
            int r0 = r12.a()
            int r1 = r11.j
            int r2 = r11.f
            int r1 = r1 - r2
            int r0 = java.lang.Math.min(r0, r1)
            com.google.android.exoplayer2.extractor.TrackOutput r1 = r11.d
            r1.b(r12, r0)
            int r1 = r11.f
            int r1 = r1 + r0
            r11.f = r1
            int r8 = r11.j
            if (r1 != r8) goto L_0x0005
            com.google.android.exoplayer2.extractor.TrackOutput r4 = r11.d
            long r5 = r11.k
            r7 = 1
            r9 = 0
            r10 = 0
            r4.a(r5, r7, r8, r9, r10)
            long r0 = r11.k
            long r4 = r11.h
            long r0 = r0 + r4
            r11.k = r0
            r11.e = r3
            goto L_0x0005
        L_0x0048:
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r11.a
            byte[] r0 = r0.a
            int r4 = r12.a()
            int r5 = r11.f
            r6 = 18
            int r5 = 18 - r5
            int r4 = java.lang.Math.min(r4, r5)
            int r5 = r11.f
            r12.a(r0, r5, r4)
            int r0 = r11.f
            int r0 = r0 + r4
            r11.f = r0
            if (r0 != r6) goto L_0x0067
            goto L_0x0068
        L_0x0067:
            r2 = 0
        L_0x0068:
            if (r2 == 0) goto L_0x0005
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r11.a
            byte[] r0 = r0.a
            com.google.android.exoplayer2.Format r2 = r11.i
            if (r2 != 0) goto L_0x0081
            java.lang.String r2 = r11.c
            java.lang.String r4 = r11.b
            com.google.android.exoplayer2.Format r2 = com.google.android.exoplayer2.audio.DtsUtil.a(r0, r2, r4)
            r11.i = r2
            com.google.android.exoplayer2.extractor.TrackOutput r4 = r11.d
            r4.a(r2)
        L_0x0081:
            int r2 = com.google.android.exoplayer2.audio.DtsUtil.b(r0)
            r11.j = r2
            int r0 = com.google.android.exoplayer2.audio.DtsUtil.a((byte[]) r0)
            long r4 = (long) r0
            r7 = 1000000(0xf4240, double:4.940656E-318)
            long r4 = r4 * r7
            com.google.android.exoplayer2.Format r0 = r11.i
            int r0 = r0.x
            long r7 = (long) r0
            long r4 = r4 / r7
            int r0 = (int) r4
            long r4 = (long) r0
            r11.h = r4
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r11.a
            r0.d(r3)
            com.google.android.exoplayer2.extractor.TrackOutput r0 = r11.d
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r11.a
            r0.b(r2, r6)
            r11.e = r1
            goto L_0x0005
        L_0x00ab:
            int r0 = r12.a()
            if (r0 <= 0) goto L_0x00e3
            int r0 = r11.g
            int r0 = r0 << 8
            r11.g = r0
            int r4 = r12.c()
            r0 = r0 | r4
            r11.g = r0
            boolean r0 = com.google.android.exoplayer2.audio.DtsUtil.a((int) r0)
            if (r0 == 0) goto L_0x00ab
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r11.a
            byte[] r0 = r0.a
            int r4 = r11.g
            int r5 = r4 >>> 24
            byte r5 = (byte) r5
            r0[r3] = r5
            int r5 = r4 >> 16
            byte r5 = (byte) r5
            r0[r2] = r5
            int r5 = r4 >> 8
            byte r5 = (byte) r5
            r0[r1] = r5
            r1 = 3
            byte r4 = (byte) r4
            r0[r1] = r4
            r0 = 4
            r11.f = r0
            r11.g = r3
            r3 = 1
        L_0x00e3:
            if (r3 == 0) goto L_0x0005
            r11.e = r2
            goto L_0x0005
        L_0x00e9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.DtsReader.a(com.google.android.exoplayer2.util.ParsableByteArray):void");
    }

    public final void b() {
    }
}

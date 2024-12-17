package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;

public final class LatmReader implements ElementaryStreamReader {
    private final String a;
    private final ParsableByteArray b;
    private final ParsableBitArray c;
    private TrackOutput d;
    private String e;
    private Format f;
    private int g;
    private int h;
    private int i;
    private int j;
    private long k;
    private boolean l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private long q;
    private int r;
    private long s;
    private int t;
    private String u;

    public LatmReader(String str) {
        this.a = str;
        ParsableByteArray parsableByteArray = new ParsableByteArray(1024);
        this.b = parsableByteArray;
        this.c = new ParsableBitArray(parsableByteArray.a);
    }

    private int a(ParsableBitArray parsableBitArray) {
        int a2 = parsableBitArray.a();
        AacUtil.Config a3 = AacUtil.a(parsableBitArray, true);
        this.u = a3.c;
        this.r = a3.a;
        this.t = a3.b;
        return a2 - parsableBitArray.a();
    }

    private int b(ParsableBitArray parsableBitArray) {
        int c2;
        if (this.o == 0) {
            int i2 = 0;
            do {
                c2 = parsableBitArray.c(8);
                i2 += c2;
            } while (c2 == 255);
            return i2;
        }
        throw new ParserException();
    }

    private static long c(ParsableBitArray parsableBitArray) {
        return (long) parsableBitArray.c((parsableBitArray.c(2) + 1) << 3);
    }

    public final void a() {
        this.g = 0;
        this.l = false;
    }

    public final void a(long j2, int i2) {
        this.k = j2;
    }

    public final void a(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.a();
        this.d = extractorOutput.a(trackIdGenerator.b(), 1);
        this.e = trackIdGenerator.c();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0140, code lost:
        if (r12.l != false) goto L_0x0142;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01d8, code lost:
        r12.g = 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.android.exoplayer2.util.ParsableByteArray r13) {
        /*
            r12 = this;
            com.google.android.exoplayer2.extractor.TrackOutput r0 = r12.d
            com.google.android.exoplayer2.util.Assertions.a((java.lang.Object) r0)
        L_0x0005:
            int r0 = r13.a()
            if (r0 <= 0) goto L_0x01e6
            int r0 = r12.g
            r1 = 86
            r2 = 3
            r3 = 0
            r4 = 8
            r5 = 1
            switch(r0) {
                case 0: goto L_0x01dc;
                case 1: goto L_0x01c5;
                case 2: goto L_0x019b;
                case 3: goto L_0x001d;
                default: goto L_0x0017;
            }
        L_0x0017:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            r13.<init>()
            throw r13
        L_0x001d:
            int r0 = r13.a()
            int r1 = r12.i
            int r6 = r12.h
            int r1 = r1 - r6
            int r0 = java.lang.Math.min(r0, r1)
            com.google.android.exoplayer2.util.ParsableBitArray r1 = r12.c
            byte[] r1 = r1.a
            int r6 = r12.h
            r13.a(r1, r6, r0)
            int r1 = r12.h
            int r1 = r1 + r0
            r12.h = r1
            int r0 = r12.i
            if (r1 != r0) goto L_0x0005
            com.google.android.exoplayer2.util.ParsableBitArray r0 = r12.c
            r0.a(r3)
            com.google.android.exoplayer2.util.ParsableBitArray r0 = r12.c
            boolean r1 = r0.e()
            if (r1 != 0) goto L_0x013e
            r12.l = r5
            int r1 = r0.c(r5)
            if (r1 != r5) goto L_0x0056
            int r6 = r0.c(r5)
            goto L_0x0057
        L_0x0056:
            r6 = 0
        L_0x0057:
            r12.m = r6
            if (r6 != 0) goto L_0x0138
            if (r1 != r5) goto L_0x0060
            c(r0)
        L_0x0060:
            boolean r6 = r0.e()
            if (r6 == 0) goto L_0x0132
            r6 = 6
            int r7 = r0.c(r6)
            r12.n = r7
            r7 = 4
            int r7 = r0.c(r7)
            int r8 = r0.c(r2)
            if (r7 != 0) goto L_0x012c
            if (r8 != 0) goto L_0x012c
            if (r1 != 0) goto L_0x00cf
            int r7 = r0.b()
            int r8 = r12.a((com.google.android.exoplayer2.util.ParsableBitArray) r0)
            r0.a(r7)
            int r7 = r8 + 7
            int r7 = r7 / r4
            byte[] r7 = new byte[r7]
            r0.b(r7, r8)
            com.google.android.exoplayer2.Format$Builder r8 = new com.google.android.exoplayer2.Format$Builder
            r8.<init>()
            java.lang.String r9 = r12.e
            r8.a = r9
            java.lang.String r9 = "audio/mp4a-latm"
            r8.k = r9
            java.lang.String r9 = r12.u
            r8.h = r9
            int r9 = r12.t
            r8.x = r9
            int r9 = r12.r
            r8.y = r9
            java.util.List r7 = java.util.Collections.singletonList(r7)
            r8.m = r7
            java.lang.String r7 = r12.a
            r8.c = r7
            com.google.android.exoplayer2.Format r7 = r8.a()
            com.google.android.exoplayer2.Format r8 = r12.f
            boolean r8 = r7.equals(r8)
            if (r8 != 0) goto L_0x00dc
            r12.f = r7
            int r8 = r7.x
            long r8 = (long) r8
            r10 = 1024000000(0x3d090000, double:5.059232213E-315)
            long r10 = r10 / r8
            r12.s = r10
            com.google.android.exoplayer2.extractor.TrackOutput r8 = r12.d
            r8.a(r7)
            goto L_0x00dc
        L_0x00cf:
            long r7 = c(r0)
            int r8 = (int) r7
            int r7 = r12.a((com.google.android.exoplayer2.util.ParsableBitArray) r0)
            int r8 = r8 - r7
            r0.b(r8)
        L_0x00dc:
            int r2 = r0.c(r2)
            r12.o = r2
            switch(r2) {
                case 0: goto L_0x00f9;
                case 1: goto L_0x00f3;
                case 2: goto L_0x00e5;
                case 3: goto L_0x00ef;
                case 4: goto L_0x00ef;
                case 5: goto L_0x00ef;
                case 6: goto L_0x00eb;
                case 7: goto L_0x00eb;
                default: goto L_0x00e5;
            }
        L_0x00e5:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            r13.<init>()
            throw r13
        L_0x00eb:
            r0.b(r5)
            goto L_0x00fc
        L_0x00ef:
            r0.b(r6)
            goto L_0x00fc
        L_0x00f3:
            r2 = 9
            r0.b(r2)
            goto L_0x00fc
        L_0x00f9:
            r0.b(r4)
        L_0x00fc:
            boolean r2 = r0.e()
            r12.p = r2
            r6 = 0
            r12.q = r6
            if (r2 == 0) goto L_0x0122
            if (r1 != r5) goto L_0x0111
            long r1 = c(r0)
            r12.q = r1
            goto L_0x0122
        L_0x0111:
            boolean r1 = r0.e()
            long r5 = r12.q
            long r5 = r5 << r4
            int r2 = r0.c(r4)
            long r7 = (long) r2
            long r5 = r5 + r7
            r12.q = r5
            if (r1 != 0) goto L_0x0111
        L_0x0122:
            boolean r1 = r0.e()
            if (r1 == 0) goto L_0x0142
            r0.b(r4)
            goto L_0x0142
        L_0x012c:
            com.google.android.exoplayer2.ParserException r13 = new com.google.android.exoplayer2.ParserException
            r13.<init>()
            throw r13
        L_0x0132:
            com.google.android.exoplayer2.ParserException r13 = new com.google.android.exoplayer2.ParserException
            r13.<init>()
            throw r13
        L_0x0138:
            com.google.android.exoplayer2.ParserException r13 = new com.google.android.exoplayer2.ParserException
            r13.<init>()
            throw r13
        L_0x013e:
            boolean r1 = r12.l
            if (r1 == 0) goto L_0x01d8
        L_0x0142:
            int r1 = r12.m
            if (r1 != 0) goto L_0x0195
            int r1 = r12.n
            if (r1 != 0) goto L_0x018f
            int r8 = r12.b(r0)
            int r1 = r0.b()
            r2 = r1 & 7
            if (r2 != 0) goto L_0x015e
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r12.b
            int r1 = r1 >> 3
            r2.d(r1)
            goto L_0x016c
        L_0x015e:
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r12.b
            byte[] r1 = r1.a
            int r2 = r8 << 3
            r0.b(r1, r2)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r12.b
            r1.d(r3)
        L_0x016c:
            com.google.android.exoplayer2.extractor.TrackOutput r1 = r12.d
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r12.b
            r1.b(r2, r8)
            com.google.android.exoplayer2.extractor.TrackOutput r4 = r12.d
            long r5 = r12.k
            r7 = 1
            r9 = 0
            r10 = 0
            r4.a(r5, r7, r8, r9, r10)
            long r1 = r12.k
            long r4 = r12.s
            long r1 = r1 + r4
            r12.k = r1
            boolean r1 = r12.p
            if (r1 == 0) goto L_0x01d8
            long r1 = r12.q
            int r2 = (int) r1
            r0.b(r2)
            goto L_0x01d8
        L_0x018f:
            com.google.android.exoplayer2.ParserException r13 = new com.google.android.exoplayer2.ParserException
            r13.<init>()
            throw r13
        L_0x0195:
            com.google.android.exoplayer2.ParserException r13 = new com.google.android.exoplayer2.ParserException
            r13.<init>()
            throw r13
        L_0x019b:
            int r0 = r12.j
            r0 = r0 & -225(0xffffffffffffff1f, float:NaN)
            int r0 = r0 << r4
            int r1 = r13.c()
            r0 = r0 | r1
            r12.i = r0
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r12.b
            byte[] r1 = r1.a
            int r1 = r1.length
            if (r0 <= r1) goto L_0x01bf
            int r0 = r12.i
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r12.b
            r1.a(r0)
            com.google.android.exoplayer2.util.ParsableBitArray r0 = r12.c
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r12.b
            byte[] r1 = r1.a
            int r4 = r1.length
            r0.a((byte[]) r1, (int) r4)
        L_0x01bf:
            r12.h = r3
            r12.g = r2
            goto L_0x0005
        L_0x01c5:
            int r0 = r13.c()
            r2 = r0 & 224(0xe0, float:3.14E-43)
            r4 = 224(0xe0, float:3.14E-43)
            if (r2 != r4) goto L_0x01d6
            r12.j = r0
            r0 = 2
            r12.g = r0
            goto L_0x0005
        L_0x01d6:
            if (r0 == r1) goto L_0x0005
        L_0x01d8:
            r12.g = r3
            goto L_0x0005
        L_0x01dc:
            int r0 = r13.c()
            if (r0 != r1) goto L_0x0005
            r12.g = r5
            goto L_0x0005
        L_0x01e6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.LatmReader.a(com.google.android.exoplayer2.util.ParsableByteArray):void");
    }

    public final void b() {
    }
}

package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;

public final class H265Reader implements ElementaryStreamReader {
    private final SeiReader a;
    private String b;
    private TrackOutput c;
    private SampleReader d;
    private boolean e;
    private final boolean[] f = new boolean[3];
    private final NalUnitTargetBuffer g = new NalUnitTargetBuffer(32);
    private final NalUnitTargetBuffer h = new NalUnitTargetBuffer(33);
    private final NalUnitTargetBuffer i = new NalUnitTargetBuffer(34);
    private final NalUnitTargetBuffer j = new NalUnitTargetBuffer(39);
    private final NalUnitTargetBuffer k = new NalUnitTargetBuffer(40);
    private long l;
    private long m;
    private final ParsableByteArray n = new ParsableByteArray();

    final class SampleReader {
        long a;
        boolean b;
        int c;
        long d;
        boolean e;
        boolean f;
        boolean g;
        boolean h;
        boolean i;
        long j;
        long k;
        boolean l;
        private final TrackOutput m;

        public SampleReader(TrackOutput trackOutput) {
            this.m = trackOutput;
        }

        static boolean b(int i2) {
            return (32 <= i2 && i2 <= 35) || i2 == 39;
        }

        static boolean c(int i2) {
            return i2 < 32 || i2 == 40;
        }

        /* access modifiers changed from: package-private */
        public final void a(int i2) {
            boolean z = this.l;
            int i3 = (int) (this.a - this.j);
            this.m.a(this.k, z ? 1 : 0, i3, i2, (TrackOutput.CryptoData) null);
        }

        public final void a(byte[] bArr, int i2, int i3) {
            if (this.e) {
                int i4 = this.c;
                int i5 = (i2 + 2) - i4;
                if (i5 < i3) {
                    this.f = (bArr[i5] & 128) != 0;
                    this.e = false;
                    return;
                }
                this.c = i4 + (i3 - i2);
            }
        }
    }

    public H265Reader(SeiReader seiReader) {
        this.a = seiReader;
    }

    private void a(byte[] bArr, int i2, int i3) {
        this.d.a(bArr, i2, i3);
        if (!this.e) {
            this.g.a(bArr, i2, i3);
            this.h.a(bArr, i2, i3);
            this.i.a(bArr, i2, i3);
        }
        this.j.a(bArr, i2, i3);
        this.k.a(bArr, i2, i3);
    }

    public final void a() {
        this.l = 0;
        NalUnitUtil.a(this.f);
        this.g.a();
        this.h.a();
        this.i.a();
        this.j.a();
        this.k.a();
        SampleReader sampleReader = this.d;
        if (sampleReader != null) {
            sampleReader.e = false;
            sampleReader.f = false;
            sampleReader.g = false;
            sampleReader.h = false;
            sampleReader.i = false;
        }
    }

    public final void a(long j2, int i2) {
        this.m = j2;
    }

    public final void a(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.a();
        this.b = trackIdGenerator.c();
        TrackOutput a2 = extractorOutput.a(trackIdGenerator.b(), 2);
        this.c = a2;
        this.d = new SampleReader(a2);
        this.a.a(extractorOutput, trackIdGenerator);
    }

    /* JADX WARNING: Removed duplicated region for block: B:157:0x0351  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x037a  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x03b4  */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x03d9  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x03e5  */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x03e7  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x03f3  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x03f5  */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x03fc  */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x040b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.android.exoplayer2.util.ParsableByteArray r29) {
        /*
            r28 = this;
            r0 = r28
            r1 = r29
            com.google.android.exoplayer2.extractor.TrackOutput r2 = r0.c
            com.google.android.exoplayer2.util.Assertions.a((java.lang.Object) r2)
            com.google.android.exoplayer2.extractor.ts.H265Reader$SampleReader r2 = r0.d
            com.google.android.exoplayer2.util.Util.a((java.lang.Object) r2)
        L_0x000e:
            int r2 = r29.a()
            if (r2 <= 0) goto L_0x0423
            int r2 = r1.b
            int r3 = r1.c
            byte[] r4 = r1.a
            long r5 = r0.l
            int r7 = r29.a()
            long r7 = (long) r7
            long r5 = r5 + r7
            r0.l = r5
            com.google.android.exoplayer2.extractor.TrackOutput r5 = r0.c
            int r6 = r29.a()
            r5.b(r1, r6)
        L_0x002d:
            if (r2 >= r3) goto L_0x041f
            boolean[] r5 = r0.f
            int r5 = com.google.android.exoplayer2.util.NalUnitUtil.a(r4, r2, r3, r5)
            if (r5 != r3) goto L_0x003b
            r0.a(r4, r2, r3)
            return
        L_0x003b:
            int r6 = com.google.android.exoplayer2.util.NalUnitUtil.c(r4, r5)
            int r7 = r5 - r2
            if (r7 <= 0) goto L_0x0046
            r0.a(r4, r2, r5)
        L_0x0046:
            int r2 = r3 - r5
            long r8 = r0.l
            long r10 = (long) r2
            long r8 = r8 - r10
            if (r7 >= 0) goto L_0x0050
            int r7 = -r7
            goto L_0x0051
        L_0x0050:
            r7 = 0
        L_0x0051:
            long r11 = r0.m
            com.google.android.exoplayer2.extractor.ts.H265Reader$SampleReader r13 = r0.d
            boolean r14 = r0.e
            boolean r15 = r13.i
            r10 = 1
            if (r15 == 0) goto L_0x0068
            boolean r15 = r13.f
            if (r15 == 0) goto L_0x0068
            boolean r14 = r13.b
            r13.l = r14
            r14 = 0
            r13.i = r14
            goto L_0x008d
        L_0x0068:
            boolean r15 = r13.g
            if (r15 != 0) goto L_0x0070
            boolean r15 = r13.f
            if (r15 == 0) goto L_0x008d
        L_0x0070:
            if (r14 == 0) goto L_0x007f
            boolean r14 = r13.h
            if (r14 == 0) goto L_0x007f
            long r14 = r13.a
            long r14 = r8 - r14
            int r15 = (int) r14
            int r15 = r15 + r2
            r13.a(r15)
        L_0x007f:
            long r14 = r13.a
            r13.j = r14
            long r14 = r13.d
            r13.k = r14
            boolean r14 = r13.b
            r13.l = r14
            r13.h = r10
        L_0x008d:
            boolean r13 = r0.e
            if (r13 != 0) goto L_0x033c
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r13 = r0.g
            r13.b(r7)
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r13 = r0.h
            r13.b(r7)
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r13 = r0.i
            r13.b(r7)
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r13 = r0.g
            boolean r13 = r13.a
            if (r13 == 0) goto L_0x033c
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r13 = r0.h
            boolean r13 = r13.a
            if (r13 == 0) goto L_0x033c
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r13 = r0.i
            boolean r13 = r13.a
            if (r13 == 0) goto L_0x033c
            com.google.android.exoplayer2.extractor.TrackOutput r13 = r0.c
            java.lang.String r15 = r0.b
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r14 = r0.g
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r10 = r0.h
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r1 = r0.i
            r16 = r3
            int r3 = r14.c
            r17 = r4
            int r4 = r10.c
            int r3 = r3 + r4
            int r4 = r1.c
            int r3 = r3 + r4
            byte[] r3 = new byte[r3]
            byte[] r4 = r14.b
            r18 = r5
            int r5 = r14.c
            r19 = r2
            r2 = 0
            java.lang.System.arraycopy(r4, r2, r3, r2, r5)
            byte[] r4 = r10.b
            int r5 = r14.c
            r20 = r6
            int r6 = r10.c
            java.lang.System.arraycopy(r4, r2, r3, r5, r6)
            byte[] r4 = r1.b
            int r5 = r14.c
            int r6 = r10.c
            int r5 = r5 + r6
            int r1 = r1.c
            java.lang.System.arraycopy(r4, r2, r3, r5, r1)
            com.google.android.exoplayer2.util.ParsableNalUnitBitArray r1 = new com.google.android.exoplayer2.util.ParsableNalUnitBitArray
            byte[] r4 = r10.b
            int r5 = r10.c
            r1.<init>(r4, r2, r5)
            r2 = 44
            r1.a(r2)
            r2 = 3
            int r4 = r1.c(r2)
            r1.a()
            r5 = 88
            r1.a(r5)
            r5 = 8
            r1.a(r5)
            r6 = 0
            r14 = 0
        L_0x010f:
            if (r6 >= r4) goto L_0x0124
            boolean r21 = r1.b()
            if (r21 == 0) goto L_0x0119
            int r14 = r14 + 89
        L_0x0119:
            boolean r21 = r1.b()
            if (r21 == 0) goto L_0x0121
            int r14 = r14 + 8
        L_0x0121:
            int r6 = r6 + 1
            goto L_0x010f
        L_0x0124:
            r1.a(r14)
            r6 = 2
            if (r4 <= 0) goto L_0x0131
            int r14 = 8 - r4
            int r14 = r14 * 2
            r1.a(r14)
        L_0x0131:
            r1.f()
            int r14 = r1.f()
            if (r14 != r2) goto L_0x013d
            r1.a()
        L_0x013d:
            int r21 = r1.f()
            int r22 = r1.f()
            boolean r23 = r1.b()
            if (r23 == 0) goto L_0x0177
            int r23 = r1.f()
            int r24 = r1.f()
            int r25 = r1.f()
            int r26 = r1.f()
            r5 = 1
            if (r14 == r5) goto L_0x0164
            if (r14 != r6) goto L_0x0161
            goto L_0x0164
        L_0x0161:
            r27 = 1
            goto L_0x0166
        L_0x0164:
            r27 = 2
        L_0x0166:
            if (r14 != r5) goto L_0x016a
            r5 = 2
            goto L_0x016b
        L_0x016a:
            r5 = 1
        L_0x016b:
            int r23 = r23 + r24
            int r27 = r27 * r23
            int r21 = r21 - r27
            int r25 = r25 + r26
            int r5 = r5 * r25
            int r22 = r22 - r5
        L_0x0177:
            r5 = r21
            r1.f()
            r1.f()
            int r14 = r1.f()
            boolean r21 = r1.b()
            if (r21 == 0) goto L_0x018c
            r21 = 0
            goto L_0x018e
        L_0x018c:
            r21 = r4
        L_0x018e:
            r6 = r21
        L_0x0190:
            r1.f()
            r1.f()
            r1.f()
            if (r6 > r4) goto L_0x019e
            int r6 = r6 + 1
            goto L_0x0190
        L_0x019e:
            r1.f()
            r1.f()
            r1.f()
            boolean r4 = r1.b()
            r6 = 4
            if (r4 == 0) goto L_0x01f8
            boolean r4 = r1.b()
            if (r4 == 0) goto L_0x01f8
            r4 = 0
        L_0x01b5:
            if (r4 >= r6) goto L_0x01f8
            r2 = 0
        L_0x01b8:
            r6 = 6
            if (r2 >= r6) goto L_0x01f0
            boolean r6 = r1.b()
            if (r6 != 0) goto L_0x01c8
            r1.f()
            r25 = r8
        L_0x01c6:
            r6 = 3
            goto L_0x01e7
        L_0x01c8:
            int r6 = r4 << 1
            r24 = 4
            int r6 = r6 + 4
            r25 = r8
            r8 = 1
            int r6 = r8 << r6
            r9 = 64
            int r6 = java.lang.Math.min(r9, r6)
            if (r4 <= r8) goto L_0x01de
            r1.e()
        L_0x01de:
            r8 = 0
        L_0x01df:
            if (r8 >= r6) goto L_0x01c6
            r1.e()
            int r8 = r8 + 1
            goto L_0x01df
        L_0x01e7:
            if (r4 != r6) goto L_0x01eb
            r8 = 3
            goto L_0x01ec
        L_0x01eb:
            r8 = 1
        L_0x01ec:
            int r2 = r2 + r8
            r8 = r25
            goto L_0x01b8
        L_0x01f0:
            r25 = r8
            r6 = 3
            int r4 = r4 + 1
            r2 = 3
            r6 = 4
            goto L_0x01b5
        L_0x01f8:
            r25 = r8
            r2 = 2
            r1.a(r2)
            boolean r2 = r1.b()
            if (r2 == 0) goto L_0x0212
            r2 = 8
            r1.a(r2)
            r1.f()
            r1.f()
            r1.a()
        L_0x0212:
            int r2 = r1.f()
            r4 = 0
            r6 = 0
            r8 = 0
        L_0x0219:
            if (r4 >= r2) goto L_0x0266
            if (r4 == 0) goto L_0x0221
            boolean r6 = r1.b()
        L_0x0221:
            if (r6 == 0) goto L_0x023b
            r1.a()
            r1.f()
            r9 = 0
        L_0x022a:
            if (r9 > r8) goto L_0x0238
            boolean r21 = r1.b()
            if (r21 == 0) goto L_0x0235
            r1.a()
        L_0x0235:
            int r9 = r9 + 1
            goto L_0x022a
        L_0x0238:
            r27 = r2
            goto L_0x0261
        L_0x023b:
            int r8 = r1.f()
            int r9 = r1.f()
            int r21 = r8 + r9
            r27 = r2
            r2 = 0
        L_0x0248:
            if (r2 >= r8) goto L_0x0253
            r1.f()
            r1.a()
            int r2 = r2 + 1
            goto L_0x0248
        L_0x0253:
            r2 = 0
        L_0x0254:
            if (r2 >= r9) goto L_0x025f
            r1.f()
            r1.a()
            int r2 = r2 + 1
            goto L_0x0254
        L_0x025f:
            r8 = r21
        L_0x0261:
            int r4 = r4 + 1
            r2 = r27
            goto L_0x0219
        L_0x0266:
            boolean r2 = r1.b()
            if (r2 == 0) goto L_0x027e
            r2 = 0
        L_0x026d:
            int r4 = r1.f()
            if (r2 >= r4) goto L_0x027e
            r4 = 4
            int r6 = r14 + 4
            r4 = 1
            int r6 = r6 + r4
            r1.a(r6)
            int r2 = r2 + 1
            goto L_0x026d
        L_0x027e:
            r2 = 2
            r1.a(r2)
            boolean r2 = r1.b()
            r4 = 24
            r6 = 1065353216(0x3f800000, float:1.0)
            if (r2 == 0) goto L_0x0307
            boolean r2 = r1.b()
            if (r2 == 0) goto L_0x02d4
            r2 = 8
            int r2 = r1.c(r2)
            r8 = 255(0xff, float:3.57E-43)
            if (r2 != r8) goto L_0x02af
            r8 = 16
            int r2 = r1.c(r8)
            int r9 = r1.c(r8)
            if (r2 == 0) goto L_0x02d4
            if (r9 == 0) goto L_0x02d4
            float r2 = (float) r2
            float r6 = (float) r9
            float r6 = r2 / r6
            goto L_0x02d4
        L_0x02af:
            float[] r8 = com.google.android.exoplayer2.util.NalUnitUtil.b
            r8 = 17
            if (r2 >= r8) goto L_0x02ba
            float[] r6 = com.google.android.exoplayer2.util.NalUnitUtil.b
            r6 = r6[r2]
            goto L_0x02d4
        L_0x02ba:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r9 = 46
            r8.<init>(r9)
            java.lang.String r9 = "Unexpected aspect_ratio_idc value: "
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r2 = r8.append(r2)
            java.lang.String r2 = r2.toString()
            java.lang.String r8 = "H265Reader"
            com.google.android.exoplayer2.util.Log.c(r8, r2)
        L_0x02d4:
            boolean r2 = r1.b()
            if (r2 == 0) goto L_0x02dd
            r1.a()
        L_0x02dd:
            boolean r2 = r1.b()
            if (r2 == 0) goto L_0x02f0
            r2 = 4
            r1.a(r2)
            boolean r2 = r1.b()
            if (r2 == 0) goto L_0x02f0
            r1.a(r4)
        L_0x02f0:
            boolean r2 = r1.b()
            if (r2 == 0) goto L_0x02fc
            r1.d()
            r1.d()
        L_0x02fc:
            r1.a()
            boolean r2 = r1.b()
            if (r2 == 0) goto L_0x0307
            int r22 = r22 << 1
        L_0x0307:
            r2 = r22
            byte[] r8 = r10.b
            int r9 = r10.c
            r10 = 0
            r1.a(r8, r10, r9)
            r1.a(r4)
            java.lang.String r1 = com.google.android.exoplayer2.util.CodecSpecificDataUtil.a((com.google.android.exoplayer2.util.ParsableNalUnitBitArray) r1)
            com.google.android.exoplayer2.Format$Builder r4 = new com.google.android.exoplayer2.Format$Builder
            r4.<init>()
            r4.a = r15
            java.lang.String r8 = "video/hevc"
            r4.k = r8
            r4.h = r1
            r4.p = r5
            r4.q = r2
            r4.t = r6
            java.util.List r1 = java.util.Collections.singletonList(r3)
            r4.m = r1
            com.google.android.exoplayer2.Format r1 = r4.a()
            r13.a(r1)
            r1 = 1
            r0.e = r1
            goto L_0x0348
        L_0x033c:
            r19 = r2
            r16 = r3
            r17 = r4
            r18 = r5
            r20 = r6
            r25 = r8
        L_0x0348:
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r1 = r0.j
            boolean r1 = r1.b(r7)
            r2 = 5
            if (r1 == 0) goto L_0x0372
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r1 = r0.j
            byte[] r1 = r1.b
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r3 = r0.j
            int r3 = r3.c
            int r1 = com.google.android.exoplayer2.util.NalUnitUtil.a((byte[]) r1, (int) r3)
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.n
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r4 = r0.j
            byte[] r4 = r4.b
            r3.a((byte[]) r4, (int) r1)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r0.n
            r1.e(r2)
            com.google.android.exoplayer2.extractor.ts.SeiReader r1 = r0.a
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.n
            r1.a((long) r11, (com.google.android.exoplayer2.util.ParsableByteArray) r3)
        L_0x0372:
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r1 = r0.k
            boolean r1 = r1.b(r7)
            if (r1 == 0) goto L_0x039b
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r1 = r0.k
            byte[] r1 = r1.b
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r3 = r0.k
            int r3 = r3.c
            int r1 = com.google.android.exoplayer2.util.NalUnitUtil.a((byte[]) r1, (int) r3)
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.n
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r4 = r0.k
            byte[] r4 = r4.b
            r3.a((byte[]) r4, (int) r1)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r0.n
            r1.e(r2)
            com.google.android.exoplayer2.extractor.ts.SeiReader r1 = r0.a
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.n
            r1.a((long) r11, (com.google.android.exoplayer2.util.ParsableByteArray) r2)
        L_0x039b:
            long r1 = r0.m
            com.google.android.exoplayer2.extractor.ts.H265Reader$SampleReader r3 = r0.d
            boolean r4 = r0.e
            r5 = 0
            r3.f = r5
            r3.g = r5
            r3.d = r1
            r3.c = r5
            r8 = r25
            r3.a = r8
            boolean r1 = com.google.android.exoplayer2.extractor.ts.H265Reader.SampleReader.c(r20)
            if (r1 != 0) goto L_0x03d9
            boolean r1 = r3.h
            if (r1 == 0) goto L_0x03c7
            boolean r1 = r3.i
            if (r1 != 0) goto L_0x03c7
            if (r4 == 0) goto L_0x03c3
            r1 = r19
            r3.a(r1)
        L_0x03c3:
            r14 = 0
            r3.h = r14
            goto L_0x03c8
        L_0x03c7:
            r14 = 0
        L_0x03c8:
            boolean r1 = com.google.android.exoplayer2.extractor.ts.H265Reader.SampleReader.b(r20)
            if (r1 == 0) goto L_0x03d7
            boolean r1 = r3.i
            r2 = 1
            r1 = r1 ^ r2
            r3.g = r1
            r3.i = r2
            goto L_0x03db
        L_0x03d7:
            r2 = 1
            goto L_0x03db
        L_0x03d9:
            r2 = 1
            r14 = 0
        L_0x03db:
            r1 = r20
            r4 = 16
            if (r1 < r4) goto L_0x03e7
            r4 = 21
            if (r1 > r4) goto L_0x03e7
            r4 = 1
            goto L_0x03e8
        L_0x03e7:
            r4 = 0
        L_0x03e8:
            r3.b = r4
            boolean r4 = r3.b
            if (r4 != 0) goto L_0x03f5
            r4 = 9
            if (r1 > r4) goto L_0x03f3
            goto L_0x03f5
        L_0x03f3:
            r10 = 0
            goto L_0x03f6
        L_0x03f5:
            r10 = 1
        L_0x03f6:
            r3.e = r10
            boolean r2 = r0.e
            if (r2 != 0) goto L_0x040b
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r2 = r0.g
            r2.a(r1)
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r2 = r0.h
            r2.a(r1)
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r2 = r0.i
            r2.a(r1)
        L_0x040b:
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r2 = r0.j
            r2.a(r1)
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r2 = r0.k
            r2.a(r1)
            int r2 = r18 + 3
            r1 = r29
            r3 = r16
            r4 = r17
            goto L_0x002d
        L_0x041f:
            r1 = r29
            goto L_0x000e
        L_0x0423:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.H265Reader.a(com.google.android.exoplayer2.util.ParsableByteArray):void");
    }

    public final void b() {
    }
}

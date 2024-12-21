package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Arrays;

public final class H262Reader implements ElementaryStreamReader {
    private static final double[] c = {23.976023976023978d, 24.0d, 25.0d, 29.97002997002997d, 30.0d, 50.0d, 59.94005994005994d, 60.0d};
    private String a;
    private TrackOutput b;
    private final UserDataReader d;
    private final ParsableByteArray e;
    private final NalUnitTargetBuffer f;
    private final boolean[] g;
    private final CsdBuffer h;
    private long i;
    private boolean j;
    private boolean k;
    private long l;
    private long m;
    private long n;
    private long o;
    private boolean p;
    private boolean q;

    final class CsdBuffer {
        static final byte[] a = {0, 0, 1};
        boolean b;
        public int c;
        public int d;
        public byte[] e = new byte[128];

        public final void a(byte[] bArr, int i, int i2) {
            if (this.b) {
                int i3 = i2 - i;
                byte[] bArr2 = this.e;
                int length = bArr2.length;
                int i4 = this.c;
                if (length < i4 + i3) {
                    this.e = Arrays.copyOf(bArr2, (i4 + i3) << 1);
                }
                System.arraycopy(bArr, i, this.e, this.c, i3);
                this.c += i3;
            }
        }
    }

    public H262Reader() {
        this((UserDataReader) null);
    }

    H262Reader(UserDataReader userDataReader) {
        ParsableByteArray parsableByteArray;
        this.d = userDataReader;
        this.g = new boolean[4];
        this.h = new CsdBuffer();
        if (userDataReader != null) {
            this.f = new NalUnitTargetBuffer(178);
            parsableByteArray = new ParsableByteArray();
        } else {
            parsableByteArray = null;
            this.f = null;
        }
        this.e = parsableByteArray;
    }

    public final void a() {
        NalUnitUtil.a(this.g);
        CsdBuffer csdBuffer = this.h;
        csdBuffer.b = false;
        csdBuffer.c = 0;
        csdBuffer.d = 0;
        NalUnitTargetBuffer nalUnitTargetBuffer = this.f;
        if (nalUnitTargetBuffer != null) {
            nalUnitTargetBuffer.a();
        }
        this.i = 0;
        this.j = false;
    }

    public final void a(long j2, int i2) {
        this.m = j2;
    }

    public final void a(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.a();
        this.a = trackIdGenerator.c();
        this.b = extractorOutput.a(trackIdGenerator.b(), 2);
        UserDataReader userDataReader = this.d;
        if (userDataReader != null) {
            userDataReader.a(extractorOutput, trackIdGenerator);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00d2, code lost:
        r15 = r14 / ((float) r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00d5, code lost:
        r14 = new com.google.android.exoplayer2.Format.Builder();
        r14.a = r10;
        r14.k = "video/mpeg2";
        r14.p = r13;
        r14.q = r12;
        r14.t = r15;
        r14.m = java.util.Collections.singletonList(r11);
        r10 = r14.a();
        r12 = (r11[7] & 15) - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00f7, code lost:
        if (r12 < 0) goto L_0x012f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00fb, code lost:
        if (r12 >= 8) goto L_0x012f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00fd, code lost:
        r12 = c[r12];
        r9 = r11[r9.d + 9];
        r11 = (r9 & com.google.appinventor.components.runtime.util.Ev3Constants.Opcode.SYSTEM) >> 5;
        r9 = r9 & 31;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x010d, code lost:
        if (r11 == r9) goto L_0x0124;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x010f, code lost:
        r14 = (double) r11;
        java.lang.Double.isNaN(r14);
        r16 = r6;
        r11 = r7;
        r6 = (double) (r9 + 1);
        java.lang.Double.isNaN(r6);
        r12 = r12 * ((r14 + 1.0d) / r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0124, code lost:
        r16 = r6;
        r11 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0127, code lost:
        r6 = (long) (1000000.0d / r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x012f, code lost:
        r16 = r6;
        r11 = r7;
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0134, code lost:
        r6 = android.util.Pair.create(r10, java.lang.Long.valueOf(r6));
        r0.b.a((com.google.android.exoplayer2.Format) r6.first);
        r0.l = ((java.lang.Long) r6.second).longValue();
        r0.k = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0082  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.android.exoplayer2.util.ParsableByteArray r26) {
        /*
            r25 = this;
            r0 = r25
            r1 = r26
            com.google.android.exoplayer2.extractor.TrackOutput r2 = r0.b
            com.google.android.exoplayer2.util.Assertions.a((java.lang.Object) r2)
            int r2 = r1.b
            int r3 = r1.c
            byte[] r4 = r1.a
            long r5 = r0.i
            int r7 = r26.a()
            long r7 = (long) r7
            long r5 = r5 + r7
            r0.i = r5
            com.google.android.exoplayer2.extractor.TrackOutput r5 = r0.b
            int r6 = r26.a()
            r5.b(r1, r6)
        L_0x0022:
            boolean[] r5 = r0.g
            int r5 = com.google.android.exoplayer2.util.NalUnitUtil.a(r4, r2, r3, r5)
            if (r5 != r3) goto L_0x003b
            boolean r1 = r0.k
            if (r1 != 0) goto L_0x0033
            com.google.android.exoplayer2.extractor.ts.H262Reader$CsdBuffer r1 = r0.h
            r1.a(r4, r2, r3)
        L_0x0033:
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r1 = r0.f
            if (r1 == 0) goto L_0x003a
            r1.a(r4, r2, r3)
        L_0x003a:
            return
        L_0x003b:
            byte[] r6 = r1.a
            int r7 = r5 + 3
            byte r6 = r6[r7]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r8 = r5 - r2
            boolean r9 = r0.k
            r12 = 179(0xb3, float:2.51E-43)
            r13 = 0
            r14 = 1
            if (r9 != 0) goto L_0x0153
            if (r8 <= 0) goto L_0x0054
            com.google.android.exoplayer2.extractor.ts.H262Reader$CsdBuffer r9 = r0.h
            r9.a(r4, r2, r5)
        L_0x0054:
            if (r8 >= 0) goto L_0x0058
            int r9 = -r8
            goto L_0x0059
        L_0x0058:
            r9 = 0
        L_0x0059:
            com.google.android.exoplayer2.extractor.ts.H262Reader$CsdBuffer r15 = r0.h
            boolean r10 = r15.b
            if (r10 == 0) goto L_0x0075
            int r10 = r15.c
            int r10 = r10 - r9
            r15.c = r10
            int r9 = r15.d
            if (r9 != 0) goto L_0x0071
            r9 = 181(0xb5, float:2.54E-43)
            if (r6 != r9) goto L_0x0071
            int r9 = r15.c
            r15.d = r9
            goto L_0x0079
        L_0x0071:
            r15.b = r13
            r9 = 1
            goto L_0x0080
        L_0x0075:
            if (r6 != r12) goto L_0x0079
            r15.b = r14
        L_0x0079:
            byte[] r9 = com.google.android.exoplayer2.extractor.ts.H262Reader.CsdBuffer.a
            r10 = 3
            r15.a(r9, r13, r10)
            r9 = 0
        L_0x0080:
            if (r9 == 0) goto L_0x0153
            com.google.android.exoplayer2.extractor.ts.H262Reader$CsdBuffer r9 = r0.h
            java.lang.String r10 = r0.a
            java.lang.Object r10 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r10)
            java.lang.String r10 = (java.lang.String) r10
            byte[] r11 = r9.e
            int r15 = r9.c
            byte[] r11 = java.util.Arrays.copyOf(r11, r15)
            r15 = 4
            byte r13 = r11[r15]
            r13 = r13 & 255(0xff, float:3.57E-43)
            r16 = 5
            byte r12 = r11[r16]
            r12 = r12 & 255(0xff, float:3.57E-43)
            r17 = 6
            byte r14 = r11[r17]
            r14 = r14 & 255(0xff, float:3.57E-43)
            int r13 = r13 << r15
            int r17 = r12 >> 4
            r13 = r13 | r17
            r12 = r12 & 15
            r15 = 8
            int r12 = r12 << r15
            r12 = r12 | r14
            r14 = 7
            byte r15 = r11[r14]
            r15 = r15 & 240(0xf0, float:3.36E-43)
            r17 = 4
            int r15 = r15 >> 4
            switch(r15) {
                case 2: goto L_0x00cd;
                case 3: goto L_0x00c7;
                case 4: goto L_0x00bf;
                default: goto L_0x00bc;
            }
        L_0x00bc:
            r15 = 1065353216(0x3f800000, float:1.0)
            goto L_0x00d5
        L_0x00bf:
            int r15 = r12 * 121
            float r15 = (float) r15
            int r14 = r13 * 100
            float r14 = (float) r14
            float r15 = r15 / r14
            goto L_0x00d5
        L_0x00c7:
            int r14 = r12 << 4
            float r14 = (float) r14
            int r15 = r13 * 9
            goto L_0x00d2
        L_0x00cd:
            int r14 = r12 * 4
            float r14 = (float) r14
            int r15 = r13 * 3
        L_0x00d2:
            float r15 = (float) r15
            float r15 = r14 / r15
        L_0x00d5:
            com.google.android.exoplayer2.Format$Builder r14 = new com.google.android.exoplayer2.Format$Builder
            r14.<init>()
            r14.a = r10
            java.lang.String r10 = "video/mpeg2"
            r14.k = r10
            r14.p = r13
            r14.q = r12
            r14.t = r15
            java.util.List r10 = java.util.Collections.singletonList(r11)
            r14.m = r10
            com.google.android.exoplayer2.Format r10 = r14.a()
            r12 = 7
            byte r12 = r11[r12]
            r12 = r12 & 15
            r13 = 1
            int r12 = r12 - r13
            if (r12 < 0) goto L_0x012f
            r13 = 8
            if (r12 >= r13) goto L_0x012f
            double[] r13 = c
            r12 = r13[r12]
            int r9 = r9.d
            int r9 = r9 + 9
            byte r9 = r11[r9]
            r11 = r9 & 96
            int r11 = r11 >> 5
            r9 = r9 & 31
            if (r11 == r9) goto L_0x0124
            double r14 = (double) r11
            r18 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            java.lang.Double.isNaN(r14)
            double r14 = r14 + r18
            int r9 = r9 + 1
            r16 = r6
            r11 = r7
            double r6 = (double) r9
            java.lang.Double.isNaN(r6)
            double r14 = r14 / r6
            double r12 = r12 * r14
            goto L_0x0127
        L_0x0124:
            r16 = r6
            r11 = r7
        L_0x0127:
            r6 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r6 = r6 / r12
            long r6 = (long) r6
            goto L_0x0134
        L_0x012f:
            r16 = r6
            r11 = r7
            r6 = 0
        L_0x0134:
            java.lang.Long r6 = java.lang.Long.valueOf(r6)
            android.util.Pair r6 = android.util.Pair.create(r10, r6)
            com.google.android.exoplayer2.extractor.TrackOutput r7 = r0.b
            java.lang.Object r9 = r6.first
            com.google.android.exoplayer2.Format r9 = (com.google.android.exoplayer2.Format) r9
            r7.a(r9)
            java.lang.Object r6 = r6.second
            java.lang.Long r6 = (java.lang.Long) r6
            long r6 = r6.longValue()
            r0.l = r6
            r6 = 1
            r0.k = r6
            goto L_0x0156
        L_0x0153:
            r16 = r6
            r11 = r7
        L_0x0156:
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r6 = r0.f
            if (r6 == 0) goto L_0x01a9
            if (r8 <= 0) goto L_0x0161
            r6.a(r4, r2, r5)
            r2 = 0
            goto L_0x0162
        L_0x0161:
            int r2 = -r8
        L_0x0162:
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r6 = r0.f
            boolean r2 = r6.b(r2)
            if (r2 == 0) goto L_0x0194
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r2 = r0.f
            byte[] r2 = r2.b
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r6 = r0.f
            int r6 = r6.c
            int r2 = com.google.android.exoplayer2.util.NalUnitUtil.a((byte[]) r2, (int) r6)
            com.google.android.exoplayer2.util.ParsableByteArray r6 = r0.e
            java.lang.Object r6 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r6)
            com.google.android.exoplayer2.util.ParsableByteArray r6 = (com.google.android.exoplayer2.util.ParsableByteArray) r6
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r7 = r0.f
            byte[] r7 = r7.b
            r6.a((byte[]) r7, (int) r2)
            com.google.android.exoplayer2.extractor.ts.UserDataReader r2 = r0.d
            java.lang.Object r2 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r2)
            com.google.android.exoplayer2.extractor.ts.UserDataReader r2 = (com.google.android.exoplayer2.extractor.ts.UserDataReader) r2
            long r6 = r0.o
            com.google.android.exoplayer2.util.ParsableByteArray r8 = r0.e
            r2.a((long) r6, (com.google.android.exoplayer2.util.ParsableByteArray) r8)
        L_0x0194:
            r2 = 178(0xb2, float:2.5E-43)
            r6 = r16
            if (r6 != r2) goto L_0x01ab
            byte[] r2 = r1.a
            int r7 = r5 + 2
            byte r2 = r2[r7]
            r7 = 1
            if (r2 != r7) goto L_0x01ab
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r2 = r0.f
            r2.a(r6)
            goto L_0x01ab
        L_0x01a9:
            r6 = r16
        L_0x01ab:
            if (r6 == 0) goto L_0x01ba
            r2 = 179(0xb3, float:2.51E-43)
            if (r6 != r2) goto L_0x01b2
            goto L_0x01ba
        L_0x01b2:
            r2 = 184(0xb8, float:2.58E-43)
            if (r6 != r2) goto L_0x021c
            r2 = 1
            r0.p = r2
            goto L_0x021c
        L_0x01ba:
            int r2 = r3 - r5
            boolean r5 = r0.j
            if (r5 == 0) goto L_0x01e3
            boolean r5 = r0.q
            if (r5 == 0) goto L_0x01e3
            boolean r5 = r0.k
            if (r5 == 0) goto L_0x01e3
            boolean r5 = r0.p
            long r7 = r0.i
            long r9 = r0.n
            long r7 = r7 - r9
            int r8 = (int) r7
            int r22 = r8 - r2
            com.google.android.exoplayer2.extractor.TrackOutput r7 = r0.b
            long r8 = r0.o
            r24 = 0
            r18 = r7
            r19 = r8
            r21 = r5
            r23 = r2
            r18.a(r19, r21, r22, r23, r24)
        L_0x01e3:
            boolean r5 = r0.j
            if (r5 == 0) goto L_0x01ef
            boolean r7 = r0.q
            if (r7 == 0) goto L_0x01ec
            goto L_0x01ef
        L_0x01ec:
            r2 = 0
            r5 = 1
            goto L_0x0215
        L_0x01ef:
            long r7 = r0.i
            long r9 = (long) r2
            long r7 = r7 - r9
            r0.n = r7
            long r7 = r0.m
            r9 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r2 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r2 == 0) goto L_0x0201
            goto L_0x020b
        L_0x0201:
            if (r5 == 0) goto L_0x0209
            long r7 = r0.o
            long r12 = r0.l
            long r7 = r7 + r12
            goto L_0x020b
        L_0x0209:
            r7 = 0
        L_0x020b:
            r0.o = r7
            r2 = 0
            r0.p = r2
            r0.m = r9
            r5 = 1
            r0.j = r5
        L_0x0215:
            if (r6 != 0) goto L_0x0219
            r13 = 1
            goto L_0x021a
        L_0x0219:
            r13 = 0
        L_0x021a:
            r0.q = r13
        L_0x021c:
            r2 = r11
            goto L_0x0022
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.H262Reader.a(com.google.android.exoplayer2.util.ParsableByteArray):void");
    }

    public final void b() {
    }
}

package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.util.Arrays;

public final class H263Reader implements ElementaryStreamReader {
    private static final float[] a = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 1.0f};
    private final UserDataReader b;
    private final ParsableByteArray c;
    private final boolean[] d;
    private final CsdBuffer e;
    private final NalUnitTargetBuffer f;
    private SampleReader g;
    private long h;
    private String i;
    private TrackOutput j;
    private boolean k;
    private long l;

    final class CsdBuffer {
        static final byte[] a = {0, 0, 1};
        boolean b;
        int c;
        public int d;
        public int e;
        public byte[] f = new byte[128];

        public final void a() {
            this.b = false;
            this.d = 0;
            this.c = 0;
        }

        public final void a(byte[] bArr, int i, int i2) {
            if (this.b) {
                int i3 = i2 - i;
                byte[] bArr2 = this.f;
                int length = bArr2.length;
                int i4 = this.d;
                if (length < i4 + i3) {
                    this.f = Arrays.copyOf(bArr2, (i4 + i3) << 1);
                }
                System.arraycopy(bArr, i, this.f, this.d, i3);
                this.d += i3;
            }
        }
    }

    final class SampleReader {
        final TrackOutput a;
        boolean b;
        boolean c;
        boolean d;
        int e;
        int f;
        long g;
        long h;

        public SampleReader(TrackOutput trackOutput) {
            this.a = trackOutput;
        }

        public final void a(byte[] bArr, int i, int i2) {
            if (this.c) {
                int i3 = this.f;
                int i4 = (i + 1) - i3;
                if (i4 < i2) {
                    this.d = ((bArr[i4] & Ev3Constants.Opcode.FILE) >> 6) == 0;
                    this.c = false;
                    return;
                }
                this.f = i3 + (i2 - i);
            }
        }
    }

    public H263Reader() {
        this((UserDataReader) null);
    }

    H263Reader(UserDataReader userDataReader) {
        ParsableByteArray parsableByteArray;
        this.b = userDataReader;
        this.d = new boolean[4];
        this.e = new CsdBuffer();
        if (userDataReader != null) {
            this.f = new NalUnitTargetBuffer(178);
            parsableByteArray = new ParsableByteArray();
        } else {
            parsableByteArray = null;
            this.f = null;
        }
        this.c = parsableByteArray;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.exoplayer2.Format a(com.google.android.exoplayer2.extractor.ts.H263Reader.CsdBuffer r6, int r7, java.lang.String r8) {
        /*
            byte[] r0 = r6.f
            int r6 = r6.d
            byte[] r6 = java.util.Arrays.copyOf(r0, r6)
            com.google.android.exoplayer2.util.ParsableBitArray r0 = new com.google.android.exoplayer2.util.ParsableBitArray
            r0.<init>(r6)
            r0.d(r7)
            r7 = 4
            r0.d(r7)
            r0.d()
            r1 = 8
            r0.b(r1)
            boolean r2 = r0.e()
            r3 = 3
            if (r2 == 0) goto L_0x0029
            r0.b(r7)
            r0.b(r3)
        L_0x0029:
            int r7 = r0.c(r7)
            java.lang.String r2 = "H263Reader"
            r4 = 15
            if (r7 != r4) goto L_0x0041
            int r7 = r0.c(r1)
            int r1 = r0.c(r1)
            if (r1 == 0) goto L_0x0049
            float r7 = (float) r7
            float r1 = (float) r1
            float r7 = r7 / r1
            goto L_0x0050
        L_0x0041:
            r1 = 7
            if (r7 >= r1) goto L_0x0049
            float[] r1 = a
            r7 = r1[r7]
            goto L_0x0050
        L_0x0049:
            java.lang.String r7 = "Invalid aspect ratio"
            com.google.android.exoplayer2.util.Log.c(r2, r7)
            r7 = 1065353216(0x3f800000, float:1.0)
        L_0x0050:
            boolean r1 = r0.e()
            r5 = 2
            if (r1 == 0) goto L_0x0087
            r0.b(r5)
            r1 = 1
            r0.b(r1)
            boolean r1 = r0.e()
            if (r1 == 0) goto L_0x0087
            r0.b(r4)
            r0.d()
            r0.b(r4)
            r0.d()
            r0.b(r4)
            r0.d()
            r0.b(r3)
            r1 = 11
            r0.b(r1)
            r0.d()
            r0.b(r4)
            r0.d()
        L_0x0087:
            int r1 = r0.c(r5)
            if (r1 == 0) goto L_0x0092
            java.lang.String r1 = "Unhandled video object layer shape"
            com.google.android.exoplayer2.util.Log.c(r2, r1)
        L_0x0092:
            r0.d()
            r1 = 16
            int r1 = r0.c(r1)
            r0.d()
            boolean r3 = r0.e()
            if (r3 == 0) goto L_0x00b9
            if (r1 != 0) goto L_0x00ac
            java.lang.String r1 = "Invalid vop_increment_time_resolution"
            com.google.android.exoplayer2.util.Log.c(r2, r1)
            goto L_0x00b9
        L_0x00ac:
            int r1 = r1 + -1
            r2 = 0
        L_0x00af:
            if (r1 <= 0) goto L_0x00b6
            int r2 = r2 + 1
            int r1 = r1 >> 1
            goto L_0x00af
        L_0x00b6:
            r0.b(r2)
        L_0x00b9:
            r0.d()
            r1 = 13
            int r2 = r0.c(r1)
            r0.d()
            int r1 = r0.c(r1)
            r0.d()
            r0.d()
            com.google.android.exoplayer2.Format$Builder r0 = new com.google.android.exoplayer2.Format$Builder
            r0.<init>()
            r0.a = r8
            java.lang.String r8 = "video/mp4v-es"
            r0.k = r8
            r0.p = r2
            r0.q = r1
            r0.t = r7
            java.util.List r6 = java.util.Collections.singletonList(r6)
            r0.m = r6
            com.google.android.exoplayer2.Format r6 = r0.a()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.H263Reader.a(com.google.android.exoplayer2.extractor.ts.H263Reader$CsdBuffer, int, java.lang.String):com.google.android.exoplayer2.Format");
    }

    public final void a() {
        NalUnitUtil.a(this.d);
        this.e.a();
        SampleReader sampleReader = this.g;
        if (sampleReader != null) {
            sampleReader.b = false;
            sampleReader.c = false;
            sampleReader.d = false;
            sampleReader.e = -1;
        }
        NalUnitTargetBuffer nalUnitTargetBuffer = this.f;
        if (nalUnitTargetBuffer != null) {
            nalUnitTargetBuffer.a();
        }
        this.h = 0;
    }

    public final void a(long j2, int i2) {
        this.l = j2;
    }

    public final void a(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.a();
        this.i = trackIdGenerator.c();
        TrackOutput a2 = extractorOutput.a(trackIdGenerator.b(), 2);
        this.j = a2;
        this.g = new SampleReader(a2);
        UserDataReader userDataReader = this.b;
        if (userDataReader != null) {
            userDataReader.a(extractorOutput, trackIdGenerator);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a4, code lost:
        r13.c = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00b0, code lost:
        r13.a(com.google.android.exoplayer2.extractor.ts.H263Reader.CsdBuffer.a, 0, 3);
        r9 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b7, code lost:
        if (r9 == false) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b9, code lost:
        r9 = r0.j;
        r11 = r0.e;
        r9.a(a(r11, r11.e, (java.lang.String) com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r0.i)));
        r0.k = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x015c  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0177  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.android.exoplayer2.util.ParsableByteArray r24) {
        /*
            r23 = this;
            r0 = r23
            r1 = r24
            com.google.android.exoplayer2.extractor.ts.H263Reader$SampleReader r2 = r0.g
            com.google.android.exoplayer2.util.Assertions.a((java.lang.Object) r2)
            com.google.android.exoplayer2.extractor.TrackOutput r2 = r0.j
            com.google.android.exoplayer2.util.Assertions.a((java.lang.Object) r2)
            int r2 = r1.b
            int r3 = r1.c
            byte[] r4 = r1.a
            long r5 = r0.h
            int r7 = r24.a()
            long r7 = (long) r7
            long r5 = r5 + r7
            r0.h = r5
            com.google.android.exoplayer2.extractor.TrackOutput r5 = r0.j
            int r6 = r24.a()
            r5.b(r1, r6)
        L_0x0027:
            boolean[] r5 = r0.d
            int r5 = com.google.android.exoplayer2.util.NalUnitUtil.a(r4, r2, r3, r5)
            if (r5 != r3) goto L_0x0045
            boolean r1 = r0.k
            if (r1 != 0) goto L_0x0038
            com.google.android.exoplayer2.extractor.ts.H263Reader$CsdBuffer r1 = r0.e
            r1.a(r4, r2, r3)
        L_0x0038:
            com.google.android.exoplayer2.extractor.ts.H263Reader$SampleReader r1 = r0.g
            r1.a(r4, r2, r3)
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r1 = r0.f
            if (r1 == 0) goto L_0x0044
            r1.a(r4, r2, r3)
        L_0x0044:
            return
        L_0x0045:
            byte[] r6 = r1.a
            int r7 = r5 + 3
            byte r6 = r6[r7]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r8 = r5 - r2
            boolean r9 = r0.k
            r10 = 179(0xb3, float:2.51E-43)
            if (r9 != 0) goto L_0x00d1
            if (r8 <= 0) goto L_0x005c
            com.google.android.exoplayer2.extractor.ts.H263Reader$CsdBuffer r9 = r0.e
            r9.a(r4, r2, r5)
        L_0x005c:
            if (r8 >= 0) goto L_0x0060
            int r9 = -r8
            goto L_0x0061
        L_0x0060:
            r9 = 0
        L_0x0061:
            com.google.android.exoplayer2.extractor.ts.H263Reader$CsdBuffer r13 = r0.e
            int r14 = r13.c
            java.lang.String r11 = "Unexpected start code value"
            java.lang.String r12 = "H263Reader"
            r15 = 3
            switch(r14) {
                case 0: goto L_0x00a7;
                case 1: goto L_0x0098;
                case 2: goto L_0x0090;
                case 3: goto L_0x0083;
                case 4: goto L_0x0073;
                default: goto L_0x006d;
            }
        L_0x006d:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x0073:
            if (r6 == r10) goto L_0x0079
            r11 = 181(0xb5, float:2.54E-43)
            if (r6 != r11) goto L_0x00b0
        L_0x0079:
            int r11 = r13.d
            int r11 = r11 - r9
            r13.d = r11
            r9 = 0
            r13.b = r9
            r9 = 1
            goto L_0x00b7
        L_0x0083:
            r9 = r6 & 240(0xf0, float:3.36E-43)
            r14 = 32
            if (r9 == r14) goto L_0x008a
            goto L_0x009c
        L_0x008a:
            int r9 = r13.d
            r13.e = r9
            r9 = 4
            goto L_0x00a4
        L_0x0090:
            r9 = 31
            if (r6 <= r9) goto L_0x0095
            goto L_0x009c
        L_0x0095:
            r13.c = r15
            goto L_0x00b0
        L_0x0098:
            r9 = 181(0xb5, float:2.54E-43)
            if (r6 == r9) goto L_0x00a3
        L_0x009c:
            com.google.android.exoplayer2.util.Log.c(r12, r11)
            r13.a()
            goto L_0x00b0
        L_0x00a3:
            r9 = 2
        L_0x00a4:
            r13.c = r9
            goto L_0x00b0
        L_0x00a7:
            r9 = 176(0xb0, float:2.47E-43)
            if (r6 != r9) goto L_0x00b0
            r9 = 1
            r13.c = r9
            r13.b = r9
        L_0x00b0:
            byte[] r9 = com.google.android.exoplayer2.extractor.ts.H263Reader.CsdBuffer.a
            r11 = 0
            r13.a(r9, r11, r15)
            r9 = 0
        L_0x00b7:
            if (r9 == 0) goto L_0x00d1
            com.google.android.exoplayer2.extractor.TrackOutput r9 = r0.j
            com.google.android.exoplayer2.extractor.ts.H263Reader$CsdBuffer r11 = r0.e
            int r12 = r11.e
            java.lang.String r13 = r0.i
            java.lang.Object r13 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r13)
            java.lang.String r13 = (java.lang.String) r13
            com.google.android.exoplayer2.Format r11 = a(r11, r12, r13)
            r9.a(r11)
            r9 = 1
            r0.k = r9
        L_0x00d1:
            com.google.android.exoplayer2.extractor.ts.H263Reader$SampleReader r9 = r0.g
            r9.a(r4, r2, r5)
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r9 = r0.f
            if (r9 == 0) goto L_0x0127
            if (r8 <= 0) goto L_0x00e1
            r9.a(r4, r2, r5)
            r2 = 0
            goto L_0x00e2
        L_0x00e1:
            int r2 = -r8
        L_0x00e2:
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r8 = r0.f
            boolean r2 = r8.b(r2)
            if (r2 == 0) goto L_0x0114
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r2 = r0.f
            byte[] r2 = r2.b
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r8 = r0.f
            int r8 = r8.c
            int r2 = com.google.android.exoplayer2.util.NalUnitUtil.a((byte[]) r2, (int) r8)
            com.google.android.exoplayer2.util.ParsableByteArray r8 = r0.c
            java.lang.Object r8 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r8)
            com.google.android.exoplayer2.util.ParsableByteArray r8 = (com.google.android.exoplayer2.util.ParsableByteArray) r8
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r9 = r0.f
            byte[] r9 = r9.b
            r8.a((byte[]) r9, (int) r2)
            com.google.android.exoplayer2.extractor.ts.UserDataReader r2 = r0.b
            java.lang.Object r2 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r2)
            com.google.android.exoplayer2.extractor.ts.UserDataReader r2 = (com.google.android.exoplayer2.extractor.ts.UserDataReader) r2
            long r8 = r0.l
            com.google.android.exoplayer2.util.ParsableByteArray r11 = r0.c
            r2.a((long) r8, (com.google.android.exoplayer2.util.ParsableByteArray) r11)
        L_0x0114:
            r2 = 178(0xb2, float:2.5E-43)
            if (r6 != r2) goto L_0x0127
            byte[] r2 = r1.a
            int r8 = r5 + 2
            byte r2 = r2[r8]
            r9 = 1
            if (r2 != r9) goto L_0x0128
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r2 = r0.f
            r2.a(r6)
            goto L_0x0128
        L_0x0127:
            r9 = 1
        L_0x0128:
            int r2 = r3 - r5
            long r11 = r0.h
            long r13 = (long) r2
            long r11 = r11 - r13
            com.google.android.exoplayer2.extractor.ts.H263Reader$SampleReader r5 = r0.g
            boolean r8 = r0.k
            int r13 = r5.e
            r14 = 182(0xb6, float:2.55E-43)
            if (r13 != r14) goto L_0x0158
            if (r8 == 0) goto L_0x0158
            boolean r8 = r5.b
            if (r8 == 0) goto L_0x0158
            long r14 = r5.g
            long r14 = r11 - r14
            int r13 = (int) r14
            boolean r14 = r5.d
            com.google.android.exoplayer2.extractor.TrackOutput r15 = r5.a
            long r8 = r5.h
            r22 = 0
            r16 = r15
            r17 = r8
            r19 = r14
            r20 = r13
            r21 = r2
            r16.a(r17, r19, r20, r21, r22)
        L_0x0158:
            int r2 = r5.e
            if (r2 == r10) goto L_0x015e
            r5.g = r11
        L_0x015e:
            com.google.android.exoplayer2.extractor.ts.H263Reader$SampleReader r2 = r0.g
            long r8 = r0.l
            r2.e = r6
            r5 = 0
            r2.d = r5
            r5 = 182(0xb6, float:2.55E-43)
            if (r6 == r5) goto L_0x0170
            if (r6 != r10) goto L_0x016e
            goto L_0x0170
        L_0x016e:
            r10 = 0
            goto L_0x0171
        L_0x0170:
            r10 = 1
        L_0x0171:
            r2.b = r10
            if (r6 != r5) goto L_0x0177
            r11 = 1
            goto L_0x0178
        L_0x0177:
            r11 = 0
        L_0x0178:
            r2.c = r11
            r5 = 0
            r2.f = r5
            r2.h = r8
            r2 = r7
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.H263Reader.a(com.google.android.exoplayer2.util.ParsableByteArray):void");
    }

    public final void b() {
    }
}

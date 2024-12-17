package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.ParsableNalUnitBitArray;

public final class H264Reader implements ElementaryStreamReader {
    private final SeiReader a;
    private final boolean b;
    private final boolean c;
    private final NalUnitTargetBuffer d = new NalUnitTargetBuffer(7);
    private final NalUnitTargetBuffer e = new NalUnitTargetBuffer(8);
    private final NalUnitTargetBuffer f = new NalUnitTargetBuffer(6);
    private long g;
    private final boolean[] h = new boolean[3];
    private String i;
    private TrackOutput j;
    private SampleReader k;
    private boolean l;
    private long m;
    private boolean n;
    private final ParsableByteArray o = new ParsableByteArray();

    final class SampleReader {
        final TrackOutput a;
        final boolean b;
        final boolean c;
        int d;
        int e;
        long f;
        boolean g;
        long h;
        SliceHeaderData i = new SliceHeaderData((byte) 0);
        SliceHeaderData j = new SliceHeaderData((byte) 0);
        boolean k;
        long l;
        long m;
        boolean n;
        private final SparseArray o = new SparseArray();
        private final SparseArray p = new SparseArray();
        private final ParsableNalUnitBitArray q;
        private byte[] r;

        final class SliceHeaderData {
            boolean a;
            int b;
            private boolean c;
            private NalUnitUtil.SpsData d;
            private int e;
            private int f;
            private int g;
            private boolean h;
            private boolean i;
            private boolean j;
            private boolean k;
            private int l;
            private int m;
            private int n;
            private int o;
            private int p;

            private SliceHeaderData() {
            }

            /* synthetic */ SliceHeaderData(byte b2) {
                this();
            }

            /* JADX WARNING: Code restructure failed: missing block: B:17:0x003a, code lost:
                r3 = r5.e;
                r4 = r6.e;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:37:0x006c, code lost:
                r0 = r5.k;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            static /* synthetic */ boolean a(com.google.android.exoplayer2.extractor.ts.H264Reader.SampleReader.SliceHeaderData r5, com.google.android.exoplayer2.extractor.ts.H264Reader.SampleReader.SliceHeaderData r6) {
                /*
                    boolean r0 = r5.c
                    if (r0 == 0) goto L_0x007b
                    boolean r0 = r6.c
                    r1 = 1
                    if (r0 != 0) goto L_0x000a
                    return r1
                L_0x000a:
                    com.google.android.exoplayer2.util.NalUnitUtil$SpsData r0 = r5.d
                    java.lang.Object r0 = com.google.android.exoplayer2.util.Assertions.a((java.lang.Object) r0)
                    com.google.android.exoplayer2.util.NalUnitUtil$SpsData r0 = (com.google.android.exoplayer2.util.NalUnitUtil.SpsData) r0
                    com.google.android.exoplayer2.util.NalUnitUtil$SpsData r2 = r6.d
                    java.lang.Object r2 = com.google.android.exoplayer2.util.Assertions.a((java.lang.Object) r2)
                    com.google.android.exoplayer2.util.NalUnitUtil$SpsData r2 = (com.google.android.exoplayer2.util.NalUnitUtil.SpsData) r2
                    int r3 = r5.f
                    int r4 = r6.f
                    if (r3 != r4) goto L_0x007a
                    int r3 = r5.g
                    int r4 = r6.g
                    if (r3 != r4) goto L_0x007a
                    boolean r3 = r5.h
                    boolean r4 = r6.h
                    if (r3 != r4) goto L_0x007a
                    boolean r3 = r5.i
                    if (r3 == 0) goto L_0x003a
                    boolean r3 = r6.i
                    if (r3 == 0) goto L_0x003a
                    boolean r3 = r5.j
                    boolean r4 = r6.j
                    if (r3 != r4) goto L_0x007a
                L_0x003a:
                    int r3 = r5.e
                    int r4 = r6.e
                    if (r3 == r4) goto L_0x0044
                    if (r3 == 0) goto L_0x007a
                    if (r4 == 0) goto L_0x007a
                L_0x0044:
                    int r3 = r0.k
                    if (r3 != 0) goto L_0x0058
                    int r3 = r2.k
                    if (r3 != 0) goto L_0x0058
                    int r3 = r5.m
                    int r4 = r6.m
                    if (r3 != r4) goto L_0x007a
                    int r3 = r5.n
                    int r4 = r6.n
                    if (r3 != r4) goto L_0x007a
                L_0x0058:
                    int r0 = r0.k
                    if (r0 != r1) goto L_0x006c
                    int r0 = r2.k
                    if (r0 != r1) goto L_0x006c
                    int r0 = r5.o
                    int r2 = r6.o
                    if (r0 != r2) goto L_0x007a
                    int r0 = r5.p
                    int r2 = r6.p
                    if (r0 != r2) goto L_0x007a
                L_0x006c:
                    boolean r0 = r5.k
                    boolean r2 = r6.k
                    if (r0 != r2) goto L_0x007a
                    if (r0 == 0) goto L_0x007b
                    int r5 = r5.l
                    int r6 = r6.l
                    if (r5 == r6) goto L_0x007b
                L_0x007a:
                    return r1
                L_0x007b:
                    r5 = 0
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.H264Reader.SampleReader.SliceHeaderData.a(com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader$SliceHeaderData, com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader$SliceHeaderData):boolean");
            }

            public final void a() {
                this.a = false;
                this.c = false;
            }

            public final void a(int i2) {
                this.b = i2;
                this.a = true;
            }

            public final void a(NalUnitUtil.SpsData spsData, int i2, int i3, int i4, int i5, boolean z, boolean z2, boolean z3, boolean z4, int i6, int i7, int i8, int i9, int i10) {
                this.d = spsData;
                this.e = i2;
                this.b = i3;
                this.f = i4;
                this.g = i5;
                this.h = z;
                this.i = z2;
                this.j = z3;
                this.k = z4;
                this.l = i6;
                this.m = i7;
                this.n = i8;
                this.o = i9;
                this.p = i10;
                this.c = true;
                this.a = true;
            }
        }

        public SampleReader(TrackOutput trackOutput, boolean z, boolean z2) {
            this.a = trackOutput;
            this.b = z;
            this.c = z2;
            byte[] bArr = new byte[128];
            this.r = bArr;
            this.q = new ParsableNalUnitBitArray(bArr, 0, 0);
            a();
        }

        public final void a() {
            this.g = false;
            this.k = false;
            this.j.a();
        }

        public final void a(NalUnitUtil.PpsData ppsData) {
            this.p.append(ppsData.a, ppsData);
        }

        public final void a(NalUnitUtil.SpsData spsData) {
            this.o.append(spsData.d, spsData);
        }

        /* JADX WARNING: Removed duplicated region for block: B:51:0x00ff  */
        /* JADX WARNING: Removed duplicated region for block: B:52:0x0102  */
        /* JADX WARNING: Removed duplicated region for block: B:54:0x0106  */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x0118  */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x011e  */
        /* JADX WARNING: Removed duplicated region for block: B:72:0x014e  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void a(byte[] r24, int r25, int r26) {
            /*
                r23 = this;
                r0 = r23
                r1 = r25
                boolean r2 = r0.g
                if (r2 != 0) goto L_0x0009
                return
            L_0x0009:
                int r2 = r26 - r1
                byte[] r3 = r0.r
                int r4 = r3.length
                int r5 = r0.d
                int r6 = r5 + r2
                r7 = 1
                if (r4 >= r6) goto L_0x001e
                int r5 = r5 + r2
                int r4 = r5 << 1
                byte[] r3 = java.util.Arrays.copyOf(r3, r4)
                r0.r = r3
            L_0x001e:
                byte[] r3 = r0.r
                int r4 = r0.d
                r5 = r24
                java.lang.System.arraycopy(r5, r1, r3, r4, r2)
                int r1 = r0.d
                int r1 = r1 + r2
                r0.d = r1
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r2 = r0.q
                byte[] r3 = r0.r
                r4 = 0
                r2.a(r3, r4, r1)
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r1 = r0.q
                r2 = 8
                boolean r1 = r1.b(r2)
                if (r1 != 0) goto L_0x003f
                return
            L_0x003f:
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r1 = r0.q
                r1.a()
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r1 = r0.q
                r2 = 2
                int r10 = r1.c(r2)
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r1 = r0.q
                r3 = 5
                r1.a(r3)
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r1 = r0.q
                boolean r1 = r1.c()
                if (r1 != 0) goto L_0x005a
                return
            L_0x005a:
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r1 = r0.q
                r1.f()
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r1 = r0.q
                boolean r1 = r1.c()
                if (r1 != 0) goto L_0x0068
                return
            L_0x0068:
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r1 = r0.q
                int r11 = r1.f()
                boolean r1 = r0.c
                if (r1 != 0) goto L_0x007a
                r0.g = r4
                com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader$SliceHeaderData r1 = r0.j
                r1.a(r11)
                return
            L_0x007a:
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r1 = r0.q
                boolean r1 = r1.c()
                if (r1 != 0) goto L_0x0083
                return
            L_0x0083:
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r1 = r0.q
                int r13 = r1.f()
                android.util.SparseArray r1 = r0.p
                int r1 = r1.indexOfKey(r13)
                if (r1 >= 0) goto L_0x0094
                r0.g = r4
                return
            L_0x0094:
                android.util.SparseArray r1 = r0.p
                java.lang.Object r1 = r1.get(r13)
                com.google.android.exoplayer2.util.NalUnitUtil$PpsData r1 = (com.google.android.exoplayer2.util.NalUnitUtil.PpsData) r1
                android.util.SparseArray r5 = r0.o
                int r6 = r1.b
                java.lang.Object r5 = r5.get(r6)
                r9 = r5
                com.google.android.exoplayer2.util.NalUnitUtil$SpsData r9 = (com.google.android.exoplayer2.util.NalUnitUtil.SpsData) r9
                boolean r5 = r9.h
                if (r5 == 0) goto L_0x00b9
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r5 = r0.q
                boolean r5 = r5.b(r2)
                if (r5 != 0) goto L_0x00b4
                return
            L_0x00b4:
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r5 = r0.q
                r5.a(r2)
            L_0x00b9:
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r2 = r0.q
                int r5 = r9.j
                boolean r2 = r2.b(r5)
                if (r2 != 0) goto L_0x00c4
                return
            L_0x00c4:
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r2 = r0.q
                int r5 = r9.j
                int r12 = r2.c(r5)
                boolean r2 = r9.i
                if (r2 != 0) goto L_0x00f7
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r2 = r0.q
                boolean r2 = r2.b(r7)
                if (r2 != 0) goto L_0x00d9
                return
            L_0x00d9:
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r2 = r0.q
                boolean r2 = r2.b()
                if (r2 == 0) goto L_0x00f5
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r5 = r0.q
                boolean r5 = r5.b(r7)
                if (r5 != 0) goto L_0x00ea
                return
            L_0x00ea:
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r5 = r0.q
                boolean r5 = r5.b()
                r14 = r2
                r16 = r5
                r15 = 1
                goto L_0x00fb
            L_0x00f5:
                r14 = r2
                goto L_0x00f8
            L_0x00f7:
                r14 = 0
            L_0x00f8:
                r15 = 0
                r16 = 0
            L_0x00fb:
                int r2 = r0.e
                if (r2 != r3) goto L_0x0102
                r17 = 1
                goto L_0x0104
            L_0x0102:
                r17 = 0
            L_0x0104:
                if (r17 == 0) goto L_0x0118
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r2 = r0.q
                boolean r2 = r2.c()
                if (r2 != 0) goto L_0x010f
                return
            L_0x010f:
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r2 = r0.q
                int r2 = r2.f()
                r18 = r2
                goto L_0x011a
            L_0x0118:
                r18 = 0
            L_0x011a:
                int r2 = r9.k
                if (r2 != 0) goto L_0x014e
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r2 = r0.q
                int r3 = r9.l
                boolean r2 = r2.b(r3)
                if (r2 != 0) goto L_0x0129
                return
            L_0x0129:
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r2 = r0.q
                int r3 = r9.l
                int r2 = r2.c(r3)
                boolean r1 = r1.c
                if (r1 == 0) goto L_0x014b
                if (r14 != 0) goto L_0x014b
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r1 = r0.q
                boolean r1 = r1.c()
                if (r1 != 0) goto L_0x0140
                return
            L_0x0140:
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r1 = r0.q
                int r1 = r1.e()
                r20 = r1
                r19 = r2
                goto L_0x018e
            L_0x014b:
                r19 = r2
                goto L_0x018c
            L_0x014e:
                int r2 = r9.k
                if (r2 != r7) goto L_0x018a
                boolean r2 = r9.m
                if (r2 != 0) goto L_0x018a
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r2 = r0.q
                boolean r2 = r2.c()
                if (r2 != 0) goto L_0x015f
                return
            L_0x015f:
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r2 = r0.q
                int r2 = r2.e()
                boolean r1 = r1.c
                if (r1 == 0) goto L_0x0183
                if (r14 != 0) goto L_0x0183
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r1 = r0.q
                boolean r1 = r1.c()
                if (r1 != 0) goto L_0x0174
                return
            L_0x0174:
                com.google.android.exoplayer2.util.ParsableNalUnitBitArray r1 = r0.q
                int r1 = r1.e()
                r22 = r1
                r21 = r2
                r19 = 0
                r20 = 0
                goto L_0x0192
            L_0x0183:
                r21 = r2
                r19 = 0
                r20 = 0
                goto L_0x0190
            L_0x018a:
                r19 = 0
            L_0x018c:
                r20 = 0
            L_0x018e:
                r21 = 0
            L_0x0190:
                r22 = 0
            L_0x0192:
                com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader$SliceHeaderData r8 = r0.j
                r8.a(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
                r0.g = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.H264Reader.SampleReader.a(byte[], int, int):void");
        }
    }

    public H264Reader(SeiReader seiReader, boolean z, boolean z2) {
        this.a = seiReader;
        this.b = z;
        this.c = z2;
    }

    private void a(byte[] bArr, int i2, int i3) {
        if (!this.l || this.k.c) {
            this.d.a(bArr, i2, i3);
            this.e.a(bArr, i2, i3);
        }
        this.f.a(bArr, i2, i3);
        this.k.a(bArr, i2, i3);
    }

    public final void a() {
        this.g = 0;
        this.n = false;
        NalUnitUtil.a(this.h);
        this.d.a();
        this.e.a();
        this.f.a();
        SampleReader sampleReader = this.k;
        if (sampleReader != null) {
            sampleReader.a();
        }
    }

    public final void a(long j2, int i2) {
        this.m = j2;
        this.n |= (i2 & 2) != 0;
    }

    public final void a(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.a();
        this.i = trackIdGenerator.c();
        TrackOutput a2 = extractorOutput.a(trackIdGenerator.b(), 2);
        this.j = a2;
        this.k = new SampleReader(a2, this.b, this.c);
        this.a.a(extractorOutput, trackIdGenerator);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0206, code lost:
        if (r1.e != 1) goto L_0x020a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x013c  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01ae  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x01c7 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01d9  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0203  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0209  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x020e  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x022d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.android.exoplayer2.util.ParsableByteArray r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            com.google.android.exoplayer2.extractor.TrackOutput r2 = r0.j
            com.google.android.exoplayer2.util.Assertions.a((java.lang.Object) r2)
            com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader r2 = r0.k
            com.google.android.exoplayer2.util.Util.a((java.lang.Object) r2)
            int r2 = r1.b
            int r3 = r1.c
            byte[] r4 = r1.a
            long r5 = r0.g
            int r7 = r19.a()
            long r7 = (long) r7
            long r5 = r5 + r7
            r0.g = r5
            com.google.android.exoplayer2.extractor.TrackOutput r5 = r0.j
            int r6 = r19.a()
            r5.b(r1, r6)
        L_0x0027:
            boolean[] r1 = r0.h
            int r1 = com.google.android.exoplayer2.util.NalUnitUtil.a(r4, r2, r3, r1)
            if (r1 != r3) goto L_0x0033
            r0.a(r4, r2, r3)
            return
        L_0x0033:
            int r5 = com.google.android.exoplayer2.util.NalUnitUtil.b(r4, r1)
            int r6 = r1 - r2
            if (r6 <= 0) goto L_0x003e
            r0.a(r4, r2, r1)
        L_0x003e:
            int r2 = r3 - r1
            long r7 = r0.g
            long r9 = (long) r2
            long r7 = r7 - r9
            if (r6 >= 0) goto L_0x0048
            int r6 = -r6
            goto L_0x0049
        L_0x0048:
            r6 = 0
        L_0x0049:
            long r10 = r0.m
            boolean r12 = r0.l
            if (r12 == 0) goto L_0x005c
            com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader r12 = r0.k
            boolean r12 = r12.c
            if (r12 == 0) goto L_0x0056
            goto L_0x005c
        L_0x0056:
            r16 = r3
            r17 = r4
            goto L_0x0134
        L_0x005c:
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r12 = r0.d
            r12.b(r6)
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r12 = r0.e
            r12.b(r6)
            boolean r12 = r0.l
            r14 = 3
            if (r12 != 0) goto L_0x00f9
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r12 = r0.d
            boolean r12 = r12.a
            if (r12 == 0) goto L_0x0056
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r12 = r0.e
            boolean r12 = r12.a
            if (r12 == 0) goto L_0x0056
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r15 = r0.d
            byte[] r15 = r15.b
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r9 = r0.d
            int r9 = r9.c
            byte[] r9 = java.util.Arrays.copyOf(r15, r9)
            r12.add(r9)
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r9 = r0.e
            byte[] r9 = r9.b
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r15 = r0.e
            int r15 = r15.c
            byte[] r9 = java.util.Arrays.copyOf(r9, r15)
            r12.add(r9)
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r9 = r0.d
            byte[] r9 = r9.b
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r15 = r0.d
            int r15 = r15.c
            com.google.android.exoplayer2.util.NalUnitUtil$SpsData r9 = com.google.android.exoplayer2.util.NalUnitUtil.a(r9, r14, r15)
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r14 = r0.e
            byte[] r14 = r14.b
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r15 = r0.e
            int r15 = r15.c
            com.google.android.exoplayer2.util.NalUnitUtil$PpsData r14 = com.google.android.exoplayer2.util.NalUnitUtil.d(r14, r15)
            int r15 = r9.a
            int r13 = r9.b
            r16 = r3
            int r3 = r9.c
            java.lang.String r3 = com.google.android.exoplayer2.util.CodecSpecificDataUtil.a((int) r15, (int) r13, (int) r3)
            com.google.android.exoplayer2.extractor.TrackOutput r13 = r0.j
            com.google.android.exoplayer2.Format$Builder r15 = new com.google.android.exoplayer2.Format$Builder
            r15.<init>()
            r17 = r4
            java.lang.String r4 = r0.i
            r15.a = r4
            java.lang.String r4 = "video/avc"
            r15.k = r4
            r15.h = r3
            int r3 = r9.e
            r15.p = r3
            int r3 = r9.f
            r15.q = r3
            float r3 = r9.g
            r15.t = r3
            r15.m = r12
            com.google.android.exoplayer2.Format r3 = r15.a()
            r13.a(r3)
            r3 = 1
            r0.l = r3
            com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader r3 = r0.k
            r3.a((com.google.android.exoplayer2.util.NalUnitUtil.SpsData) r9)
            com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader r3 = r0.k
            r3.a((com.google.android.exoplayer2.util.NalUnitUtil.PpsData) r14)
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r3 = r0.d
            r3.a()
            goto L_0x0131
        L_0x00f9:
            r16 = r3
            r17 = r4
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r3 = r0.d
            boolean r3 = r3.a
            if (r3 == 0) goto L_0x011a
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r3 = r0.d
            byte[] r3 = r3.b
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r4 = r0.d
            int r4 = r4.c
            com.google.android.exoplayer2.util.NalUnitUtil$SpsData r3 = com.google.android.exoplayer2.util.NalUnitUtil.a(r3, r14, r4)
            com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader r4 = r0.k
            r4.a((com.google.android.exoplayer2.util.NalUnitUtil.SpsData) r3)
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r3 = r0.d
        L_0x0116:
            r3.a()
            goto L_0x0134
        L_0x011a:
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r3 = r0.e
            boolean r3 = r3.a
            if (r3 == 0) goto L_0x0134
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r3 = r0.e
            byte[] r3 = r3.b
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r4 = r0.e
            int r4 = r4.c
            com.google.android.exoplayer2.util.NalUnitUtil$PpsData r3 = com.google.android.exoplayer2.util.NalUnitUtil.d(r3, r4)
            com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader r4 = r0.k
            r4.a((com.google.android.exoplayer2.util.NalUnitUtil.PpsData) r3)
        L_0x0131:
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r3 = r0.e
            goto L_0x0116
        L_0x0134:
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r3 = r0.f
            boolean r3 = r3.b(r6)
            if (r3 == 0) goto L_0x015e
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r3 = r0.f
            byte[] r3 = r3.b
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r4 = r0.f
            int r4 = r4.c
            int r3 = com.google.android.exoplayer2.util.NalUnitUtil.a((byte[]) r3, (int) r4)
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.o
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r6 = r0.f
            byte[] r6 = r6.b
            r4.a((byte[]) r6, (int) r3)
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r0.o
            r4 = 4
            r3.d(r4)
            com.google.android.exoplayer2.extractor.ts.SeiReader r3 = r0.a
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.o
            r3.a((long) r10, (com.google.android.exoplayer2.util.ParsableByteArray) r4)
        L_0x015e:
            com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader r3 = r0.k
            boolean r4 = r0.l
            boolean r6 = r0.n
            int r9 = r3.e
            r10 = 9
            if (r9 == r10) goto L_0x017b
            boolean r9 = r3.c
            if (r9 == 0) goto L_0x0179
            com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader$SliceHeaderData r9 = r3.j
            com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader$SliceHeaderData r10 = r3.i
            boolean r9 = com.google.android.exoplayer2.extractor.ts.H264Reader.SampleReader.SliceHeaderData.a(r9, r10)
            if (r9 == 0) goto L_0x0179
            goto L_0x017b
        L_0x0179:
            r4 = r1
            goto L_0x01a9
        L_0x017b:
            if (r4 == 0) goto L_0x019a
            boolean r4 = r3.k
            if (r4 == 0) goto L_0x019a
            long r9 = r3.f
            long r9 = r7 - r9
            int r4 = (int) r9
            int r14 = r2 + r4
            boolean r12 = r3.n
            long r9 = r3.f
            r4 = r1
            long r1 = r3.l
            long r9 = r9 - r1
            int r13 = (int) r9
            com.google.android.exoplayer2.extractor.TrackOutput r9 = r3.a
            long r10 = r3.m
            r15 = 0
            r9.a(r10, r12, r13, r14, r15)
            goto L_0x019b
        L_0x019a:
            r4 = r1
        L_0x019b:
            long r1 = r3.f
            r3.l = r1
            long r1 = r3.h
            r3.m = r1
            r1 = 0
            r3.n = r1
            r1 = 1
            r3.k = r1
        L_0x01a9:
            boolean r1 = r3.b
            r2 = 2
            if (r1 == 0) goto L_0x01c0
            com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader$SliceHeaderData r1 = r3.j
            boolean r6 = r1.a
            if (r6 == 0) goto L_0x01bf
            int r6 = r1.b
            r9 = 7
            if (r6 == r9) goto L_0x01bd
            int r1 = r1.b
            if (r1 != r2) goto L_0x01bf
        L_0x01bd:
            r6 = 1
            goto L_0x01c0
        L_0x01bf:
            r6 = 0
        L_0x01c0:
            boolean r1 = r3.n
            int r9 = r3.e
            r10 = 5
            if (r9 == r10) goto L_0x01d1
            if (r6 == 0) goto L_0x01cf
            int r6 = r3.e
            r9 = 1
            if (r6 != r9) goto L_0x01cf
            goto L_0x01d1
        L_0x01cf:
            r6 = 0
            goto L_0x01d2
        L_0x01d1:
            r6 = 1
        L_0x01d2:
            r1 = r1 | r6
            r3.n = r1
            boolean r1 = r3.n
            if (r1 == 0) goto L_0x01dc
            r1 = 0
            r0.n = r1
        L_0x01dc:
            long r11 = r0.m
            boolean r1 = r0.l
            if (r1 == 0) goto L_0x01e8
            com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader r1 = r0.k
            boolean r1 = r1.c
            if (r1 == 0) goto L_0x01f2
        L_0x01e8:
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r1 = r0.d
            r1.a(r5)
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r1 = r0.e
            r1.a(r5)
        L_0x01f2:
            com.google.android.exoplayer2.extractor.ts.NalUnitTargetBuffer r1 = r0.f
            r1.a(r5)
            com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader r1 = r0.k
            r1.e = r5
            r1.h = r11
            r1.f = r7
            boolean r3 = r1.b
            if (r3 == 0) goto L_0x0209
            int r3 = r1.e
            r5 = 1
            if (r3 == r5) goto L_0x021a
            goto L_0x020a
        L_0x0209:
            r5 = 1
        L_0x020a:
            boolean r3 = r1.c
            if (r3 == 0) goto L_0x022d
            int r3 = r1.e
            if (r3 == r10) goto L_0x021a
            int r3 = r1.e
            if (r3 == r5) goto L_0x021a
            int r3 = r1.e
            if (r3 != r2) goto L_0x022d
        L_0x021a:
            com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader$SliceHeaderData r2 = r1.i
            com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader$SliceHeaderData r3 = r1.j
            r1.i = r3
            r1.j = r2
            com.google.android.exoplayer2.extractor.ts.H264Reader$SampleReader$SliceHeaderData r2 = r1.j
            r2.a()
            r2 = 0
            r1.d = r2
            r2 = 1
            r1.g = r2
        L_0x022d:
            int r2 = r4 + 3
            r3 = r16
            r4 = r17
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.H264Reader.a(com.google.android.exoplayer2.util.ParsableByteArray):void");
    }

    public final void b() {
    }
}

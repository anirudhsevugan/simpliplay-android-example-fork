package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class TsExtractor implements Extractor {
    /* access modifiers changed from: private */
    public final int a;
    private final int b;
    /* access modifiers changed from: private */
    public final List c;
    private final ParsableByteArray d;
    private final SparseIntArray e;
    /* access modifiers changed from: private */
    public final TsPayloadReader.Factory f;
    /* access modifiers changed from: private */
    public final SparseArray g;
    /* access modifiers changed from: private */
    public final SparseBooleanArray h;
    /* access modifiers changed from: private */
    public final SparseBooleanArray i;
    private final TsDurationReader j;
    private TsBinarySearchSeeker k;
    /* access modifiers changed from: private */
    public ExtractorOutput l;
    /* access modifiers changed from: private */
    public int m;
    /* access modifiers changed from: private */
    public boolean n;
    private boolean o;
    private boolean p;
    /* access modifiers changed from: private */
    public TsPayloadReader q;
    private int r;
    /* access modifiers changed from: private */
    public int s;

    class PatReader implements SectionPayloadReader {
        private final ParsableBitArray a = new ParsableBitArray(new byte[4]);

        public PatReader() {
        }

        public final void a(ParsableByteArray parsableByteArray) {
            if (parsableByteArray.c() == 0 && (parsableByteArray.c() & 128) != 0) {
                parsableByteArray.e(6);
                int a2 = parsableByteArray.a() / 4;
                for (int i = 0; i < a2; i++) {
                    parsableByteArray.a(this.a, 4);
                    int c = this.a.c(16);
                    this.a.b(3);
                    if (c == 0) {
                        this.a.b(13);
                    } else {
                        int c2 = this.a.c(13);
                        if (TsExtractor.this.g.get(c2) == null) {
                            TsExtractor.this.g.put(c2, new SectionReader(new PmtReader(c2)));
                            TsExtractor.b(TsExtractor.this);
                        }
                    }
                }
                if (TsExtractor.this.a != 2) {
                    TsExtractor.this.g.remove(0);
                }
            }
        }

        public final void a(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        }
    }

    class PmtReader implements SectionPayloadReader {
        private final ParsableBitArray a = new ParsableBitArray(new byte[5]);
        private final SparseArray b = new SparseArray();
        private final SparseIntArray c = new SparseIntArray();
        private final int d;

        public PmtReader(int i) {
            this.d = i;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:48:0x0168, code lost:
            if (r27.c() == r14) goto L_0x0144;
         */
        /* JADX WARNING: Removed duplicated region for block: B:117:0x023b A[SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:88:0x022e  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void a(com.google.android.exoplayer2.util.ParsableByteArray r27) {
            /*
                r26 = this;
                r0 = r26
                r1 = r27
                int r2 = r27.c()
                r3 = 2
                if (r2 == r3) goto L_0x000c
                return
            L_0x000c:
                com.google.android.exoplayer2.extractor.ts.TsExtractor r2 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                int r2 = r2.a
                r4 = 0
                r5 = 1
                if (r2 == r5) goto L_0x0046
                com.google.android.exoplayer2.extractor.ts.TsExtractor r2 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                int r2 = r2.a
                if (r2 == r3) goto L_0x0046
                com.google.android.exoplayer2.extractor.ts.TsExtractor r2 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                int r2 = r2.m
                if (r2 != r5) goto L_0x0027
                goto L_0x0046
            L_0x0027:
                com.google.android.exoplayer2.util.TimestampAdjuster r2 = new com.google.android.exoplayer2.util.TimestampAdjuster
                com.google.android.exoplayer2.extractor.ts.TsExtractor r6 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                java.util.List r6 = r6.c
                java.lang.Object r6 = r6.get(r4)
                com.google.android.exoplayer2.util.TimestampAdjuster r6 = (com.google.android.exoplayer2.util.TimestampAdjuster) r6
                long r6 = r6.a()
                r2.<init>(r6)
                com.google.android.exoplayer2.extractor.ts.TsExtractor r6 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                java.util.List r6 = r6.c
                r6.add(r2)
                goto L_0x0052
            L_0x0046:
                com.google.android.exoplayer2.extractor.ts.TsExtractor r2 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                java.util.List r2 = r2.c
                java.lang.Object r2 = r2.get(r4)
                com.google.android.exoplayer2.util.TimestampAdjuster r2 = (com.google.android.exoplayer2.util.TimestampAdjuster) r2
            L_0x0052:
                int r6 = r27.c()
                r6 = r6 & 128(0x80, float:1.794E-43)
                if (r6 != 0) goto L_0x005b
                return
            L_0x005b:
                r1.e(r5)
                int r6 = r27.d()
                r7 = 3
                r1.e(r7)
                com.google.android.exoplayer2.util.ParsableBitArray r8 = r0.a
                r1.a((com.google.android.exoplayer2.util.ParsableBitArray) r8, (int) r3)
                com.google.android.exoplayer2.util.ParsableBitArray r8 = r0.a
                r8.b(r7)
                com.google.android.exoplayer2.extractor.ts.TsExtractor r8 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                com.google.android.exoplayer2.util.ParsableBitArray r9 = r0.a
                r10 = 13
                int r9 = r9.c(r10)
                int unused = r8.s = r9
                com.google.android.exoplayer2.util.ParsableBitArray r8 = r0.a
                r1.a((com.google.android.exoplayer2.util.ParsableBitArray) r8, (int) r3)
                com.google.android.exoplayer2.util.ParsableBitArray r8 = r0.a
                r9 = 4
                r8.b(r9)
                com.google.android.exoplayer2.util.ParsableBitArray r8 = r0.a
                r11 = 12
                int r8 = r8.c(r11)
                r1.e(r8)
                com.google.android.exoplayer2.extractor.ts.TsExtractor r8 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                int r8 = r8.a
                r12 = 8192(0x2000, float:1.14794E-41)
                r13 = 0
                r14 = 21
                if (r8 != r3) goto L_0x00d0
                com.google.android.exoplayer2.extractor.ts.TsExtractor r8 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                com.google.android.exoplayer2.extractor.ts.TsPayloadReader r8 = r8.q
                if (r8 != 0) goto L_0x00d0
                com.google.android.exoplayer2.extractor.ts.TsPayloadReader$EsInfo r8 = new com.google.android.exoplayer2.extractor.ts.TsPayloadReader$EsInfo
                byte[] r15 = com.google.android.exoplayer2.util.Util.f
                r8.<init>(r14, r13, r13, r15)
                com.google.android.exoplayer2.extractor.ts.TsExtractor r15 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                com.google.android.exoplayer2.extractor.ts.TsPayloadReader$Factory r13 = r15.f
                com.google.android.exoplayer2.extractor.ts.TsPayloadReader r8 = r13.a(r14, r8)
                com.google.android.exoplayer2.extractor.ts.TsPayloadReader unused = r15.q = r8
                com.google.android.exoplayer2.extractor.ts.TsExtractor r8 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                com.google.android.exoplayer2.extractor.ts.TsPayloadReader r8 = r8.q
                com.google.android.exoplayer2.extractor.ts.TsExtractor r13 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                com.google.android.exoplayer2.extractor.ExtractorOutput r13 = r13.l
                com.google.android.exoplayer2.extractor.ts.TsPayloadReader$TrackIdGenerator r15 = new com.google.android.exoplayer2.extractor.ts.TsPayloadReader$TrackIdGenerator
                r15.<init>(r6, r14, r12)
                r8.a(r2, r13, r15)
            L_0x00d0:
                android.util.SparseArray r8 = r0.b
                r8.clear()
                android.util.SparseIntArray r8 = r0.c
                r8.clear()
                int r8 = r27.a()
            L_0x00de:
                if (r8 <= 0) goto L_0x024a
                com.google.android.exoplayer2.util.ParsableBitArray r13 = r0.a
                r15 = 5
                r1.a((com.google.android.exoplayer2.util.ParsableBitArray) r13, (int) r15)
                com.google.android.exoplayer2.util.ParsableBitArray r13 = r0.a
                r5 = 8
                int r5 = r13.c(r5)
                com.google.android.exoplayer2.util.ParsableBitArray r13 = r0.a
                r13.b(r7)
                com.google.android.exoplayer2.util.ParsableBitArray r13 = r0.a
                int r13 = r13.c(r10)
                com.google.android.exoplayer2.util.ParsableBitArray r10 = r0.a
                r10.b(r9)
                com.google.android.exoplayer2.util.ParsableBitArray r10 = r0.a
                int r10 = r10.c(r11)
                int r11 = r1.b
                int r12 = r11 + r10
                r16 = -1
                r3 = -1
                r17 = 0
                r18 = 0
            L_0x010f:
                int r4 = r1.b
                if (r4 >= r12) goto L_0x01c6
                int r4 = r27.c()
                int r19 = r27.c()
                int r9 = r1.b
                int r9 = r9 + r19
                if (r9 > r12) goto L_0x01c6
                r19 = 172(0xac, float:2.41E-43)
                r20 = 135(0x87, float:1.89E-43)
                r21 = 129(0x81, float:1.81E-43)
                if (r4 != r15) goto L_0x0151
                long r22 = r27.h()
                r24 = 1094921523(0x41432d33, double:5.409631094E-315)
                int r4 = (r22 > r24 ? 1 : (r22 == r24 ? 0 : -1))
                if (r4 != 0) goto L_0x0135
                goto L_0x0155
            L_0x0135:
                r24 = 1161904947(0x45414333, double:5.74057318E-315)
                int r4 = (r22 > r24 ? 1 : (r22 == r24 ? 0 : -1))
                if (r4 != 0) goto L_0x013d
                goto L_0x015d
            L_0x013d:
                r20 = 1094921524(0x41432d34, double:5.4096311E-315)
                int r4 = (r22 > r20 ? 1 : (r22 == r20 ? 0 : -1))
                if (r4 != 0) goto L_0x0147
            L_0x0144:
                r3 = 172(0xac, float:2.41E-43)
                goto L_0x0157
            L_0x0147:
                r20 = 1212503619(0x48455643, double:5.990563836E-315)
                int r4 = (r22 > r20 ? 1 : (r22 == r20 ? 0 : -1))
                if (r4 != 0) goto L_0x0157
                r3 = 36
                goto L_0x0157
            L_0x0151:
                r15 = 106(0x6a, float:1.49E-43)
                if (r4 != r15) goto L_0x0159
            L_0x0155:
                r3 = 129(0x81, float:1.81E-43)
            L_0x0157:
                r7 = 4
                goto L_0x01b9
            L_0x0159:
                r15 = 122(0x7a, float:1.71E-43)
                if (r4 != r15) goto L_0x0160
            L_0x015d:
                r3 = 135(0x87, float:1.89E-43)
                goto L_0x0157
            L_0x0160:
                r15 = 127(0x7f, float:1.78E-43)
                if (r4 != r15) goto L_0x016b
                int r4 = r27.c()
                if (r4 != r14) goto L_0x0157
                goto L_0x0144
            L_0x016b:
                r15 = 123(0x7b, float:1.72E-43)
                if (r4 != r15) goto L_0x0172
                r3 = 138(0x8a, float:1.93E-43)
                goto L_0x0157
            L_0x0172:
                r15 = 10
                if (r4 != r15) goto L_0x017f
                java.lang.String r4 = r1.f(r7)
                java.lang.String r17 = r4.trim()
                goto L_0x0157
            L_0x017f:
                r15 = 89
                if (r4 != r15) goto L_0x01b2
                java.util.ArrayList r3 = new java.util.ArrayList
                r3.<init>()
            L_0x0188:
                int r4 = r1.b
                if (r4 >= r9) goto L_0x01ac
                java.lang.String r4 = r1.f(r7)
                java.lang.String r4 = r4.trim()
                r27.c()
                r7 = 4
                byte[] r15 = new byte[r7]
                r14 = 0
                r1.a(r15, r14, r7)
                com.google.android.exoplayer2.extractor.ts.TsPayloadReader$DvbSubtitleInfo r14 = new com.google.android.exoplayer2.extractor.ts.TsPayloadReader$DvbSubtitleInfo
                r14.<init>(r4, r15)
                r3.add(r14)
                r7 = 3
                r14 = 21
                r15 = 89
                goto L_0x0188
            L_0x01ac:
                r7 = 4
                r18 = r3
                r3 = 89
                goto L_0x01b9
            L_0x01b2:
                r7 = 4
                r14 = 111(0x6f, float:1.56E-43)
                if (r4 != r14) goto L_0x01b9
                r3 = 257(0x101, float:3.6E-43)
            L_0x01b9:
                int r4 = r1.b
                int r9 = r9 - r4
                r1.e(r9)
                r7 = 3
                r9 = 4
                r14 = 21
                r15 = 5
                goto L_0x010f
            L_0x01c6:
                r7 = 4
                r1.d(r12)
                com.google.android.exoplayer2.extractor.ts.TsPayloadReader$EsInfo r4 = new com.google.android.exoplayer2.extractor.ts.TsPayloadReader$EsInfo
                byte[] r9 = r1.a
                byte[] r9 = java.util.Arrays.copyOfRange(r9, r11, r12)
                r11 = r17
                r12 = r18
                r4.<init>(r3, r11, r12, r9)
                r3 = 6
                if (r5 == r3) goto L_0x01df
                r3 = 5
                if (r5 != r3) goto L_0x01e1
            L_0x01df:
                int r5 = r4.a
            L_0x01e1:
                int r10 = r10 + 5
                int r8 = r8 - r10
                com.google.android.exoplayer2.extractor.ts.TsExtractor r3 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                int r3 = r3.a
                r9 = 2
                if (r3 != r9) goto L_0x01ef
                r3 = r5
                goto L_0x01f0
            L_0x01ef:
                r3 = r13
            L_0x01f0:
                com.google.android.exoplayer2.extractor.ts.TsExtractor r10 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                android.util.SparseBooleanArray r10 = r10.h
                boolean r10 = r10.get(r3)
                if (r10 != 0) goto L_0x0239
                com.google.android.exoplayer2.extractor.ts.TsExtractor r10 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                int r10 = r10.a
                if (r10 != r9) goto L_0x020f
                r9 = 21
                if (r5 != r9) goto L_0x0211
                com.google.android.exoplayer2.extractor.ts.TsExtractor r4 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                com.google.android.exoplayer2.extractor.ts.TsPayloadReader r4 = r4.q
                goto L_0x021b
            L_0x020f:
                r9 = 21
            L_0x0211:
                com.google.android.exoplayer2.extractor.ts.TsExtractor r10 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                com.google.android.exoplayer2.extractor.ts.TsPayloadReader$Factory r10 = r10.f
                com.google.android.exoplayer2.extractor.ts.TsPayloadReader r4 = r10.a(r5, r4)
            L_0x021b:
                com.google.android.exoplayer2.extractor.ts.TsExtractor r5 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                int r5 = r5.a
                r10 = 2
                if (r5 != r10) goto L_0x022e
                android.util.SparseIntArray r5 = r0.c
                r10 = 8192(0x2000, float:1.14794E-41)
                int r5 = r5.get(r3, r10)
                if (r13 >= r5) goto L_0x023b
            L_0x022e:
                android.util.SparseIntArray r5 = r0.c
                r5.put(r3, r13)
                android.util.SparseArray r5 = r0.b
                r5.put(r3, r4)
                goto L_0x023b
            L_0x0239:
                r9 = 21
            L_0x023b:
                r3 = 2
                r4 = 0
                r5 = 1
                r7 = 3
                r9 = 4
                r10 = 13
                r11 = 12
                r12 = 8192(0x2000, float:1.14794E-41)
                r14 = 21
                goto L_0x00de
            L_0x024a:
                android.util.SparseIntArray r1 = r0.c
                int r1 = r1.size()
                r14 = 0
            L_0x0251:
                if (r14 >= r1) goto L_0x02a6
                android.util.SparseIntArray r3 = r0.c
                int r3 = r3.keyAt(r14)
                android.util.SparseIntArray r4 = r0.c
                int r4 = r4.valueAt(r14)
                com.google.android.exoplayer2.extractor.ts.TsExtractor r5 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                android.util.SparseBooleanArray r5 = r5.h
                r7 = 1
                r5.put(r3, r7)
                com.google.android.exoplayer2.extractor.ts.TsExtractor r5 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                android.util.SparseBooleanArray r5 = r5.i
                r5.put(r4, r7)
                android.util.SparseArray r5 = r0.b
                java.lang.Object r5 = r5.valueAt(r14)
                com.google.android.exoplayer2.extractor.ts.TsPayloadReader r5 = (com.google.android.exoplayer2.extractor.ts.TsPayloadReader) r5
                if (r5 == 0) goto L_0x02a1
                com.google.android.exoplayer2.extractor.ts.TsExtractor r7 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                com.google.android.exoplayer2.extractor.ts.TsPayloadReader r7 = r7.q
                if (r5 == r7) goto L_0x0295
                com.google.android.exoplayer2.extractor.ts.TsExtractor r7 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                com.google.android.exoplayer2.extractor.ExtractorOutput r7 = r7.l
                com.google.android.exoplayer2.extractor.ts.TsPayloadReader$TrackIdGenerator r8 = new com.google.android.exoplayer2.extractor.ts.TsPayloadReader$TrackIdGenerator
                r9 = 8192(0x2000, float:1.14794E-41)
                r8.<init>(r6, r3, r9)
                r5.a(r2, r7, r8)
                goto L_0x0297
            L_0x0295:
                r9 = 8192(0x2000, float:1.14794E-41)
            L_0x0297:
                com.google.android.exoplayer2.extractor.ts.TsExtractor r3 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                android.util.SparseArray r3 = r3.g
                r3.put(r4, r5)
                goto L_0x02a3
            L_0x02a1:
                r9 = 8192(0x2000, float:1.14794E-41)
            L_0x02a3:
                int r14 = r14 + 1
                goto L_0x0251
            L_0x02a6:
                com.google.android.exoplayer2.extractor.ts.TsExtractor r1 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                int r1 = r1.a
                r2 = 2
                if (r1 != r2) goto L_0x02cc
                com.google.android.exoplayer2.extractor.ts.TsExtractor r1 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                boolean r1 = r1.n
                if (r1 != 0) goto L_0x0304
                com.google.android.exoplayer2.extractor.ts.TsExtractor r1 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                com.google.android.exoplayer2.extractor.ExtractorOutput r1 = r1.l
                r1.c_()
                com.google.android.exoplayer2.extractor.ts.TsExtractor r1 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                r2 = 0
                int unused = r1.m = r2
                com.google.android.exoplayer2.extractor.ts.TsExtractor r1 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                boolean unused = r1.n = true
                return
            L_0x02cc:
                r2 = 0
                com.google.android.exoplayer2.extractor.ts.TsExtractor r1 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                android.util.SparseArray r1 = r1.g
                int r3 = r0.d
                r1.remove(r3)
                com.google.android.exoplayer2.extractor.ts.TsExtractor r1 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                int r3 = r1.a
                r4 = 1
                if (r3 != r4) goto L_0x02e3
                r4 = 0
                goto L_0x02eb
            L_0x02e3:
                com.google.android.exoplayer2.extractor.ts.TsExtractor r2 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                int r2 = r2.m
                int r4 = r2 + -1
            L_0x02eb:
                int unused = r1.m = r4
                com.google.android.exoplayer2.extractor.ts.TsExtractor r1 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                int r1 = r1.m
                if (r1 != 0) goto L_0x0304
                com.google.android.exoplayer2.extractor.ts.TsExtractor r1 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                com.google.android.exoplayer2.extractor.ExtractorOutput r1 = r1.l
                r1.c_()
                com.google.android.exoplayer2.extractor.ts.TsExtractor r1 = com.google.android.exoplayer2.extractor.ts.TsExtractor.this
                boolean unused = r1.n = true
            L_0x0304:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.TsExtractor.PmtReader.a(com.google.android.exoplayer2.util.ParsableByteArray):void");
        }

        public final void a(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        }
    }

    static {
        ExtractorsFactory extractorsFactory = TsExtractor$$Lambda$0.b;
    }

    public TsExtractor() {
        this((byte) 0);
    }

    private TsExtractor(byte b2) {
        this(1, 112800);
    }

    public TsExtractor(int i2, int i3) {
        this(i2, new TimestampAdjuster(0), new DefaultTsPayloadReaderFactory((byte) 0), i3);
    }

    private TsExtractor(int i2, TimestampAdjuster timestampAdjuster, TsPayloadReader.Factory factory, int i3) {
        this.f = (TsPayloadReader.Factory) Assertions.b((Object) factory);
        this.b = i3;
        this.a = i2;
        if (i2 == 1 || i2 == 2) {
            this.c = Collections.singletonList(timestampAdjuster);
        } else {
            ArrayList arrayList = new ArrayList();
            this.c = arrayList;
            arrayList.add(timestampAdjuster);
        }
        this.d = new ParsableByteArray(new byte[9400], 0);
        this.h = new SparseBooleanArray();
        this.i = new SparseBooleanArray();
        this.g = new SparseArray();
        this.e = new SparseIntArray();
        this.j = new TsDurationReader(i3);
        this.s = -1;
        b();
    }

    public TsExtractor(TimestampAdjuster timestampAdjuster, TsPayloadReader.Factory factory) {
        this(2, timestampAdjuster, factory, 112800);
    }

    static final /* synthetic */ Extractor[] a() {
        return new Extractor[]{new TsExtractor()};
    }

    static /* synthetic */ int b(TsExtractor tsExtractor) {
        int i2 = tsExtractor.m;
        tsExtractor.m = i2 + 1;
        return i2;
    }

    private void b() {
        this.h.clear();
        this.g.clear();
        SparseArray a2 = this.f.a();
        int size = a2.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.g.put(a2.keyAt(i2), (TsPayloadReader) a2.valueAt(i2));
        }
        this.g.put(0, new SectionReader(new PatReader()));
        this.q = null;
    }

    public final int a(ExtractorInput extractorInput, PositionHolder positionHolder) {
        boolean z;
        boolean z2;
        ExtractorInput extractorInput2;
        boolean z3;
        long j2;
        SeekMap seekMap;
        ExtractorOutput extractorOutput;
        long j3;
        ExtractorInput extractorInput3 = extractorInput;
        PositionHolder positionHolder2 = positionHolder;
        long d2 = extractorInput.d();
        if (this.n) {
            long j4 = -9223372036854775807L;
            if (!((d2 == -1 || this.a == 2) ? false : true) || this.j.d) {
                if (!this.o) {
                    this.o = true;
                    if (this.j.i != -9223372036854775807L) {
                        TsBinarySearchSeeker tsBinarySearchSeeker = r3;
                        j2 = 0;
                        z = false;
                        z2 = true;
                        TsBinarySearchSeeker tsBinarySearchSeeker2 = new TsBinarySearchSeeker(this.j.b, this.j.i, d2, this.s, this.b);
                        this.k = tsBinarySearchSeeker;
                        extractorOutput = this.l;
                        seekMap = tsBinarySearchSeeker.a;
                    } else {
                        j2 = 0;
                        z2 = true;
                        z = false;
                        extractorOutput = this.l;
                        seekMap = new SeekMap.Unseekable(this.j.i);
                    }
                    extractorOutput.a(seekMap);
                } else {
                    j2 = 0;
                    z2 = true;
                    z = false;
                }
                if (this.p) {
                    this.p = z;
                    a(j2, j2);
                    if (extractorInput.c() != j2) {
                        positionHolder.a = j2;
                        return z2 ? 1 : 0;
                    }
                }
                PositionHolder positionHolder3 = positionHolder;
                TsBinarySearchSeeker tsBinarySearchSeeker3 = this.k;
                if (tsBinarySearchSeeker3 != null && tsBinarySearchSeeker3.a()) {
                    return this.k.a(extractorInput, positionHolder3);
                }
                extractorInput2 = extractorInput;
            } else {
                TsDurationReader tsDurationReader = this.j;
                int i2 = this.s;
                if (i2 <= 0) {
                    return tsDurationReader.a(extractorInput3);
                }
                if (!tsDurationReader.f) {
                    long d3 = extractorInput.d();
                    int min = (int) Math.min((long) tsDurationReader.a, d3);
                    long j5 = d3 - ((long) min);
                    if (extractorInput.c() != j5) {
                        positionHolder2.a = j5;
                        return 1;
                    }
                    tsDurationReader.c.a(min);
                    extractorInput.a();
                    extractorInput3.d(tsDurationReader.c.a, 0, min);
                    ParsableByteArray parsableByteArray = tsDurationReader.c;
                    int i3 = parsableByteArray.b;
                    int i4 = parsableByteArray.c - 1;
                    while (true) {
                        if (i4 < i3) {
                            break;
                        }
                        if (parsableByteArray.a[i4] == 71) {
                            long a2 = TsUtil.a(parsableByteArray, i4, i2);
                            if (a2 != -9223372036854775807L) {
                                j4 = a2;
                                break;
                            }
                        }
                        i4--;
                    }
                    tsDurationReader.h = j4;
                    tsDurationReader.f = true;
                    return 0;
                } else if (tsDurationReader.h == -9223372036854775807L) {
                    return tsDurationReader.a(extractorInput3);
                } else {
                    if (!tsDurationReader.e) {
                        int min2 = (int) Math.min((long) tsDurationReader.a, extractorInput.d());
                        if (extractorInput.c() != 0) {
                            positionHolder2.a = 0;
                            return 1;
                        }
                        tsDurationReader.c.a(min2);
                        extractorInput.a();
                        extractorInput3.d(tsDurationReader.c.a, 0, min2);
                        ParsableByteArray parsableByteArray2 = tsDurationReader.c;
                        int i5 = parsableByteArray2.b;
                        int i6 = parsableByteArray2.c;
                        while (true) {
                            if (i5 >= i6) {
                                j3 = -9223372036854775807L;
                                break;
                            }
                            if (parsableByteArray2.a[i5] == 71) {
                                long a3 = TsUtil.a(parsableByteArray2, i5, i2);
                                if (a3 != -9223372036854775807L) {
                                    j3 = a3;
                                    break;
                                }
                            }
                            i5++;
                        }
                        tsDurationReader.g = j3;
                        tsDurationReader.e = true;
                        return 0;
                    } else if (tsDurationReader.g == -9223372036854775807L) {
                        return tsDurationReader.a(extractorInput3);
                    } else {
                        tsDurationReader.i = tsDurationReader.b.b(tsDurationReader.h) - tsDurationReader.b.b(tsDurationReader.g);
                        return tsDurationReader.a(extractorInput3);
                    }
                }
            }
        } else {
            extractorInput2 = extractorInput3;
            z2 = true;
            z = false;
        }
        byte[] bArr = this.d.a;
        if (9400 - this.d.b < 188) {
            int a4 = this.d.a();
            if (a4 > 0) {
                System.arraycopy(bArr, this.d.b, bArr, z, a4);
            }
            this.d.a(bArr, a4);
        }
        while (true) {
            if (this.d.a() >= 188) {
                z3 = true;
                break;
            }
            int i7 = this.d.c;
            int a5 = extractorInput2.a(bArr, i7, 9400 - i7);
            if (a5 == -1) {
                z3 = false;
                break;
            }
            this.d.c(i7 + a5);
        }
        if (!z3) {
            return -1;
        }
        int i8 = this.d.b;
        int i9 = this.d.c;
        int a6 = TsUtil.a(this.d.a, i8, i9);
        this.d.d(a6);
        int i10 = a6 + 188;
        if (i10 > i9) {
            int i11 = this.r + (a6 - i8);
            this.r = i11;
            if (this.a == 2 && i11 > 376) {
                throw new ParserException("Cannot find sync byte. Most likely not a Transport Stream.");
            }
        } else {
            this.r = z;
        }
        int i12 = this.d.c;
        if (i10 > i12) {
            return z;
        }
        int j6 = this.d.j();
        if ((8388608 & j6) == 0) {
            int i13 = ((4194304 & j6) != 0 ? 1 : 0) | 0;
            int i14 = (2096896 & j6) >> 8;
            boolean z4 = (j6 & 32) != 0;
            TsPayloadReader tsPayloadReader = (j6 & 16) != 0 ? (TsPayloadReader) this.g.get(i14) : null;
            if (tsPayloadReader != null) {
                if (this.a != 2) {
                    int i15 = j6 & 15;
                    int i16 = this.e.get(i14, i15 - 1);
                    this.e.put(i14, i15);
                    if (i16 != i15) {
                        if (i15 != ((i16 + z2) & 15)) {
                            tsPayloadReader.a();
                        }
                    }
                }
                if (z4) {
                    int c2 = this.d.c();
                    i13 |= (this.d.c() & 64) != 0 ? 2 : 0;
                    this.d.e(c2 - (z2 ? 1 : 0));
                }
                boolean z5 = this.n;
                if (this.a == 2 || z5 || !this.i.get(i14, z)) {
                    this.d.c(i10);
                    tsPayloadReader.a(this.d, i13);
                    this.d.c(i12);
                }
                if (this.a != 2 && !z5 && this.n && d2 != -1) {
                    this.p = z2;
                }
            }
        }
        this.d.d(i10);
        return z ? 1 : 0;
    }

    public final void a(long j2, long j3) {
        TsBinarySearchSeeker tsBinarySearchSeeker;
        Assertions.b(this.a != 2);
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            TimestampAdjuster timestampAdjuster = (TimestampAdjuster) this.c.get(i2);
            if ((timestampAdjuster.c() == -9223372036854775807L) || !(timestampAdjuster.c() == 0 || timestampAdjuster.a() == j3)) {
                timestampAdjuster.a(j3);
            }
        }
        if (!(j3 == 0 || (tsBinarySearchSeeker = this.k) == null)) {
            tsBinarySearchSeeker.a(j3);
        }
        this.d.a(0);
        this.e.clear();
        for (int i3 = 0; i3 < this.g.size(); i3++) {
            ((TsPayloadReader) this.g.valueAt(i3)).a();
        }
        this.r = 0;
    }

    public final void a(ExtractorOutput extractorOutput) {
        this.l = extractorOutput;
    }

    public final boolean a(ExtractorInput extractorInput) {
        boolean z;
        byte[] bArr = this.d.a;
        extractorInput.d(bArr, 0, 940);
        for (int i2 = 0; i2 < 188; i2++) {
            int i3 = 0;
            while (true) {
                if (i3 >= 5) {
                    z = true;
                    break;
                } else if (bArr[(i3 * 188) + i2] != 71) {
                    z = false;
                    break;
                } else {
                    i3++;
                }
            }
            if (z) {
                extractorInput.b(i2);
                return true;
            }
        }
        return false;
    }
}

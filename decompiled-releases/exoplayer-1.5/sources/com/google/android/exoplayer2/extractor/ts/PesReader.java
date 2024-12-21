package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;

public final class PesReader implements TsPayloadReader {
    private final ElementaryStreamReader a;
    private final ParsableBitArray b = new ParsableBitArray(new byte[10]);
    private int c = 0;
    private int d;
    private TimestampAdjuster e;
    private boolean f;
    private boolean g;
    private boolean h;
    private int i;
    private int j;
    private boolean k;
    private long l;

    public PesReader(ElementaryStreamReader elementaryStreamReader) {
        this.a = elementaryStreamReader;
    }

    private void a(int i2) {
        this.c = i2;
        this.d = 0;
    }

    private boolean a(ParsableByteArray parsableByteArray, byte[] bArr, int i2) {
        int min = Math.min(parsableByteArray.a(), i2 - this.d);
        if (min <= 0) {
            return true;
        }
        if (bArr == null) {
            parsableByteArray.e(min);
        } else {
            parsableByteArray.a(bArr, this.d, min);
        }
        int i3 = this.d + min;
        this.d = i3;
        return i3 == i2;
    }

    public final void a() {
        this.c = 0;
        this.d = 0;
        this.h = false;
        this.a.a();
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01fa A[SYNTHETIC] */
    public final void a(com.google.android.exoplayer2.util.ParsableByteArray r16, int r17) {
        /*
            r15 = this;
            r0 = r15
            com.google.android.exoplayer2.util.TimestampAdjuster r1 = r0.e
            com.google.android.exoplayer2.util.Assertions.a((java.lang.Object) r1)
            r1 = r17 & 1
            java.lang.String r2 = "PesReader"
            r3 = -1
            r4 = 1
            if (r1 == 0) goto L_0x004c
            int r1 = r0.c
            switch(r1) {
                case 0: goto L_0x0046;
                case 1: goto L_0x0046;
                case 2: goto L_0x0041;
                case 3: goto L_0x0019;
                default: goto L_0x0013;
            }
        L_0x0013:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x0019:
            int r1 = r0.j
            if (r1 == r3) goto L_0x003b
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r6 = 59
            r5.<init>(r6)
            java.lang.String r6 = "Unexpected start indicator: expected "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r1 = r5.append(r1)
            java.lang.String r5 = " more bytes"
            java.lang.StringBuilder r1 = r1.append(r5)
            java.lang.String r1 = r1.toString()
            com.google.android.exoplayer2.util.Log.c(r2, r1)
        L_0x003b:
            com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader r1 = r0.a
            r1.b()
            goto L_0x0046
        L_0x0041:
            java.lang.String r1 = "Unexpected start indicator reading extended header"
            com.google.android.exoplayer2.util.Log.c(r2, r1)
        L_0x0046:
            r1 = r16
            r5 = r17
            r6 = r0
            goto L_0x008a
        L_0x004c:
            r1 = r16
            r5 = r17
            r6 = r0
        L_0x0051:
            int r7 = r1.a()
            if (r7 <= 0) goto L_0x01fa
            int r7 = r6.c
            r8 = 0
            switch(r7) {
                case 0: goto L_0x01f1;
                case 1: goto L_0x0147;
                case 2: goto L_0x008e;
                case 3: goto L_0x0063;
                default: goto L_0x005d;
            }
        L_0x005d:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x0063:
            int r7 = r1.a()
            int r9 = r6.j
            if (r9 != r3) goto L_0x006c
            goto L_0x006e
        L_0x006c:
            int r8 = r7 - r9
        L_0x006e:
            if (r8 <= 0) goto L_0x0077
            int r7 = r7 - r8
            int r8 = r1.b
            int r8 = r8 + r7
            r1.c(r8)
        L_0x0077:
            com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader r8 = r6.a
            r8.a(r1)
            int r8 = r6.j
            if (r8 == r3) goto L_0x0051
            int r8 = r8 - r7
            r6.j = r8
            if (r8 != 0) goto L_0x0051
            com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader r7 = r6.a
            r7.b()
        L_0x008a:
            r6.a(r4)
            goto L_0x0051
        L_0x008e:
            r7 = 10
            int r9 = r6.i
            int r7 = java.lang.Math.min(r7, r9)
            com.google.android.exoplayer2.util.ParsableBitArray r9 = r6.b
            byte[] r9 = r9.a
            boolean r7 = r6.a((com.google.android.exoplayer2.util.ParsableByteArray) r1, (byte[]) r9, (int) r7)
            if (r7 == 0) goto L_0x0051
            r7 = 0
            int r9 = r6.i
            boolean r7 = r6.a((com.google.android.exoplayer2.util.ParsableByteArray) r1, (byte[]) r7, (int) r9)
            if (r7 == 0) goto L_0x0051
            com.google.android.exoplayer2.util.ParsableBitArray r7 = r6.b
            r7.a(r8)
            r9 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r6.l = r9
            boolean r7 = r6.f
            r9 = 3
            r10 = 4
            if (r7 == 0) goto L_0x0132
            com.google.android.exoplayer2.util.ParsableBitArray r7 = r6.b
            r7.b(r10)
            com.google.android.exoplayer2.util.ParsableBitArray r7 = r6.b
            int r7 = r7.c(r9)
            long r11 = (long) r7
            r7 = 30
            long r11 = r11 << r7
            com.google.android.exoplayer2.util.ParsableBitArray r13 = r6.b
            r13.b(r4)
            com.google.android.exoplayer2.util.ParsableBitArray r13 = r6.b
            r14 = 15
            int r13 = r13.c(r14)
            int r13 = r13 << r14
            long r7 = (long) r13
            long r7 = r7 | r11
            com.google.android.exoplayer2.util.ParsableBitArray r11 = r6.b
            r11.b(r4)
            com.google.android.exoplayer2.util.ParsableBitArray r11 = r6.b
            int r11 = r11.c(r14)
            long r11 = (long) r11
            long r7 = r7 | r11
            com.google.android.exoplayer2.util.ParsableBitArray r11 = r6.b
            r11.b(r4)
            boolean r11 = r6.h
            if (r11 != 0) goto L_0x012a
            boolean r11 = r6.g
            if (r11 == 0) goto L_0x012a
            com.google.android.exoplayer2.util.ParsableBitArray r11 = r6.b
            r11.b(r10)
            com.google.android.exoplayer2.util.ParsableBitArray r11 = r6.b
            int r11 = r11.c(r9)
            long r11 = (long) r11
            r13 = 30
            long r11 = r11 << r13
            com.google.android.exoplayer2.util.ParsableBitArray r13 = r6.b
            r13.b(r4)
            com.google.android.exoplayer2.util.ParsableBitArray r13 = r6.b
            int r13 = r13.c(r14)
            int r13 = r13 << r14
            long r9 = (long) r13
            long r9 = r9 | r11
            com.google.android.exoplayer2.util.ParsableBitArray r11 = r6.b
            r11.b(r4)
            com.google.android.exoplayer2.util.ParsableBitArray r11 = r6.b
            int r11 = r11.c(r14)
            long r11 = (long) r11
            long r9 = r9 | r11
            com.google.android.exoplayer2.util.ParsableBitArray r11 = r6.b
            r11.b(r4)
            com.google.android.exoplayer2.util.TimestampAdjuster r11 = r6.e
            r11.b(r9)
            r6.h = r4
        L_0x012a:
            com.google.android.exoplayer2.util.TimestampAdjuster r9 = r6.e
            long r7 = r9.b(r7)
            r6.l = r7
        L_0x0132:
            boolean r7 = r6.k
            if (r7 == 0) goto L_0x0138
            r8 = 4
            goto L_0x0139
        L_0x0138:
            r8 = 0
        L_0x0139:
            r5 = r5 | r8
            com.google.android.exoplayer2.extractor.ts.ElementaryStreamReader r7 = r6.a
            long r8 = r6.l
            r7.a((long) r8, (int) r5)
            r7 = 3
            r6.a(r7)
            goto L_0x0051
        L_0x0147:
            com.google.android.exoplayer2.util.ParsableBitArray r7 = r6.b
            byte[] r7 = r7.a
            r8 = 9
            boolean r7 = r6.a((com.google.android.exoplayer2.util.ParsableByteArray) r1, (byte[]) r7, (int) r8)
            if (r7 == 0) goto L_0x0051
            com.google.android.exoplayer2.util.ParsableBitArray r7 = r6.b
            r9 = 0
            r7.a(r9)
            com.google.android.exoplayer2.util.ParsableBitArray r7 = r6.b
            r10 = 24
            int r7 = r7.c(r10)
            r10 = 2
            if (r7 == r4) goto L_0x0180
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r11 = 41
            r8.<init>(r11)
            java.lang.String r11 = "Unexpected start code prefix: "
            java.lang.StringBuilder r8 = r8.append(r11)
            java.lang.StringBuilder r7 = r8.append(r7)
            java.lang.String r7 = r7.toString()
            com.google.android.exoplayer2.util.Log.c(r2, r7)
            r6.j = r3
            r7 = 0
            goto L_0x01e7
        L_0x0180:
            com.google.android.exoplayer2.util.ParsableBitArray r7 = r6.b
            r11 = 8
            r7.b(r11)
            com.google.android.exoplayer2.util.ParsableBitArray r7 = r6.b
            r12 = 16
            int r7 = r7.c(r12)
            com.google.android.exoplayer2.util.ParsableBitArray r12 = r6.b
            r13 = 5
            r12.b(r13)
            com.google.android.exoplayer2.util.ParsableBitArray r12 = r6.b
            boolean r12 = r12.e()
            r6.k = r12
            com.google.android.exoplayer2.util.ParsableBitArray r12 = r6.b
            r12.b(r10)
            com.google.android.exoplayer2.util.ParsableBitArray r12 = r6.b
            boolean r12 = r12.e()
            r6.f = r12
            com.google.android.exoplayer2.util.ParsableBitArray r12 = r6.b
            boolean r12 = r12.e()
            r6.g = r12
            com.google.android.exoplayer2.util.ParsableBitArray r12 = r6.b
            r13 = 6
            r12.b(r13)
            com.google.android.exoplayer2.util.ParsableBitArray r12 = r6.b
            int r11 = r12.c(r11)
            r6.i = r11
            if (r7 != 0) goto L_0x01c5
        L_0x01c2:
            r6.j = r3
            goto L_0x01e6
        L_0x01c5:
            int r7 = r7 + 6
            int r7 = r7 - r8
            int r7 = r7 - r11
            r6.j = r7
            if (r7 >= 0) goto L_0x01e6
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r11 = 47
            r8.<init>(r11)
            java.lang.String r11 = "Found negative packet payload size: "
            java.lang.StringBuilder r8 = r8.append(r11)
            java.lang.StringBuilder r7 = r8.append(r7)
            java.lang.String r7 = r7.toString()
            com.google.android.exoplayer2.util.Log.c(r2, r7)
            goto L_0x01c2
        L_0x01e6:
            r7 = 1
        L_0x01e7:
            if (r7 == 0) goto L_0x01eb
            r8 = 2
            goto L_0x01ec
        L_0x01eb:
            r8 = 0
        L_0x01ec:
            r6.a(r8)
            goto L_0x0051
        L_0x01f1:
            int r7 = r1.a()
            r1.e(r7)
            goto L_0x0051
        L_0x01fa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.PesReader.a(com.google.android.exoplayer2.util.ParsableByteArray, int):void");
    }

    public final void a(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        this.e = timestampAdjuster;
        this.a.a(extractorOutput, trackIdGenerator);
    }
}

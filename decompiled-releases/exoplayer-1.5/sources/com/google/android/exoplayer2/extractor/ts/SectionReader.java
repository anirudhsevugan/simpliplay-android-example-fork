package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;

public final class SectionReader implements TsPayloadReader {
    private final SectionPayloadReader a;
    private final ParsableByteArray b = new ParsableByteArray(32);
    private int c;
    private int d;
    private boolean e;
    private boolean f;

    public SectionReader(SectionPayloadReader sectionPayloadReader) {
        this.a = sectionPayloadReader;
    }

    public final void a() {
        this.f = true;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f7 A[SYNTHETIC] */
    public final void a(com.google.android.exoplayer2.util.ParsableByteArray r6, int r7) {
        /*
            r5 = this;
            r0 = 1
            r7 = r7 & r0
            r1 = 0
            if (r7 == 0) goto L_0x0007
            r7 = 1
            goto L_0x0008
        L_0x0007:
            r7 = 0
        L_0x0008:
            if (r7 == 0) goto L_0x0012
            int r2 = r6.c()
            int r3 = r6.b
            int r3 = r3 + r2
            goto L_0x0013
        L_0x0012:
            r3 = 0
        L_0x0013:
            boolean r2 = r5.f
            if (r2 == 0) goto L_0x0021
            if (r7 != 0) goto L_0x001a
            return
        L_0x001a:
            r5.f = r1
            r6.d(r3)
        L_0x001f:
            r5.d = r1
        L_0x0021:
            int r7 = r6.a()
            if (r7 <= 0) goto L_0x00f7
            int r7 = r5.d
            r2 = 3
            if (r7 >= r2) goto L_0x00a7
            if (r7 != 0) goto L_0x003f
            int r7 = r6.c()
            int r3 = r6.b
            int r3 = r3 - r0
            r6.d(r3)
            r3 = 255(0xff, float:3.57E-43)
            if (r7 != r3) goto L_0x003f
            r5.f = r0
            return
        L_0x003f:
            int r7 = r6.a()
            int r3 = r5.d
            int r3 = 3 - r3
            int r7 = java.lang.Math.min(r7, r3)
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r5.b
            byte[] r3 = r3.a
            int r4 = r5.d
            r6.a(r3, r4, r7)
            int r3 = r5.d
            int r3 = r3 + r7
            r5.d = r3
            if (r3 != r2) goto L_0x0021
            com.google.android.exoplayer2.util.ParsableByteArray r7 = r5.b
            r7.d(r1)
            com.google.android.exoplayer2.util.ParsableByteArray r7 = r5.b
            r7.c(r2)
            com.google.android.exoplayer2.util.ParsableByteArray r7 = r5.b
            r7.e(r0)
            com.google.android.exoplayer2.util.ParsableByteArray r7 = r5.b
            int r7 = r7.c()
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r5.b
            int r3 = r3.c()
            r4 = r7 & 128(0x80, float:1.794E-43)
            if (r4 == 0) goto L_0x007c
            r4 = 1
            goto L_0x007d
        L_0x007c:
            r4 = 0
        L_0x007d:
            r5.e = r4
            r7 = r7 & 15
            int r7 = r7 << 8
            r7 = r7 | r3
            int r7 = r7 + r2
            r5.c = r7
            com.google.android.exoplayer2.util.ParsableByteArray r7 = r5.b
            byte[] r7 = r7.a
            int r7 = r7.length
            int r2 = r5.c
            if (r7 >= r2) goto L_0x0021
            com.google.android.exoplayer2.util.ParsableByteArray r7 = r5.b
            byte[] r7 = r7.a
            int r7 = r7.length
            int r7 = r7 << r0
            int r7 = java.lang.Math.max(r2, r7)
            r2 = 4098(0x1002, float:5.743E-42)
            int r7 = java.lang.Math.min(r2, r7)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r5.b
            r2.b(r7)
            goto L_0x0021
        L_0x00a7:
            int r7 = r6.a()
            int r2 = r5.c
            int r3 = r5.d
            int r2 = r2 - r3
            int r7 = java.lang.Math.min(r7, r2)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r5.b
            byte[] r2 = r2.a
            int r3 = r5.d
            r6.a(r2, r3, r7)
            int r2 = r5.d
            int r2 = r2 + r7
            r5.d = r2
            int r7 = r5.c
            if (r2 != r7) goto L_0x0021
            boolean r2 = r5.e
            if (r2 == 0) goto L_0x00e4
            com.google.android.exoplayer2.util.ParsableByteArray r7 = r5.b
            byte[] r7 = r7.a
            int r2 = r5.c
            r3 = -1
            int r7 = com.google.android.exoplayer2.util.Util.b((byte[]) r7, (int) r2, (int) r3)
            if (r7 == 0) goto L_0x00da
            r5.f = r0
            return
        L_0x00da:
            com.google.android.exoplayer2.util.ParsableByteArray r7 = r5.b
            int r2 = r5.c
            int r2 = r2 + -4
            r7.c(r2)
            goto L_0x00e9
        L_0x00e4:
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r5.b
            r2.c(r7)
        L_0x00e9:
            com.google.android.exoplayer2.util.ParsableByteArray r7 = r5.b
            r7.d(r1)
            com.google.android.exoplayer2.extractor.ts.SectionPayloadReader r7 = r5.a
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r5.b
            r7.a(r2)
            goto L_0x001f
        L_0x00f7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.SectionReader.a(com.google.android.exoplayer2.util.ParsableByteArray, int):void");
    }

    public final void a(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        this.a.a(timestampAdjuster, extractorOutput, trackIdGenerator);
        this.f = true;
    }
}

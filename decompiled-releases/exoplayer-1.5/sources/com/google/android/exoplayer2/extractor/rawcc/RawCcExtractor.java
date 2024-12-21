package com.google.android.exoplayer2.extractor.rawcc;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;

public final class RawCcExtractor implements Extractor {
    private final Format a;
    private final ParsableByteArray b = new ParsableByteArray(9);
    private TrackOutput c;
    private int d = 0;
    private int e;
    private long f;
    private int g;
    private int h;

    public RawCcExtractor(Format format) {
        this.a = format;
    }

    private void b(ExtractorInput extractorInput) {
        while (this.g > 0) {
            this.b.a(3);
            extractorInput.b(this.b.a, 0, 3);
            this.c.b(this.b, 3);
            this.h += 3;
            this.g--;
        }
        int i = this.h;
        if (i > 0) {
            this.c.a(this.f, 1, i, 0, (TrackOutput.CryptoData) null);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x006a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(com.google.android.exoplayer2.extractor.ExtractorInput r8, com.google.android.exoplayer2.extractor.PositionHolder r9) {
        /*
            r7 = this;
            com.google.android.exoplayer2.extractor.TrackOutput r9 = r7.c
            com.google.android.exoplayer2.util.Assertions.a((java.lang.Object) r9)
        L_0x0005:
            int r9 = r7.d
            r0 = -1
            r1 = 1
            r2 = 0
            switch(r9) {
                case 0: goto L_0x008a;
                case 1: goto L_0x0019;
                case 2: goto L_0x0013;
                default: goto L_0x000d;
            }
        L_0x000d:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            r8.<init>()
            throw r8
        L_0x0013:
            r7.b(r8)
            r7.d = r1
            return r2
        L_0x0019:
            int r9 = r7.e
            if (r9 != 0) goto L_0x003f
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r7.b
            r3 = 5
            r9.a(r3)
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r7.b
            byte[] r9 = r9.a
            boolean r9 = r8.a(r9, r2, r3, r1)
            if (r9 != 0) goto L_0x002f
        L_0x002d:
            r1 = 0
            goto L_0x0064
        L_0x002f:
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r7.b
            long r3 = r9.h()
            r5 = 1000(0x3e8, double:4.94E-321)
            long r3 = r3 * r5
            r5 = 45
            long r3 = r3 / r5
        L_0x003c:
            r7.f = r3
            goto L_0x005a
        L_0x003f:
            if (r9 != r1) goto L_0x006d
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r7.b
            r3 = 9
            r9.a(r3)
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r7.b
            byte[] r9 = r9.a
            boolean r9 = r8.a(r9, r2, r3, r1)
            if (r9 != 0) goto L_0x0053
            goto L_0x002d
        L_0x0053:
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r7.b
            long r3 = r9.l()
            goto L_0x003c
        L_0x005a:
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r7.b
            int r9 = r9.c()
            r7.g = r9
            r7.h = r2
        L_0x0064:
            if (r1 == 0) goto L_0x006a
            r9 = 2
            r7.d = r9
            goto L_0x0005
        L_0x006a:
            r7.d = r2
            return r0
        L_0x006d:
            com.google.android.exoplayer2.ParserException r8 = new com.google.android.exoplayer2.ParserException
            int r9 = r7.e
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = 39
            r0.<init>(r1)
            java.lang.String r1 = "Unsupported version number: "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r9 = r0.append(r9)
            java.lang.String r9 = r9.toString()
            r8.<init>((java.lang.String) r9)
            throw r8
        L_0x008a:
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r7.b
            r3 = 8
            r9.a(r3)
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r7.b
            byte[] r9 = r9.a
            boolean r9 = r8.a(r9, r2, r3, r1)
            if (r9 == 0) goto L_0x00b8
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r7.b
            int r9 = r9.j()
            r2 = 1380139777(0x52434301, float:2.0966069E11)
            if (r9 != r2) goto L_0x00b0
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r7.b
            int r9 = r9.c()
            r7.e = r9
            r2 = 1
            goto L_0x00b8
        L_0x00b0:
            java.io.IOException r8 = new java.io.IOException
            java.lang.String r9 = "Input not RawCC"
            r8.<init>(r9)
            throw r8
        L_0x00b8:
            if (r2 == 0) goto L_0x00be
            r7.d = r1
            goto L_0x0005
        L_0x00be:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.rawcc.RawCcExtractor.a(com.google.android.exoplayer2.extractor.ExtractorInput, com.google.android.exoplayer2.extractor.PositionHolder):int");
    }

    public final void a(long j, long j2) {
        this.d = 0;
    }

    public final void a(ExtractorOutput extractorOutput) {
        extractorOutput.a(new SeekMap.Unseekable(-9223372036854775807L));
        TrackOutput a2 = extractorOutput.a(0, 3);
        this.c = a2;
        a2.a(this.a);
        extractorOutput.c_();
    }

    public final boolean a(ExtractorInput extractorInput) {
        this.b.a(8);
        extractorInput.d(this.b.a, 0, 8);
        return this.b.j() == 1380139777;
    }
}

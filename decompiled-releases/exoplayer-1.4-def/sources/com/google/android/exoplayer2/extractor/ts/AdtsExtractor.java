package com.google.android.exoplayer2.extractor.ts;

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

public final class AdtsExtractor implements Extractor {
    private final AdtsReader a;
    private final ParsableByteArray b;
    private final ParsableByteArray c;
    private final ParsableBitArray d;
    private ExtractorOutput e;
    private long f;
    private long g;
    private boolean h;
    private boolean i;

    static {
        ExtractorsFactory extractorsFactory = AdtsExtractor$$Lambda$0.b;
    }

    public AdtsExtractor() {
        this((byte) 0);
    }

    public AdtsExtractor(byte b2) {
        this.a = new AdtsReader();
        this.b = new ParsableByteArray(2048);
        this.g = -1;
        ParsableByteArray parsableByteArray = new ParsableByteArray(10);
        this.c = parsableByteArray;
        this.d = new ParsableBitArray(parsableByteArray.a);
    }

    static final /* synthetic */ Extractor[] a() {
        return new Extractor[]{new AdtsExtractor()};
    }

    public final int a(ExtractorInput extractorInput, PositionHolder positionHolder) {
        Assertions.a((Object) this.e);
        extractorInput.d();
        int a2 = extractorInput.a(this.b.a, 0, 2048);
        boolean z = a2 == -1;
        if (!this.i) {
            this.e.a(new SeekMap.Unseekable(-9223372036854775807L));
            this.i = true;
        }
        if (z) {
            return -1;
        }
        this.b.d(0);
        this.b.c(a2);
        if (!this.h) {
            this.a.a = this.f;
            this.h = true;
        }
        this.a.a(this.b);
        return 0;
    }

    public final void a(long j, long j2) {
        this.h = false;
        this.a.c();
        this.f = j2;
    }

    public final void a(ExtractorOutput extractorOutput) {
        this.e = extractorOutput;
        this.a.a(extractorOutput, new TsPayloadReader.TrackIdGenerator(0, 1));
        extractorOutput.c_();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x005b, code lost:
        r9.a();
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0064, code lost:
        if ((r3 - r1) < 8192) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0066, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(com.google.android.exoplayer2.extractor.ExtractorInput r9) {
        /*
            r8 = this;
            r0 = 0
            r1 = 0
        L_0x0002:
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r8.c
            byte[] r2 = r2.a
            r3 = 10
            r9.d(r2, r0, r3)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r8.c
            r2.d(r0)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r8.c
            int r2 = r2.g()
            r3 = 4801587(0x494433, float:6.728456E-39)
            if (r2 != r3) goto L_0x002e
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r8.c
            r3 = 3
            r2.e(r3)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r8.c
            int r2 = r2.n()
            int r3 = r2 + 10
            int r1 = r1 + r3
            r9.c(r2)
            goto L_0x0002
        L_0x002e:
            r9.a()
            r9.c(r1)
            long r2 = r8.g
            r4 = -1
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x003f
            long r2 = (long) r1
            r8.g = r2
        L_0x003f:
            r3 = r1
        L_0x0040:
            r2 = 0
            r4 = 0
        L_0x0042:
            com.google.android.exoplayer2.util.ParsableByteArray r5 = r8.c
            byte[] r5 = r5.a
            r6 = 2
            r9.d(r5, r0, r6)
            com.google.android.exoplayer2.util.ParsableByteArray r5 = r8.c
            r5.d(r0)
            com.google.android.exoplayer2.util.ParsableByteArray r5 = r8.c
            int r5 = r5.d()
            boolean r5 = com.google.android.exoplayer2.extractor.ts.AdtsReader.a((int) r5)
            if (r5 != 0) goto L_0x006b
            r9.a()
            int r3 = r3 + 1
            int r2 = r3 - r1
            r4 = 8192(0x2000, float:1.14794E-41)
            if (r2 < r4) goto L_0x0067
            return r0
        L_0x0067:
            r9.c(r3)
            goto L_0x0040
        L_0x006b:
            r5 = 1
            int r2 = r2 + r5
            r6 = 4
            if (r2 < r6) goto L_0x0075
            r7 = 188(0xbc, float:2.63E-43)
            if (r4 <= r7) goto L_0x0075
            return r5
        L_0x0075:
            com.google.android.exoplayer2.util.ParsableByteArray r5 = r8.c
            byte[] r5 = r5.a
            r9.d(r5, r0, r6)
            com.google.android.exoplayer2.util.ParsableBitArray r5 = r8.d
            r6 = 14
            r5.a(r6)
            com.google.android.exoplayer2.util.ParsableBitArray r5 = r8.d
            r6 = 13
            int r5 = r5.c(r6)
            r6 = 6
            if (r5 > r6) goto L_0x008f
            return r0
        L_0x008f:
            int r6 = r5 + -6
            r9.c(r6)
            int r4 = r4 + r5
            goto L_0x0042
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.AdtsExtractor.a(com.google.android.exoplayer2.extractor.ExtractorInput):boolean");
    }
}

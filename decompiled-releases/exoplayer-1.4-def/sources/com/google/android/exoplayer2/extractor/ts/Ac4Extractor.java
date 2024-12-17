package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.ParsableByteArray;

public final class Ac4Extractor implements Extractor {
    private final Ac4Reader a = new Ac4Reader();
    private final ParsableByteArray b = new ParsableByteArray(16384);
    private boolean c;

    static {
        ExtractorsFactory extractorsFactory = Ac4Extractor$$Lambda$0.b;
    }

    static final /* synthetic */ Extractor[] a() {
        return new Extractor[]{new Ac4Extractor()};
    }

    public final int a(ExtractorInput extractorInput, PositionHolder positionHolder) {
        int a2 = extractorInput.a(this.b.a, 0, 16384);
        if (a2 == -1) {
            return -1;
        }
        this.b.d(0);
        this.b.c(a2);
        if (!this.c) {
            this.a.a = 0;
            this.c = true;
        }
        this.a.a(this.b);
        return 0;
    }

    public final void a(long j, long j2) {
        this.c = false;
        this.a.a();
    }

    public final void a(ExtractorOutput extractorOutput) {
        this.a.a(extractorOutput, new TsPayloadReader.TrackIdGenerator(0, 1));
        extractorOutput.c_();
        extractorOutput.a(new SeekMap.Unseekable(-9223372036854775807L));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0048, code lost:
        r9.a();
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0051, code lost:
        if ((r4 - r3) < 8192) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0053, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(com.google.android.exoplayer2.extractor.ExtractorInput r9) {
        /*
            r8 = this;
            com.google.android.exoplayer2.util.ParsableByteArray r0 = new com.google.android.exoplayer2.util.ParsableByteArray
            r1 = 10
            r0.<init>((int) r1)
            r2 = 0
            r3 = 0
        L_0x0009:
            byte[] r4 = r0.a
            r9.d(r4, r2, r1)
            r0.d(r2)
            int r4 = r0.g()
            r5 = 4801587(0x494433, float:6.728456E-39)
            if (r4 != r5) goto L_0x0029
            r4 = 3
            r0.e(r4)
            int r4 = r0.n()
            int r5 = r4 + 10
            int r3 = r3 + r5
            r9.c(r4)
            goto L_0x0009
        L_0x0029:
            r9.a()
            r9.c(r3)
            r4 = r3
        L_0x0030:
            r1 = 0
        L_0x0031:
            byte[] r5 = r0.a
            r6 = 7
            r9.d(r5, r2, r6)
            r0.d(r2)
            int r5 = r0.d()
            r6 = 44096(0xac40, float:6.1792E-41)
            if (r5 == r6) goto L_0x0058
            r6 = 44097(0xac41, float:6.1793E-41)
            if (r5 == r6) goto L_0x0058
            r9.a()
            int r4 = r4 + 1
            int r1 = r4 - r3
            r5 = 8192(0x2000, float:1.14794E-41)
            if (r1 < r5) goto L_0x0054
            return r2
        L_0x0054:
            r9.c(r4)
            goto L_0x0030
        L_0x0058:
            r6 = 1
            int r1 = r1 + r6
            r7 = 4
            if (r1 < r7) goto L_0x005e
            return r6
        L_0x005e:
            byte[] r6 = r0.a
            int r5 = com.google.android.exoplayer2.audio.Ac4Util.a((byte[]) r6, (int) r5)
            r6 = -1
            if (r5 != r6) goto L_0x0068
            return r2
        L_0x0068:
            int r5 = r5 + -7
            r9.c(r5)
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.Ac4Extractor.a(com.google.android.exoplayer2.extractor.ExtractorInput):boolean");
    }
}

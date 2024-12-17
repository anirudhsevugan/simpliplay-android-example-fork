package com.google.android.exoplayer2.extractor.flv;

import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.util.ParsableByteArray;

public final class FlvExtractor implements Extractor {
    private final ParsableByteArray a = new ParsableByteArray(4);
    private final ParsableByteArray b = new ParsableByteArray(9);
    private final ParsableByteArray c = new ParsableByteArray(11);
    private final ParsableByteArray d = new ParsableByteArray();
    private final ScriptTagPayloadReader e = new ScriptTagPayloadReader();
    private ExtractorOutput f;
    private int g = 1;
    private boolean h;
    private long i;
    private int j;
    private int k;
    private int l;
    private long m;
    private boolean n;
    private AudioTagPayloadReader o;
    private VideoTagPayloadReader p;

    static {
        ExtractorsFactory extractorsFactory = FlvExtractor$$Lambda$0.b;
    }

    static final /* synthetic */ Extractor[] a() {
        return new Extractor[]{new FlvExtractor()};
    }

    private ParsableByteArray b(ExtractorInput extractorInput) {
        if (this.l > this.d.a.length) {
            ParsableByteArray parsableByteArray = this.d;
            parsableByteArray.a(new byte[Math.max(parsableByteArray.a.length << 1, this.l)], 0);
        } else {
            this.d.d(0);
        }
        this.d.c(this.l);
        extractorInput.b(this.d.a, 0, this.l);
        return this.d;
    }

    private void b() {
        if (!this.n) {
            this.f.a(new SeekMap.Unseekable(-9223372036854775807L));
            this.n = true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00b8 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0008 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(com.google.android.exoplayer2.extractor.ExtractorInput r16, com.google.android.exoplayer2.extractor.PositionHolder r17) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            com.google.android.exoplayer2.extractor.ExtractorOutput r2 = r0.f
            com.google.android.exoplayer2.util.Assertions.a((java.lang.Object) r2)
        L_0x0008:
            int r2 = r0.g
            r3 = -1
            r4 = 8
            r5 = 3
            r6 = 2
            r7 = 9
            r8 = 4
            r9 = 1
            r10 = 0
            switch(r2) {
                case 1: goto L_0x010c;
                case 2: goto L_0x0101;
                case 3: goto L_0x00b9;
                case 4: goto L_0x001d;
                default: goto L_0x0017;
            }
        L_0x0017:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x001d:
            boolean r2 = r0.h
            r13 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r2 == 0) goto L_0x002c
            long r2 = r0.i
            long r11 = r0.m
            long r2 = r2 + r11
            goto L_0x0039
        L_0x002c:
            com.google.android.exoplayer2.extractor.flv.ScriptTagPayloadReader r2 = r0.e
            long r2 = r2.a
            int r5 = (r2 > r13 ? 1 : (r2 == r13 ? 0 : -1))
            if (r5 != 0) goto L_0x0037
            r2 = 0
            goto L_0x0039
        L_0x0037:
            long r2 = r0.m
        L_0x0039:
            int r5 = r0.k
            if (r5 != r4) goto L_0x0050
            com.google.android.exoplayer2.extractor.flv.AudioTagPayloadReader r4 = r0.o
            if (r4 == 0) goto L_0x0050
            r15.b()
            com.google.android.exoplayer2.extractor.flv.AudioTagPayloadReader r4 = r0.o
            com.google.android.exoplayer2.util.ParsableByteArray r5 = r15.b(r16)
            boolean r2 = r4.b(r5, r2)
        L_0x004e:
            r3 = 1
            goto L_0x009a
        L_0x0050:
            if (r5 != r7) goto L_0x0064
            com.google.android.exoplayer2.extractor.flv.VideoTagPayloadReader r4 = r0.p
            if (r4 == 0) goto L_0x0064
            r15.b()
            com.google.android.exoplayer2.extractor.flv.VideoTagPayloadReader r4 = r0.p
            com.google.android.exoplayer2.util.ParsableByteArray r5 = r15.b(r16)
            boolean r2 = r4.b(r5, r2)
            goto L_0x004e
        L_0x0064:
            r4 = 18
            if (r5 != r4) goto L_0x0093
            boolean r4 = r0.n
            if (r4 != 0) goto L_0x0093
            com.google.android.exoplayer2.extractor.flv.ScriptTagPayloadReader r4 = r0.e
            com.google.android.exoplayer2.util.ParsableByteArray r5 = r15.b(r16)
            boolean r2 = r4.b(r5, r2)
            com.google.android.exoplayer2.extractor.flv.ScriptTagPayloadReader r3 = r0.e
            long r3 = r3.a
            int r5 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r5 == 0) goto L_0x004e
            com.google.android.exoplayer2.extractor.ExtractorOutput r5 = r0.f
            com.google.android.exoplayer2.extractor.IndexSeekMap r7 = new com.google.android.exoplayer2.extractor.IndexSeekMap
            com.google.android.exoplayer2.extractor.flv.ScriptTagPayloadReader r11 = r0.e
            long[] r11 = r11.c
            com.google.android.exoplayer2.extractor.flv.ScriptTagPayloadReader r12 = r0.e
            long[] r12 = r12.b
            r7.<init>(r11, r12, r3)
            r5.a(r7)
            r0.n = r9
            goto L_0x004e
        L_0x0093:
            int r2 = r0.l
            r1.b(r2)
            r2 = 0
            r3 = 0
        L_0x009a:
            boolean r4 = r0.h
            if (r4 != 0) goto L_0x00b2
            if (r2 == 0) goto L_0x00b2
            r0.h = r9
            com.google.android.exoplayer2.extractor.flv.ScriptTagPayloadReader r2 = r0.e
            long r4 = r2.a
            int r2 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r2 != 0) goto L_0x00ae
            long r4 = r0.m
            long r11 = -r4
            goto L_0x00b0
        L_0x00ae:
            r11 = 0
        L_0x00b0:
            r0.i = r11
        L_0x00b2:
            r0.j = r8
            r0.g = r6
            if (r3 == 0) goto L_0x0008
            return r10
        L_0x00b9:
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.c
            byte[] r2 = r2.a
            r4 = 11
            boolean r2 = r1.a(r2, r10, r4, r9)
            if (r2 != 0) goto L_0x00c7
            r9 = 0
            goto L_0x00fe
        L_0x00c7:
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.c
            r2.d(r10)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.c
            int r2 = r2.c()
            r0.k = r2
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.c
            int r2 = r2.g()
            r0.l = r2
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.c
            int r2 = r2.g()
            long r6 = (long) r2
            r0.m = r6
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.c
            int r2 = r2.c()
            int r2 = r2 << 24
            long r6 = (long) r2
            long r10 = r0.m
            long r6 = r6 | r10
            r10 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 * r10
            r0.m = r6
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.c
            r2.e(r5)
            r0.g = r8
        L_0x00fe:
            if (r9 != 0) goto L_0x0008
            return r3
        L_0x0101:
            int r2 = r0.j
            r1.b(r2)
            r0.j = r10
            r0.g = r5
            goto L_0x0008
        L_0x010c:
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.b
            byte[] r2 = r2.a
            boolean r2 = r1.a(r2, r10, r7, r9)
            if (r2 != 0) goto L_0x0118
            r9 = 0
            goto L_0x016b
        L_0x0118:
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.b
            r2.d(r10)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.b
            r2.e(r8)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.b
            int r2 = r2.c()
            r5 = r2 & 4
            if (r5 == 0) goto L_0x012e
            r5 = 1
            goto L_0x012f
        L_0x012e:
            r5 = 0
        L_0x012f:
            r2 = r2 & 1
            if (r2 == 0) goto L_0x0134
            r10 = 1
        L_0x0134:
            if (r5 == 0) goto L_0x0147
            com.google.android.exoplayer2.extractor.flv.AudioTagPayloadReader r2 = r0.o
            if (r2 != 0) goto L_0x0147
            com.google.android.exoplayer2.extractor.flv.AudioTagPayloadReader r2 = new com.google.android.exoplayer2.extractor.flv.AudioTagPayloadReader
            com.google.android.exoplayer2.extractor.ExtractorOutput r5 = r0.f
            com.google.android.exoplayer2.extractor.TrackOutput r4 = r5.a(r4, r9)
            r2.<init>(r4)
            r0.o = r2
        L_0x0147:
            if (r10 == 0) goto L_0x015a
            com.google.android.exoplayer2.extractor.flv.VideoTagPayloadReader r2 = r0.p
            if (r2 != 0) goto L_0x015a
            com.google.android.exoplayer2.extractor.flv.VideoTagPayloadReader r2 = new com.google.android.exoplayer2.extractor.flv.VideoTagPayloadReader
            com.google.android.exoplayer2.extractor.ExtractorOutput r4 = r0.f
            com.google.android.exoplayer2.extractor.TrackOutput r4 = r4.a(r7, r6)
            r2.<init>(r4)
            r0.p = r2
        L_0x015a:
            com.google.android.exoplayer2.extractor.ExtractorOutput r2 = r0.f
            r2.c_()
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.b
            int r2 = r2.j()
            int r2 = r2 - r7
            int r2 = r2 + r8
            r0.j = r2
            r0.g = r6
        L_0x016b:
            if (r9 != 0) goto L_0x0008
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.flv.FlvExtractor.a(com.google.android.exoplayer2.extractor.ExtractorInput, com.google.android.exoplayer2.extractor.PositionHolder):int");
    }

    public final void a(long j2, long j3) {
        if (j2 == 0) {
            this.g = 1;
            this.h = false;
        } else {
            this.g = 3;
        }
        this.j = 0;
    }

    public final void a(ExtractorOutput extractorOutput) {
        this.f = extractorOutput;
    }

    public final boolean a(ExtractorInput extractorInput) {
        extractorInput.d(this.a.a, 0, 3);
        this.a.d(0);
        if (this.a.g() != 4607062) {
            return false;
        }
        extractorInput.d(this.a.a, 0, 2);
        this.a.d(0);
        if ((this.a.d() & 250) != 0) {
            return false;
        }
        extractorInput.d(this.a.a, 0, 4);
        this.a.d(0);
        int j2 = this.a.j();
        extractorInput.a();
        extractorInput.c(j2);
        extractorInput.d(this.a.a, 0, 4);
        this.a.d(0);
        return this.a.j() == 0;
    }
}

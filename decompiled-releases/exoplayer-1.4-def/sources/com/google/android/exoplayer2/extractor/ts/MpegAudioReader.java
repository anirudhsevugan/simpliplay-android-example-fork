package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.audio.MpegAudioUtil;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.ParsableByteArray;

public final class MpegAudioReader implements ElementaryStreamReader {
    private final ParsableByteArray a;
    private final MpegAudioUtil.Header b;
    private final String c;
    private TrackOutput d;
    private String e;
    private int f;
    private int g;
    private boolean h;
    private boolean i;
    private long j;
    private int k;
    private long l;

    public MpegAudioReader() {
        this((String) null);
    }

    public MpegAudioReader(String str) {
        this.f = 0;
        ParsableByteArray parsableByteArray = new ParsableByteArray(4);
        this.a = parsableByteArray;
        parsableByteArray.a[0] = -1;
        this.b = new MpegAudioUtil.Header();
        this.c = str;
    }

    public final void a() {
        this.f = 0;
        this.g = 0;
        this.i = false;
    }

    public final void a(long j2, int i2) {
        this.l = j2;
    }

    public final void a(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.a();
        this.e = trackIdGenerator.c();
        this.d = extractorOutput.a(trackIdGenerator.b(), 1);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x007c, code lost:
        r11.f = 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.android.exoplayer2.util.ParsableByteArray r12) {
        /*
            r11 = this;
            com.google.android.exoplayer2.extractor.TrackOutput r0 = r11.d
            com.google.android.exoplayer2.util.Assertions.a((java.lang.Object) r0)
        L_0x0005:
            int r0 = r12.a()
            if (r0 <= 0) goto L_0x0118
            int r0 = r11.f
            r1 = 2
            r2 = 1
            r3 = 0
            switch(r0) {
                case 0: goto L_0x00d9;
                case 1: goto L_0x004a;
                case 2: goto L_0x0019;
                default: goto L_0x0013;
            }
        L_0x0013:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            r12.<init>()
            throw r12
        L_0x0019:
            int r0 = r12.a()
            int r1 = r11.k
            int r2 = r11.g
            int r1 = r1 - r2
            int r0 = java.lang.Math.min(r0, r1)
            com.google.android.exoplayer2.extractor.TrackOutput r1 = r11.d
            r1.b(r12, r0)
            int r1 = r11.g
            int r1 = r1 + r0
            r11.g = r1
            int r8 = r11.k
            if (r1 < r8) goto L_0x0005
            com.google.android.exoplayer2.extractor.TrackOutput r4 = r11.d
            long r5 = r11.l
            r7 = 1
            r9 = 0
            r10 = 0
            r4.a(r5, r7, r8, r9, r10)
            long r0 = r11.l
            long r4 = r11.j
            long r0 = r0 + r4
            r11.l = r0
            r11.g = r3
            r11.f = r3
            goto L_0x0005
        L_0x004a:
            int r0 = r12.a()
            int r4 = r11.g
            r5 = 4
            int r4 = 4 - r4
            int r0 = java.lang.Math.min(r0, r4)
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r11.a
            byte[] r4 = r4.a
            int r6 = r11.g
            r12.a(r4, r6, r0)
            int r4 = r11.g
            int r4 = r4 + r0
            r11.g = r4
            if (r4 < r5) goto L_0x0005
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r11.a
            r0.d(r3)
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r0 = r11.b
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r11.a
            int r4 = r4.j()
            boolean r0 = r0.a(r4)
            if (r0 != 0) goto L_0x007f
            r11.g = r3
        L_0x007c:
            r11.f = r2
            goto L_0x0005
        L_0x007f:
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r0 = r11.b
            int r0 = r0.c
            r11.k = r0
            boolean r0 = r11.h
            if (r0 != 0) goto L_0x00c9
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r0 = r11.b
            int r0 = r0.g
            long r6 = (long) r0
            r8 = 1000000(0xf4240, double:4.940656E-318)
            long r6 = r6 * r8
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r0 = r11.b
            int r0 = r0.d
            long r8 = (long) r0
            long r6 = r6 / r8
            r11.j = r6
            com.google.android.exoplayer2.Format$Builder r0 = new com.google.android.exoplayer2.Format$Builder
            r0.<init>()
            java.lang.String r4 = r11.e
            r0.a = r4
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r4 = r11.b
            java.lang.String r4 = r4.b
            r0.k = r4
            r4 = 4096(0x1000, float:5.74E-42)
            r0.l = r4
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r4 = r11.b
            int r4 = r4.e
            r0.x = r4
            com.google.android.exoplayer2.audio.MpegAudioUtil$Header r4 = r11.b
            int r4 = r4.d
            r0.y = r4
            java.lang.String r4 = r11.c
            r0.c = r4
            com.google.android.exoplayer2.Format r0 = r0.a()
            com.google.android.exoplayer2.extractor.TrackOutput r4 = r11.d
            r4.a(r0)
            r11.h = r2
        L_0x00c9:
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r11.a
            r0.d(r3)
            com.google.android.exoplayer2.extractor.TrackOutput r0 = r11.d
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r11.a
            r0.b(r2, r5)
            r11.f = r1
            goto L_0x0005
        L_0x00d9:
            byte[] r0 = r12.a
            int r4 = r12.b
            int r5 = r12.c
        L_0x00df:
            if (r4 >= r5) goto L_0x0113
            byte r6 = r0[r4]
            r7 = r6 & 255(0xff, float:3.57E-43)
            r8 = 255(0xff, float:3.57E-43)
            if (r7 != r8) goto L_0x00eb
            r7 = 1
            goto L_0x00ec
        L_0x00eb:
            r7 = 0
        L_0x00ec:
            boolean r8 = r11.i
            if (r8 == 0) goto L_0x00f8
            r6 = r6 & 224(0xe0, float:3.14E-43)
            r8 = 224(0xe0, float:3.14E-43)
            if (r6 != r8) goto L_0x00f8
            r6 = 1
            goto L_0x00f9
        L_0x00f8:
            r6 = 0
        L_0x00f9:
            r11.i = r7
            if (r6 == 0) goto L_0x0110
            int r5 = r4 + 1
            r12.d(r5)
            r11.i = r3
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r11.a
            byte[] r3 = r3.a
            byte r0 = r0[r4]
            r3[r2] = r0
            r11.g = r1
            goto L_0x007c
        L_0x0110:
            int r4 = r4 + 1
            goto L_0x00df
        L_0x0113:
            r12.d(r5)
            goto L_0x0005
        L_0x0118:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ts.MpegAudioReader.a(com.google.android.exoplayer2.util.ParsableByteArray):void");
    }

    public final void b() {
    }
}

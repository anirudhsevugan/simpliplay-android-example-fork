package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

final class DefaultOggSeeker implements OggSeeker {
    private final OggPageHeader a;
    /* access modifiers changed from: private */
    public final long b;
    /* access modifiers changed from: private */
    public final long c;
    /* access modifiers changed from: private */
    public final StreamReader d;
    private int e;
    /* access modifiers changed from: private */
    public long f;
    private long g;
    private long h;
    private long i;
    private long j;
    private long k;
    private long l;

    final class OggSeekMap implements SeekMap {
        private OggSeekMap() {
        }

        /* synthetic */ OggSeekMap(DefaultOggSeeker defaultOggSeeker, byte b) {
            this();
        }

        public final SeekMap.SeekPoints a(long j) {
            return new SeekMap.SeekPoints(new SeekPoint(j, Util.a((DefaultOggSeeker.this.b + ((DefaultOggSeeker.this.d.b(j) * (DefaultOggSeeker.this.c - DefaultOggSeeker.this.b)) / DefaultOggSeeker.this.f)) - 30000, DefaultOggSeeker.this.b, DefaultOggSeeker.this.c - 1)));
        }

        public final boolean a() {
            return true;
        }

        public final long b() {
            return DefaultOggSeeker.this.d.a(DefaultOggSeeker.this.f);
        }
    }

    public DefaultOggSeeker(StreamReader streamReader, long j2, long j3, long j4, long j5, boolean z) {
        Assertions.a(j2 >= 0 && j3 > j2);
        this.d = streamReader;
        this.b = j2;
        this.c = j3;
        if (j4 == j3 - j2 || z) {
            this.f = j5;
            this.e = 4;
        } else {
            this.e = 0;
        }
        this.a = new OggPageHeader();
    }

    private void b(ExtractorInput extractorInput) {
        while (true) {
            this.a.a(extractorInput);
            this.a.a(extractorInput, false);
            if (this.a.b <= this.h) {
                extractorInput.b(this.a.d + this.a.e);
                this.i = extractorInput.c();
                this.k = this.a.b;
            } else {
                extractorInput.a();
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c7, code lost:
        b(r23);
        r0.e = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00d3, code lost:
        return -(r0.k + 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00e8, code lost:
        r0.a.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00f3, code lost:
        if (r0.a.a(r1) == false) goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00f5, code lost:
        r0.a.a(r1, false);
        r1.b(r0.a.d + r0.a.e);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x010c, code lost:
        if ((r0.a.a & 4) == 4) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0114, code lost:
        if (r0.a.a(r1) == false) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x011e, code lost:
        if (r23.c() < r0.c) goto L_0x00f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0120, code lost:
        r0.f = r0.a.b;
        r0.e = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x012b, code lost:
        return r0.g;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0132, code lost:
        throw new java.io.EOFException();
     */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00c3 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long a(com.google.android.exoplayer2.extractor.ExtractorInput r23) {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            int r2 = r0.e
            r3 = 0
            r6 = -1
            switch(r2) {
                case 0: goto L_0x00d4;
                case 1: goto L_0x00e8;
                case 2: goto L_0x0013;
                case 3: goto L_0x00c7;
                case 4: goto L_0x0012;
                default: goto L_0x000c;
            }
        L_0x000c:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x0012:
            return r6
        L_0x0013:
            long r9 = r0.i
            long r11 = r0.j
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 != 0) goto L_0x001f
        L_0x001b:
            r2 = r6
            r4 = r2
            goto L_0x00bf
        L_0x001f:
            long r9 = r23.c()
            com.google.android.exoplayer2.extractor.ogg.OggPageHeader r2 = r0.a
            long r11 = r0.j
            boolean r2 = r2.a((com.google.android.exoplayer2.extractor.ExtractorInput) r1, (long) r11)
            if (r2 != 0) goto L_0x003e
            long r2 = r0.i
            int r11 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r11 == 0) goto L_0x0036
            r4 = r6
            goto L_0x00bf
        L_0x0036:
            java.io.IOException r1 = new java.io.IOException
            java.lang.String r2 = "No ogg page can be found."
            r1.<init>(r2)
            throw r1
        L_0x003e:
            com.google.android.exoplayer2.extractor.ogg.OggPageHeader r2 = r0.a
            r2.a((com.google.android.exoplayer2.extractor.ExtractorInput) r1, (boolean) r3)
            r23.a()
            long r2 = r0.h
            com.google.android.exoplayer2.extractor.ogg.OggPageHeader r11 = r0.a
            long r11 = r11.b
            long r2 = r2 - r11
            com.google.android.exoplayer2.extractor.ogg.OggPageHeader r11 = r0.a
            int r11 = r11.d
            com.google.android.exoplayer2.extractor.ogg.OggPageHeader r12 = r0.a
            int r12 = r12.e
            int r11 = r11 + r12
            r12 = 0
            int r14 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r14 > 0) goto L_0x0064
            r14 = 72000(0x11940, double:3.55727E-319)
            int r16 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r16 >= 0) goto L_0x0064
            goto L_0x001b
        L_0x0064:
            int r14 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r14 >= 0) goto L_0x0071
            r0.j = r9
            com.google.android.exoplayer2.extractor.ogg.OggPageHeader r9 = r0.a
            long r9 = r9.b
            r0.l = r9
            goto L_0x007f
        L_0x0071:
            long r9 = r23.c()
            long r14 = (long) r11
            long r9 = r9 + r14
            r0.i = r9
            com.google.android.exoplayer2.extractor.ogg.OggPageHeader r9 = r0.a
            long r9 = r9.b
            r0.k = r9
        L_0x007f:
            long r9 = r0.j
            long r14 = r0.i
            long r9 = r9 - r14
            r16 = 100000(0x186a0, double:4.94066E-319)
            int r18 = (r9 > r16 ? 1 : (r9 == r16 ? 0 : -1))
            if (r18 >= 0) goto L_0x0090
            r0.j = r14
            r4 = r6
            r2 = r14
            goto L_0x00bf
        L_0x0090:
            long r9 = (long) r11
            r14 = 1
            int r11 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r11 > 0) goto L_0x009a
            r11 = 2
            goto L_0x009b
        L_0x009a:
            r11 = r14
        L_0x009b:
            long r9 = r9 * r11
            long r11 = r23.c()
            long r11 = r11 - r9
            long r9 = r0.j
            long r4 = r0.i
            long r16 = r9 - r4
            long r2 = r2 * r16
            long r6 = r0.l
            r16 = r9
            long r8 = r0.k
            long r6 = r6 - r8
            long r2 = r2 / r6
            long r2 = r2 + r11
            long r20 = r16 - r14
            r16 = r2
            r18 = r4
            long r2 = com.google.android.exoplayer2.util.Util.a((long) r16, (long) r18, (long) r20)
            r4 = -1
        L_0x00bf:
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L_0x00c4
            return r2
        L_0x00c4:
            r2 = 3
            r0.e = r2
        L_0x00c7:
            r22.b((com.google.android.exoplayer2.extractor.ExtractorInput) r23)
            r1 = 4
            r0.e = r1
            long r1 = r0.k
            r3 = 2
            long r1 = r1 + r3
            long r1 = -r1
            return r1
        L_0x00d4:
            long r4 = r23.c()
            r0.g = r4
            r2 = 1
            r0.e = r2
            long r6 = r0.c
            r8 = 65307(0xff1b, double:3.2266E-319)
            long r6 = r6 - r8
            int r2 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x00e8
            return r6
        L_0x00e8:
            com.google.android.exoplayer2.extractor.ogg.OggPageHeader r2 = r0.a
            r2.a()
            com.google.android.exoplayer2.extractor.ogg.OggPageHeader r2 = r0.a
            boolean r2 = r2.a(r1)
            if (r2 == 0) goto L_0x012c
        L_0x00f5:
            com.google.android.exoplayer2.extractor.ogg.OggPageHeader r2 = r0.a
            r2.a((com.google.android.exoplayer2.extractor.ExtractorInput) r1, (boolean) r3)
            com.google.android.exoplayer2.extractor.ogg.OggPageHeader r2 = r0.a
            int r2 = r2.d
            com.google.android.exoplayer2.extractor.ogg.OggPageHeader r4 = r0.a
            int r4 = r4.e
            int r2 = r2 + r4
            r1.b(r2)
            com.google.android.exoplayer2.extractor.ogg.OggPageHeader r2 = r0.a
            int r2 = r2.a
            r4 = 4
            r2 = r2 & r4
            if (r2 == r4) goto L_0x0120
            com.google.android.exoplayer2.extractor.ogg.OggPageHeader r2 = r0.a
            boolean r2 = r2.a(r1)
            if (r2 == 0) goto L_0x0120
            long r4 = r23.c()
            long r6 = r0.c
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 < 0) goto L_0x00f5
        L_0x0120:
            com.google.android.exoplayer2.extractor.ogg.OggPageHeader r1 = r0.a
            long r1 = r1.b
            r0.f = r1
            r1 = 4
            r0.e = r1
            long r1 = r0.g
            return r1
        L_0x012c:
            java.io.EOFException r1 = new java.io.EOFException
            r1.<init>()
            goto L_0x0133
        L_0x0132:
            throw r1
        L_0x0133:
            goto L_0x0132
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.ogg.DefaultOggSeeker.a(com.google.android.exoplayer2.extractor.ExtractorInput):long");
    }

    public final /* synthetic */ SeekMap a() {
        if (this.f != 0) {
            return new OggSeekMap(this, (byte) 0);
        }
        return null;
    }

    public final void a(long j2) {
        this.h = Util.a(j2, 0, this.f - 1);
        this.e = 2;
        this.i = this.b;
        this.j = this.c;
        this.k = 0;
        this.l = this.f;
    }
}

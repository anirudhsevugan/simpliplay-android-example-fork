package com.google.android.exoplayer2.source;

import androidx.core.location.LocationRequestCompat;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

public final class ClippingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    public final MediaPeriod a;
    long b;
    long c;
    private MediaPeriod.Callback d;
    private ClippingSampleStream[] e = new ClippingSampleStream[0];
    private long f;

    final class ClippingSampleStream implements SampleStream {
        public final SampleStream a;
        boolean b;

        public ClippingSampleStream(SampleStream sampleStream) {
            this.a = sampleStream;
        }

        public final int a(long j) {
            if (ClippingMediaPeriod.this.g()) {
                return -3;
            }
            return this.a.a(j);
        }

        public final int a(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, int i) {
            if (ClippingMediaPeriod.this.g()) {
                return -3;
            }
            if (this.b) {
                decoderInputBuffer.a = 4;
                return -4;
            }
            int a2 = this.a.a(formatHolder, decoderInputBuffer, i);
            if (a2 == -5) {
                Format format = (Format) Assertions.b((Object) formatHolder.b);
                if (!(format.z == 0 && format.A == 0)) {
                    int i2 = 0;
                    int i3 = ClippingMediaPeriod.this.b != 0 ? 0 : format.z;
                    if (ClippingMediaPeriod.this.c == Long.MIN_VALUE) {
                        i2 = format.A;
                    }
                    Format.Builder a3 = format.a();
                    a3.A = i3;
                    a3.B = i2;
                    formatHolder.b = a3.a();
                }
                return -5;
            } else if (ClippingMediaPeriod.this.c == Long.MIN_VALUE || ((a2 != -4 || decoderInputBuffer.e < ClippingMediaPeriod.this.c) && (a2 != -3 || ClippingMediaPeriod.this.d() != Long.MIN_VALUE || decoderInputBuffer.d))) {
                return a2;
            } else {
                decoderInputBuffer.a();
                decoderInputBuffer.a = 4;
                this.b = true;
                return -4;
            }
        }

        public final boolean a() {
            return !ClippingMediaPeriod.this.g() && this.a.a();
        }

        public final void b() {
            this.a.b();
        }
    }

    public ClippingMediaPeriod(MediaPeriod mediaPeriod, boolean z, long j, long j2) {
        this.a = mediaPeriod;
        this.f = z ? j : -9223372036854775807L;
        this.b = j;
        this.c = j2;
    }

    public final long a(long j, SeekParameters seekParameters) {
        long j2 = this.b;
        if (j == j2) {
            return j2;
        }
        long a2 = Util.a(seekParameters.b, 0, j - this.b);
        long j3 = seekParameters.c;
        long j4 = this.c;
        long a3 = Util.a(j3, 0, j4 == Long.MIN_VALUE ? LocationRequestCompat.PASSIVE_INTERVAL : j4 - j);
        if (!(a2 == seekParameters.b && a3 == seekParameters.c)) {
            seekParameters = new SeekParameters(a2, a3);
        }
        return this.a.a(j, seekParameters);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0081, code lost:
        if (r1 > r5) goto L_0x0084;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long a(com.google.android.exoplayer2.trackselection.ExoTrackSelection[] r16, boolean[] r17, com.google.android.exoplayer2.source.SampleStream[] r18, boolean[] r19, long r20) {
        /*
            r15 = this;
            r0 = r15
            r8 = r16
            r9 = r18
            int r1 = r9.length
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r1 = new com.google.android.exoplayer2.source.ClippingMediaPeriod.ClippingSampleStream[r1]
            r0.e = r1
            int r1 = r9.length
            com.google.android.exoplayer2.source.SampleStream[] r10 = new com.google.android.exoplayer2.source.SampleStream[r1]
            r11 = 0
            r1 = 0
        L_0x000f:
            int r2 = r9.length
            r12 = 0
            if (r1 >= r2) goto L_0x0024
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r2 = r0.e
            r3 = r9[r1]
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream r3 = (com.google.android.exoplayer2.source.ClippingMediaPeriod.ClippingSampleStream) r3
            r2[r1] = r3
            if (r3 == 0) goto L_0x001f
            com.google.android.exoplayer2.source.SampleStream r12 = r3.a
        L_0x001f:
            r10[r1] = r12
            int r1 = r1 + 1
            goto L_0x000f
        L_0x0024:
            com.google.android.exoplayer2.source.MediaPeriod r1 = r0.a
            r2 = r16
            r3 = r17
            r4 = r10
            r5 = r19
            r6 = r20
            long r1 = r1.a(r2, r3, r4, r5, r6)
            boolean r3 = r15.g()
            r4 = 1
            if (r3 == 0) goto L_0x0066
            long r5 = r0.b
            int r3 = (r20 > r5 ? 1 : (r20 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x0066
            r13 = 0
            int r3 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r3 == 0) goto L_0x0061
            int r3 = r8.length
            r5 = 0
        L_0x0048:
            if (r5 >= r3) goto L_0x0061
            r6 = r8[r5]
            if (r6 == 0) goto L_0x005e
            com.google.android.exoplayer2.Format r6 = r6.g()
            java.lang.String r7 = r6.l
            java.lang.String r6 = r6.i
            boolean r6 = com.google.android.exoplayer2.util.MimeTypes.a(r7, r6)
            if (r6 != 0) goto L_0x005e
            r3 = 1
            goto L_0x0062
        L_0x005e:
            int r5 = r5 + 1
            goto L_0x0048
        L_0x0061:
            r3 = 0
        L_0x0062:
            if (r3 == 0) goto L_0x0066
            r5 = r1
            goto L_0x006b
        L_0x0066:
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x006b:
            r0.f = r5
            int r3 = (r1 > r20 ? 1 : (r1 == r20 ? 0 : -1))
            if (r3 == 0) goto L_0x0085
            long r5 = r0.b
            int r3 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r3 < 0) goto L_0x0084
            long r5 = r0.c
            r7 = -9223372036854775808
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x0085
            int r3 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r3 > 0) goto L_0x0084
            goto L_0x0085
        L_0x0084:
            r4 = 0
        L_0x0085:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r4)
        L_0x0088:
            int r3 = r9.length
            if (r11 >= r3) goto L_0x00b4
            r3 = r10[r11]
            if (r3 != 0) goto L_0x0094
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r3 = r0.e
            r3[r11] = r12
            goto L_0x00ab
        L_0x0094:
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r3 = r0.e
            r3 = r3[r11]
            if (r3 == 0) goto L_0x00a0
            com.google.android.exoplayer2.source.SampleStream r3 = r3.a
            r4 = r10[r11]
            if (r3 == r4) goto L_0x00ab
        L_0x00a0:
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r3 = r0.e
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream r4 = new com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream
            r5 = r10[r11]
            r4.<init>(r5)
            r3[r11] = r4
        L_0x00ab:
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r3 = r0.e
            r3 = r3[r11]
            r9[r11] = r3
            int r11 = r11 + 1
            goto L_0x0088
        L_0x00b4:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.ClippingMediaPeriod.a(com.google.android.exoplayer2.trackselection.ExoTrackSelection[], boolean[], com.google.android.exoplayer2.source.SampleStream[], boolean[], long):long");
    }

    public final void a() {
        this.a.a();
    }

    public final void a(long j, long j2) {
        this.b = j;
        this.c = j2;
    }

    public final void a(long j, boolean z) {
        this.a.a(j, z);
    }

    public final void a(MediaPeriod.Callback callback, long j) {
        this.d = callback;
        this.a.a((MediaPeriod.Callback) this, j);
    }

    public final void a(MediaPeriod mediaPeriod) {
        ((MediaPeriod.Callback) Assertions.b((Object) this.d)).a(this);
    }

    public final /* synthetic */ void a(SequenceableLoader sequenceableLoader) {
        ((MediaPeriod.Callback) Assertions.b((Object) this.d)).a(this);
    }

    public final void a_(long j) {
        this.a.a_(j);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0031, code lost:
        if (r0 > r7) goto L_0x0034;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long b(long r7) {
        /*
            r6 = this;
            r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r6.f = r0
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r0 = r6.e
            int r1 = r0.length
            r2 = 0
            r3 = 0
        L_0x000c:
            if (r3 >= r1) goto L_0x0017
            r4 = r0[r3]
            if (r4 == 0) goto L_0x0014
            r4.b = r2
        L_0x0014:
            int r3 = r3 + 1
            goto L_0x000c
        L_0x0017:
            com.google.android.exoplayer2.source.MediaPeriod r0 = r6.a
            long r0 = r0.b(r7)
            int r3 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x0033
            long r7 = r6.b
            int r3 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r3 < 0) goto L_0x0034
            long r7 = r6.c
            r3 = -9223372036854775808
            int r5 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0033
            int r3 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r3 > 0) goto L_0x0034
        L_0x0033:
            r2 = 1
        L_0x0034:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.ClippingMediaPeriod.b(long):long");
    }

    public final TrackGroupArray b() {
        return this.a.b();
    }

    public final long c() {
        if (g()) {
            long j = this.f;
            this.f = -9223372036854775807L;
            long c2 = c();
            return c2 != -9223372036854775807L ? c2 : j;
        }
        long c3 = this.a.c();
        if (c3 == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        boolean z = true;
        Assertions.b(c3 >= this.b);
        long j2 = this.c;
        if (j2 != Long.MIN_VALUE && c3 > j2) {
            z = false;
        }
        Assertions.b(z);
        return c3;
    }

    public final boolean c(long j) {
        return this.a.c(j);
    }

    public final long d() {
        long d2 = this.a.d();
        if (d2 != Long.MIN_VALUE) {
            long j = this.c;
            if (j == Long.MIN_VALUE || d2 < j) {
                return d2;
            }
        }
        return Long.MIN_VALUE;
    }

    public final long e() {
        long e2 = this.a.e();
        if (e2 != Long.MIN_VALUE) {
            long j = this.c;
            if (j == Long.MIN_VALUE || e2 < j) {
                return e2;
            }
        }
        return Long.MIN_VALUE;
    }

    public final boolean f() {
        return this.a.f();
    }

    /* access modifiers changed from: package-private */
    public final boolean g() {
        return this.f != -9223372036854775807L;
    }
}

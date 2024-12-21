package com.google.android.exoplayer2.audio;

import android.media.AudioTrack;
import android.os.SystemClock;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.lang.reflect.Method;

final class AudioTrackPositionTracker {
    private long A;
    private long B;
    private long C;
    private boolean D;
    private long E;
    private long F;
    final Listener a;
    AudioTrack b;
    int c;
    int d;
    AudioTimestampPoller e;
    int f;
    boolean g;
    long h;
    float i;
    long j;
    boolean k;
    boolean l;
    long m;
    long n;
    long o;
    long p;
    long q;
    long r;
    private final long[] s;
    private boolean t;
    private long u;
    private long v;
    private Method w;
    private int x;
    private int y;
    private long z;

    public interface Listener {
        void a(int i, long j);

        void a(long j);

        void a(long j, long j2, long j3, long j4);

        void b(long j);

        void b(long j, long j2, long j3, long j4);
    }

    public AudioTrackPositionTracker(Listener listener) {
        this.a = (Listener) Assertions.b((Object) listener);
        if (Util.a >= 18) {
            try {
                this.w = AudioTrack.class.getMethod("getLatency", (Class[]) null);
            } catch (NoSuchMethodException e2) {
            }
        }
        this.s = new long[10];
    }

    private long e() {
        return e(d());
    }

    public final int a(long j2) {
        return this.d - ((int) (j2 - (d() * ((long) this.c))));
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b3, code lost:
        if (r5 != false) goto L_0x00c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b6, code lost:
        if (r5 == false) goto L_0x00c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c9, code lost:
        r0.a();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long a(boolean r24) {
        /*
            r23 = this;
            r1 = r23
            android.media.AudioTrack r0 = r1.b
            java.lang.Object r0 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r0)
            android.media.AudioTrack r0 = (android.media.AudioTrack) r0
            int r0 = r0.getPlayState()
            r2 = 2
            r3 = 0
            r4 = 0
            r6 = 1
            r7 = 1000(0x3e8, double:4.94E-321)
            r9 = 3
            if (r0 != r9) goto L_0x018d
            long r17 = r23.e()
            int r0 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x018d
            long r10 = java.lang.System.nanoTime()
            long r13 = r10 / r7
            long r10 = r1.v
            long r10 = r13 - r10
            r15 = 30000(0x7530, double:1.4822E-319)
            int r0 = (r10 > r15 ? 1 : (r10 == r15 ? 0 : -1))
            if (r0 < 0) goto L_0x0060
            long[] r0 = r1.s
            int r10 = r1.x
            long r11 = r17 - r13
            r0[r10] = r11
            int r10 = r10 + r6
            r0 = 10
            int r10 = r10 % r0
            r1.x = r10
            int r10 = r1.y
            if (r10 >= r0) goto L_0x0045
            int r10 = r10 + r6
            r1.y = r10
        L_0x0045:
            r1.v = r13
            r1.u = r4
            r0 = 0
        L_0x004a:
            int r10 = r1.y
            if (r0 >= r10) goto L_0x0060
            long r11 = r1.u
            long[] r15 = r1.s
            r19 = r15[r0]
            long r4 = (long) r10
            long r19 = r19 / r4
            long r11 = r11 + r19
            r1.u = r11
            int r0 = r0 + 1
            r4 = 0
            goto L_0x004a
        L_0x0060:
            boolean r0 = r1.g
            if (r0 != 0) goto L_0x018d
            com.google.android.exoplayer2.audio.AudioTimestampPoller r0 = r1.e
            java.lang.Object r0 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r0)
            com.google.android.exoplayer2.audio.AudioTimestampPoller r0 = (com.google.android.exoplayer2.audio.AudioTimestampPoller) r0
            com.google.android.exoplayer2.audio.AudioTimestampPoller$AudioTimestampV19 r4 = r0.a
            r19 = 500000(0x7a120, double:2.47033E-318)
            if (r4 == 0) goto L_0x00f1
            long r4 = r0.e
            long r4 = r13 - r4
            long r10 = r0.d
            int r12 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r12 >= 0) goto L_0x007f
            goto L_0x00f1
        L_0x007f:
            r0.e = r13
            com.google.android.exoplayer2.audio.AudioTimestampPoller$AudioTimestampV19 r4 = r0.a
            android.media.AudioTrack r5 = r4.a
            android.media.AudioTimestamp r10 = r4.b
            boolean r5 = r5.getTimestamp(r10)
            if (r5 == 0) goto L_0x00a8
            android.media.AudioTimestamp r10 = r4.b
            long r10 = r10.framePosition
            long r7 = r4.d
            int r12 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r12 <= 0) goto L_0x009e
            long r7 = r4.c
            r15 = 1
            long r7 = r7 + r15
            r4.c = r7
        L_0x009e:
            r4.d = r10
            long r7 = r4.c
            r12 = 32
            long r7 = r7 << r12
            long r10 = r10 + r7
            r4.e = r10
        L_0x00a8:
            int r4 = r0.b
            switch(r4) {
                case 0: goto L_0x00cd;
                case 1: goto L_0x00b9;
                case 2: goto L_0x00b6;
                case 3: goto L_0x00b3;
                case 4: goto L_0x00f2;
                default: goto L_0x00ad;
            }
        L_0x00ad:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            throw r0
        L_0x00b3:
            if (r5 == 0) goto L_0x00f2
            goto L_0x00c9
        L_0x00b6:
            if (r5 != 0) goto L_0x00f2
            goto L_0x00c9
        L_0x00b9:
            if (r5 == 0) goto L_0x00c9
            com.google.android.exoplayer2.audio.AudioTimestampPoller$AudioTimestampV19 r4 = r0.a
            long r7 = r4.e
            long r9 = r0.f
            int r4 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r4 <= 0) goto L_0x00f2
            r0.a(r2)
            goto L_0x00f2
        L_0x00c9:
            r0.a()
            goto L_0x00f2
        L_0x00cd:
            if (r5 == 0) goto L_0x00e5
            com.google.android.exoplayer2.audio.AudioTimestampPoller$AudioTimestampV19 r4 = r0.a
            long r7 = r4.a()
            long r9 = r0.c
            int r4 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r4 < 0) goto L_0x00f1
            com.google.android.exoplayer2.audio.AudioTimestampPoller$AudioTimestampV19 r4 = r0.a
            long r7 = r4.e
            r0.f = r7
            r0.a(r6)
            goto L_0x00f2
        L_0x00e5:
            long r7 = r0.c
            long r7 = r13 - r7
            int r4 = (r7 > r19 ? 1 : (r7 == r19 ? 0 : -1))
            if (r4 <= 0) goto L_0x00f2
            r0.a(r9)
            goto L_0x00f2
        L_0x00f1:
            r5 = 0
        L_0x00f2:
            r7 = 5000000(0x4c4b40, double:2.470328E-317)
            if (r5 == 0) goto L_0x013b
            long r4 = r0.b()
            long r11 = r0.c()
            long r9 = r4 - r13
            long r9 = java.lang.Math.abs(r9)
            r15 = 4
            int r16 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r16 <= 0) goto L_0x0119
            com.google.android.exoplayer2.audio.AudioTrackPositionTracker$Listener r10 = r1.a
            r21 = r13
            r13 = r4
            r9 = 4
            r15 = r21
            r10.b(r11, r13, r15, r17)
        L_0x0115:
            r0.a(r9)
            goto L_0x013d
        L_0x0119:
            r21 = r13
            r9 = 4
            long r13 = r1.e(r11)
            long r13 = r13 - r17
            long r13 = java.lang.Math.abs(r13)
            int r10 = (r13 > r7 ? 1 : (r13 == r7 ? 0 : -1))
            if (r10 <= 0) goto L_0x0133
            com.google.android.exoplayer2.audio.AudioTrackPositionTracker$Listener r10 = r1.a
            r13 = r4
            r15 = r21
            r10.a(r11, r13, r15, r17)
            goto L_0x0115
        L_0x0133:
            int r4 = r0.b
            if (r4 != r9) goto L_0x013d
            r0.a()
            goto L_0x013d
        L_0x013b:
            r21 = r13
        L_0x013d:
            boolean r0 = r1.l
            if (r0 == 0) goto L_0x018d
            java.lang.reflect.Method r0 = r1.w
            if (r0 == 0) goto L_0x018d
            long r4 = r1.m
            r10 = r21
            long r13 = r10 - r4
            int r4 = (r13 > r19 ? 1 : (r13 == r19 ? 0 : -1))
            if (r4 < 0) goto L_0x018d
            android.media.AudioTrack r4 = r1.b     // Catch:{ Exception -> 0x0187 }
            java.lang.Object r4 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r4)     // Catch:{ Exception -> 0x0187 }
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x0187 }
            java.lang.Object r0 = r0.invoke(r4, r5)     // Catch:{ Exception -> 0x0187 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Exception -> 0x0187 }
            java.lang.Object r0 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r0)     // Catch:{ Exception -> 0x0187 }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ Exception -> 0x0187 }
            int r0 = r0.intValue()     // Catch:{ Exception -> 0x0187 }
            long r4 = (long) r0     // Catch:{ Exception -> 0x0187 }
            r12 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 * r12
            long r12 = r1.h     // Catch:{ Exception -> 0x0187 }
            long r4 = r4 - r12
            r1.j = r4     // Catch:{ Exception -> 0x0187 }
            r12 = 0
            long r4 = java.lang.Math.max(r4, r12)     // Catch:{ Exception -> 0x0187 }
            r1.j = r4     // Catch:{ Exception -> 0x0187 }
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 <= 0) goto L_0x018b
            com.google.android.exoplayer2.audio.AudioTrackPositionTracker$Listener r0 = r1.a     // Catch:{ Exception -> 0x0187 }
            r0.b(r4)     // Catch:{ Exception -> 0x0187 }
            r4 = 0
            r1.j = r4     // Catch:{ Exception -> 0x0187 }
            goto L_0x018b
        L_0x0187:
            r0 = move-exception
            r0 = 0
            r1.w = r0
        L_0x018b:
            r1.m = r10
        L_0x018d:
            long r4 = java.lang.System.nanoTime()
            r7 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 / r7
            com.google.android.exoplayer2.audio.AudioTimestampPoller r0 = r1.e
            java.lang.Object r0 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r0)
            com.google.android.exoplayer2.audio.AudioTimestampPoller r0 = (com.google.android.exoplayer2.audio.AudioTimestampPoller) r0
            int r7 = r0.b
            if (r7 != r2) goto L_0x01a1
            r3 = 1
        L_0x01a1:
            if (r3 == 0) goto L_0x01b9
            long r7 = r0.c()
            long r7 = r1.e(r7)
            long r9 = r0.b()
            long r9 = r4 - r9
            float r0 = r1.i
            long r9 = com.google.android.exoplayer2.util.Util.a((long) r9, (float) r0)
            long r7 = r7 + r9
            goto L_0x01d0
        L_0x01b9:
            int r0 = r1.y
            if (r0 != 0) goto L_0x01c2
            long r7 = r23.e()
            goto L_0x01c5
        L_0x01c2:
            long r7 = r1.u
            long r7 = r7 + r4
        L_0x01c5:
            if (r24 != 0) goto L_0x01d0
            long r9 = r1.j
            long r7 = r7 - r9
            r9 = 0
            long r7 = java.lang.Math.max(r9, r7)
        L_0x01d0:
            boolean r0 = r1.D
            if (r0 == r3) goto L_0x01dc
            long r9 = r1.C
            r1.F = r9
            long r9 = r1.B
            r1.E = r9
        L_0x01dc:
            long r9 = r1.F
            long r9 = r4 - r9
            r11 = 1000000(0xf4240, double:4.940656E-318)
            int r0 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r0 >= 0) goto L_0x01fd
            long r13 = r1.E
            float r0 = r1.i
            long r15 = com.google.android.exoplayer2.util.Util.a((long) r9, (float) r0)
            long r13 = r13 + r15
            r15 = 1000(0x3e8, double:4.94E-321)
            long r9 = r9 * r15
            long r9 = r9 / r11
            long r7 = r7 * r9
            long r9 = r15 - r9
            long r9 = r9 * r13
            long r7 = r7 + r9
            long r7 = r7 / r15
        L_0x01fd:
            boolean r0 = r1.t
            if (r0 != 0) goto L_0x0223
            long r9 = r1.B
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 <= 0) goto L_0x0223
            r1.t = r6
            long r9 = r7 - r9
            long r9 = com.google.android.exoplayer2.C.a((long) r9)
            float r0 = r1.i
            long r9 = com.google.android.exoplayer2.util.Util.b((long) r9, (float) r0)
            long r11 = java.lang.System.currentTimeMillis()
            long r9 = com.google.android.exoplayer2.C.a((long) r9)
            long r11 = r11 - r9
            com.google.android.exoplayer2.audio.AudioTrackPositionTracker$Listener r0 = r1.a
            r0.a(r11)
        L_0x0223:
            r1.C = r4
            r1.B = r7
            r1.D = r3
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.AudioTrackPositionTracker.a(boolean):long");
    }

    public final boolean a() {
        return ((AudioTrack) Assertions.b((Object) this.b)).getPlayState() == 3;
    }

    public final long b(long j2) {
        return C.a(e(j2 - d()));
    }

    public final void b() {
        c();
        this.b = null;
        this.e = null;
    }

    /* access modifiers changed from: package-private */
    public final void c() {
        this.u = 0;
        this.y = 0;
        this.x = 0;
        this.v = 0;
        this.C = 0;
        this.F = 0;
        this.t = false;
    }

    public final void c(long j2) {
        this.z = d();
        this.q = SystemClock.elapsedRealtime() * 1000;
        this.A = j2;
    }

    /* access modifiers changed from: package-private */
    public final long d() {
        AudioTrack audioTrack = (AudioTrack) Assertions.b((Object) this.b);
        if (this.q != -9223372036854775807L) {
            return Math.min(this.A, this.z + ((((SystemClock.elapsedRealtime() * 1000) - this.q) * ((long) this.f)) / 1000000));
        }
        int playState = audioTrack.getPlayState();
        if (playState == 1) {
            return 0;
        }
        long playbackHeadPosition = ((long) audioTrack.getPlaybackHeadPosition()) & 4294967295L;
        if (this.g) {
            if (playState == 2 && playbackHeadPosition == 0) {
                this.p = this.n;
            }
            playbackHeadPosition += this.p;
        }
        if (Util.a <= 29) {
            if (playbackHeadPosition == 0 && this.n > 0 && playState == 3) {
                if (this.r == -9223372036854775807L) {
                    this.r = SystemClock.elapsedRealtime();
                }
                return this.n;
            }
            this.r = -9223372036854775807L;
        }
        if (this.n > playbackHeadPosition) {
            this.o++;
        }
        this.n = playbackHeadPosition;
        return playbackHeadPosition + (this.o << 32);
    }

    public final boolean d(long j2) {
        if (j2 <= d()) {
            return this.g && ((AudioTrack) Assertions.b((Object) this.b)).getPlayState() == 2 && (d() > 0 ? 1 : (d() == 0 ? 0 : -1)) == 0;
        }
    }

    /* access modifiers changed from: package-private */
    public final long e(long j2) {
        return (j2 * 1000000) / ((long) this.f);
    }
}

package com.google.android.exoplayer2;

import android.os.SystemClock;
import com.dreamers.exoplayercore.repack.C0000a;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.util.Util;

public final class DefaultLivePlaybackSpeedControl implements LivePlaybackSpeedControl {
    private final float a;
    private final float b;
    private final long c;
    private final float d;
    private final long e;
    private final long f;
    private final float g;
    private long h;
    private long i;
    private long j;
    private long k;
    private long l;
    private long m;
    private float n;
    private float o;
    private float p;
    private long q;
    private long r;
    private long s;

    public final class Builder {
        float a = 0.97f;
        float b = 1.03f;
        long c = 1000;
        float d = 1.0E-7f;
        long e = C.b(20);
        long f = C.b(500);
        float g = 0.999f;
    }

    private DefaultLivePlaybackSpeedControl(float f2, float f3, long j2, float f4, long j3, long j4, float f5) {
        this.a = f2;
        this.b = f3;
        this.c = j2;
        this.d = f4;
        this.e = j3;
        this.f = j4;
        this.g = f5;
        this.h = -9223372036854775807L;
        this.i = -9223372036854775807L;
        this.k = -9223372036854775807L;
        this.l = -9223372036854775807L;
        this.o = f2;
        this.n = f3;
        this.p = 1.0f;
        this.q = -9223372036854775807L;
        this.j = -9223372036854775807L;
        this.m = -9223372036854775807L;
        this.r = -9223372036854775807L;
        this.s = -9223372036854775807L;
    }

    /* synthetic */ DefaultLivePlaybackSpeedControl(float f2, float f3, long j2, float f4, long j3, long j4, float f5, byte b2) {
        this(f2, f3, j2, f4, j3, j4, f5);
    }

    private static long a(long j2, long j3, float f2) {
        return (long) ((((float) j2) * f2) + ((1.0f - f2) * ((float) j3)));
    }

    private void c() {
        long j2 = this.h;
        if (j2 != -9223372036854775807L) {
            long j3 = this.i;
            if (j3 != -9223372036854775807L) {
                j2 = j3;
            }
            long j4 = this.k;
            if (j4 != -9223372036854775807L && j2 < j4) {
                j2 = j4;
            }
            long j5 = this.l;
            if (j5 != -9223372036854775807L && j2 > j5) {
                j2 = j5;
            }
        } else {
            j2 = -9223372036854775807L;
        }
        if (this.j != j2) {
            this.j = j2;
            this.m = j2;
            this.r = -9223372036854775807L;
            this.s = -9223372036854775807L;
            this.q = -9223372036854775807L;
        }
    }

    public final float a(long j2, long j3) {
        long j4;
        if (this.h == -9223372036854775807L) {
            return 1.0f;
        }
        long j5 = j2 - j3;
        long j6 = this.r;
        if (j6 == -9223372036854775807L) {
            this.r = j5;
            j4 = 0;
        } else {
            long max = Math.max(j5, a(j6, j5, this.g));
            this.r = max;
            j4 = a(this.s, Math.abs(j5 - max), this.g);
        }
        this.s = j4;
        if (this.q != -9223372036854775807L && SystemClock.elapsedRealtime() - this.q < this.c) {
            return this.p;
        }
        this.q = SystemClock.elapsedRealtime();
        long j7 = this.r + (this.s * 3);
        if (this.m > j7) {
            float b2 = (float) C.b(this.c);
            long[] jArr = {j7, this.j, this.m - (((long) ((this.p - 1.0f) * b2)) + ((long) ((this.n - 1.0f) * b2)))};
            C0000a.a(true);
            long j8 = jArr[0];
            for (int i2 = 1; i2 < 3; i2++) {
                long j9 = jArr[i2];
                if (j9 > j8) {
                    j8 = j9;
                }
            }
            this.m = j8;
        } else {
            long a2 = Util.a(j2 - ((long) (Math.max(0.0f, this.p - 1.0f) / this.d)), this.m, j7);
            this.m = a2;
            long j10 = this.l;
            if (j10 != -9223372036854775807L && a2 > j10) {
                this.m = j10;
            }
        }
        long j11 = j2 - this.m;
        if (Math.abs(j11) < this.e) {
            this.p = 1.0f;
        } else {
            this.p = Util.a((this.d * ((float) j11)) + 1.0f, this.o, this.n);
        }
        return this.p;
    }

    public final void a() {
        long j2 = this.m;
        if (j2 != -9223372036854775807L) {
            long j3 = j2 + this.f;
            this.m = j3;
            long j4 = this.l;
            if (j4 != -9223372036854775807L && j3 > j4) {
                this.m = j4;
            }
            this.q = -9223372036854775807L;
        }
    }

    public final void a(long j2) {
        this.i = j2;
        c();
    }

    public final void a(MediaItem.LiveConfiguration liveConfiguration) {
        this.h = C.b(liveConfiguration.a);
        this.k = C.b(liveConfiguration.b);
        this.l = C.b(liveConfiguration.c);
        this.o = liveConfiguration.d != -3.4028235E38f ? liveConfiguration.d : this.a;
        this.n = liveConfiguration.e != -3.4028235E38f ? liveConfiguration.e : this.b;
        c();
    }

    public final long b() {
        return this.m;
    }
}

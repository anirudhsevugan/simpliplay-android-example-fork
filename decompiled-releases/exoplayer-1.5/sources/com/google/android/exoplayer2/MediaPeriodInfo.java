package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.util.Util;

final class MediaPeriodInfo {
    public final MediaSource.MediaPeriodId a;
    public final long b;
    public final long c;
    public final long d;
    public final long e;
    public final boolean f;
    public final boolean g;
    public final boolean h;

    MediaPeriodInfo(MediaSource.MediaPeriodId mediaPeriodId, long j, long j2, long j3, long j4, boolean z, boolean z2, boolean z3) {
        this.a = mediaPeriodId;
        this.b = j;
        this.c = j2;
        this.d = j3;
        this.e = j4;
        this.f = z;
        this.g = z2;
        this.h = z3;
    }

    public final MediaPeriodInfo a(long j) {
        if (j == this.b) {
            return this;
        }
        return new MediaPeriodInfo(this.a, j, this.c, this.d, this.e, this.f, this.g, this.h);
    }

    public final MediaPeriodInfo b(long j) {
        if (j == this.c) {
            return this;
        }
        return new MediaPeriodInfo(this.a, this.b, j, this.d, this.e, this.f, this.g, this.h);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            MediaPeriodInfo mediaPeriodInfo = (MediaPeriodInfo) obj;
            return this.b == mediaPeriodInfo.b && this.c == mediaPeriodInfo.c && this.d == mediaPeriodInfo.d && this.e == mediaPeriodInfo.e && this.f == mediaPeriodInfo.f && this.g == mediaPeriodInfo.g && this.h == mediaPeriodInfo.h && Util.a((Object) this.a, (Object) mediaPeriodInfo.a);
        }
    }

    public final int hashCode() {
        return ((((((((((((((this.a.hashCode() + 527) * 31) + ((int) this.b)) * 31) + ((int) this.c)) * 31) + ((int) this.d)) * 31) + ((int) this.e)) * 31) + (this.f ? 1 : 0)) * 31) + (this.g ? 1 : 0)) * 31) + (this.h ? 1 : 0);
    }
}

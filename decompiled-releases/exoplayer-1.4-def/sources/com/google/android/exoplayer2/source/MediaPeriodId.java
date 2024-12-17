package com.google.android.exoplayer2.source;

public class MediaPeriodId {
    public final Object a;
    public final int b;
    public final int c;
    public final long d;
    public final int e;

    protected MediaPeriodId(MediaPeriodId mediaPeriodId) {
        this.a = mediaPeriodId.a;
        this.b = mediaPeriodId.b;
        this.c = mediaPeriodId.c;
        this.d = mediaPeriodId.d;
        this.e = mediaPeriodId.e;
    }

    public MediaPeriodId(Object obj) {
        this(obj, (byte) 0);
    }

    private MediaPeriodId(Object obj, byte b2) {
        this(obj, -1, -1, -1, -1);
    }

    public MediaPeriodId(Object obj, int i, int i2, long j) {
        this(obj, i, i2, j, -1);
    }

    private MediaPeriodId(Object obj, int i, int i2, long j, int i3) {
        this.a = obj;
        this.b = i;
        this.c = i2;
        this.d = j;
        this.e = i3;
    }

    public MediaPeriodId(Object obj, long j, int i) {
        this(obj, -1, -1, j, i);
    }

    public MediaPeriodId a(Object obj) {
        if (this.a.equals(obj)) {
            return this;
        }
        return new MediaPeriodId(obj, this.b, this.c, this.d, this.e);
    }

    public final boolean a() {
        return this.b != -1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaPeriodId)) {
            return false;
        }
        MediaPeriodId mediaPeriodId = (MediaPeriodId) obj;
        return this.a.equals(mediaPeriodId.a) && this.b == mediaPeriodId.b && this.c == mediaPeriodId.c && this.d == mediaPeriodId.d && this.e == mediaPeriodId.e;
    }

    public int hashCode() {
        return ((((((((this.a.hashCode() + 527) * 31) + this.b) * 31) + this.c) * 31) + ((int) this.d)) * 31) + this.e;
    }
}

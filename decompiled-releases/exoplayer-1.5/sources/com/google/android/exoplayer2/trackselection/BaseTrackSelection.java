package com.google.android.exoplayer2.trackselection;

import android.os.SystemClock;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;
import java.util.List;

public abstract class BaseTrackSelection implements ExoTrackSelection {
    protected final int a;
    private TrackGroup b;
    private int[] c;
    private final Format[] d;
    private final long[] e;
    private int f;

    public BaseTrackSelection(TrackGroup trackGroup, int... iArr) {
        this(trackGroup, iArr, (byte) 0);
    }

    public BaseTrackSelection(TrackGroup trackGroup, int[] iArr, byte b2) {
        int i = 0;
        Assertions.b(iArr.length > 0);
        this.b = (TrackGroup) Assertions.b((Object) trackGroup);
        int length = iArr.length;
        this.a = length;
        this.d = new Format[length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            this.d[i2] = trackGroup.b[iArr[i2]];
        }
        Arrays.sort(this.d, BaseTrackSelection$$Lambda$0.a);
        this.c = new int[this.a];
        while (true) {
            int i3 = this.a;
            if (i < i3) {
                this.c[i] = trackGroup.a(this.d[i]);
                i++;
            } else {
                this.e = new long[i3];
                return;
            }
        }
    }

    static final /* synthetic */ int a(Format format, Format format2) {
        return format2.h - format.h;
    }

    public int a(long j, List list) {
        return list.size();
    }

    public final int a(Format format) {
        for (int i = 0; i < this.a; i++) {
            if (this.d[i] == format) {
                return i;
            }
        }
        return -1;
    }

    public final Format a(int i) {
        return this.d[i];
    }

    public void a(float f2) {
    }

    public final boolean a(int i, long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        boolean b2 = b(i, elapsedRealtime);
        int i2 = 0;
        while (i2 < this.a && !b2) {
            b2 = i2 != i && !b(i2, elapsedRealtime);
            i2++;
        }
        if (!b2) {
            return false;
        }
        long[] jArr = this.e;
        jArr[i] = Math.max(jArr[i], Util.b(elapsedRealtime, j));
        return true;
    }

    public final int b(int i) {
        return this.c[i];
    }

    public final boolean b(int i, long j) {
        return this.e[i] > j;
    }

    public final int c(int i) {
        for (int i2 = 0; i2 < this.a; i2++) {
            if (this.c[i2] == i) {
                return i2;
            }
        }
        return -1;
    }

    public void c() {
    }

    public void d() {
    }

    public final TrackGroup e() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            BaseTrackSelection baseTrackSelection = (BaseTrackSelection) obj;
            return this.b == baseTrackSelection.b && Arrays.equals(this.c, baseTrackSelection.c);
        }
    }

    public final int f() {
        return this.c.length;
    }

    public final Format g() {
        return this.d[a()];
    }

    public final int h() {
        return this.c[a()];
    }

    public int hashCode() {
        if (this.f == 0) {
            this.f = (System.identityHashCode(this.b) * 31) + Arrays.hashCode(this.c);
        }
        return this.f;
    }

    public final void i() {
        ExoTrackSelection$$CC.a();
    }

    public final void j() {
        ExoTrackSelection$$CC.b();
    }

    public final void k() {
        ExoTrackSelection$$CC.c();
    }

    public final boolean l() {
        return ExoTrackSelection$$CC.d();
    }
}

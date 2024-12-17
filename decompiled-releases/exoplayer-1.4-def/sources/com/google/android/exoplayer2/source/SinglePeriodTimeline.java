package com.google.android.exoplayer2.source;

import android.net.Uri;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.util.Assertions;

public final class SinglePeriodTimeline extends Timeline {
    private static final Object a = new Object();
    private final long c;
    private final long d;
    private final long e;
    private final long f;
    private final long g;
    private final long h;
    private final boolean i;
    private final boolean j;
    private final Object k;
    private final MediaItem l;
    private final MediaItem.LiveConfiguration m;

    static {
        MediaItem.Builder a2 = new MediaItem.Builder().a("SinglePeriodTimeline");
        a2.a = Uri.EMPTY;
        a2.a();
    }

    public SinglePeriodTimeline(long j2, long j3, long j4, long j5, long j6, long j7, boolean z, boolean z2, Object obj, MediaItem mediaItem, MediaItem.LiveConfiguration liveConfiguration) {
        this.c = j2;
        this.d = j3;
        this.e = j4;
        this.f = j5;
        this.g = j6;
        this.h = j7;
        this.i = z;
        this.j = z2;
        this.k = obj;
        this.l = (MediaItem) Assertions.b((Object) mediaItem);
        this.m = liveConfiguration;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    private SinglePeriodTimeline(long j2, long j3, boolean z, boolean z2, MediaItem mediaItem) {
        this(-9223372036854775807L, -9223372036854775807L, j2, j3, 0, 0, z, false, (Object) null, mediaItem, z2 ? mediaItem.c : null);
    }

    public SinglePeriodTimeline(long j2, boolean z, boolean z2, MediaItem mediaItem) {
        this(j2, j2, z, z2, mediaItem);
    }

    public final int a() {
        return 1;
    }

    public final Timeline.Period a(int i2, Timeline.Period period, boolean z) {
        Assertions.a(i2, 1);
        return period.a((Object) null, z ? a : null, this.e, -this.g);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0026, code lost:
        if (r1 > r3) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.exoplayer2.Timeline.Window a(int r29, com.google.android.exoplayer2.Timeline.Window r30, long r31) {
        /*
            r28 = this;
            r0 = r28
            r1 = 1
            r2 = r29
            com.google.android.exoplayer2.util.Assertions.a((int) r2, (int) r1)
            long r1 = r0.h
            boolean r3 = r0.j
            if (r3 == 0) goto L_0x0029
            r3 = 0
            int r5 = (r31 > r3 ? 1 : (r31 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0029
            long r3 = r0.f
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0022
        L_0x001f:
            r21 = r5
            goto L_0x002b
        L_0x0022:
            long r1 = r1 + r31
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r7 <= 0) goto L_0x0029
            goto L_0x001f
        L_0x0029:
            r21 = r1
        L_0x002b:
            java.lang.Object r9 = com.google.android.exoplayer2.Timeline.Window.a
            com.google.android.exoplayer2.MediaItem r10 = r0.l
            java.lang.Object r11 = r0.k
            long r12 = r0.c
            long r14 = r0.d
            r16 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            boolean r1 = r0.i
            r18 = r1
            boolean r1 = r0.j
            r19 = r1
            com.google.android.exoplayer2.MediaItem$LiveConfiguration r1 = r0.m
            r20 = r1
            long r1 = r0.f
            r23 = r1
            r25 = 0
            long r1 = r0.g
            r26 = r1
            r8 = r30
            com.google.android.exoplayer2.Timeline$Window r1 = r8.a(r9, r10, r11, r12, r14, r16, r18, r19, r20, r21, r23, r25, r26)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.SinglePeriodTimeline.a(int, com.google.android.exoplayer2.Timeline$Window, long):com.google.android.exoplayer2.Timeline$Window");
    }

    public final Object a(int i2) {
        Assertions.a(i2, 1);
        return a;
    }

    public final int b() {
        return 1;
    }

    public final int c(Object obj) {
        return a.equals(obj) ? 0 : -1;
    }
}

package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.ArrayList;

public final class ClippingMediaSource extends CompositeMediaSource {
    private final MediaSource a;
    private final long b;
    private final long c;
    private final boolean d;
    private final boolean e;
    private final boolean f;
    private final ArrayList g;
    private final Timeline.Window h;
    private ClippingTimeline i;
    private IllegalClippingException j;
    private long k;
    private long l;

    final class ClippingTimeline extends ForwardingTimeline {
        private final long c;
        private final long d;
        private final long e;
        private final boolean f;

        public ClippingTimeline(Timeline timeline, long j, long j2) {
            super(timeline);
            boolean z = false;
            if (timeline.b() == 1) {
                Timeline.Window a = timeline.a(0, new Timeline.Window(), 0);
                long max = Math.max(0, j);
                if (a.k || max == 0 || a.h) {
                    long max2 = j2 == Long.MIN_VALUE ? a.m : Math.max(0, j2);
                    if (a.m != -9223372036854775807L) {
                        max2 = max2 > a.m ? a.m : max2;
                        if (max > max2) {
                            throw new IllegalClippingException(2);
                        }
                    }
                    this.c = max;
                    this.d = max2;
                    this.e = max2 == -9223372036854775807L ? -9223372036854775807L : max2 - max;
                    if (a.i && (max2 == -9223372036854775807L || (a.m != -9223372036854775807L && max2 == a.m))) {
                        z = true;
                    }
                    this.f = z;
                    return;
                }
                throw new IllegalClippingException(1);
            }
            throw new IllegalClippingException(0);
        }

        public final Timeline.Period a(int i, Timeline.Period period, boolean z) {
            this.a.a(0, period, z);
            long j = period.e - this.c;
            long j2 = this.e;
            return period.a(period.a, period.b, j2 == -9223372036854775807L ? -9223372036854775807L : j2 - j, j);
        }

        public final Timeline.Window a(int i, Timeline.Window window, long j) {
            this.a.a(0, window, 0);
            window.p += this.c;
            window.m = this.e;
            window.i = this.f;
            if (window.l != -9223372036854775807L) {
                window.l = Math.max(window.l, this.c);
                int i2 = (this.d > -9223372036854775807L ? 1 : (this.d == -9223372036854775807L ? 0 : -1));
                long j2 = window.l;
                if (i2 != 0) {
                    j2 = Math.min(j2, this.d);
                }
                window.l = j2;
                window.l -= this.c;
            }
            long a = C.a(this.c);
            if (window.e != -9223372036854775807L) {
                window.e += a;
            }
            if (window.f != -9223372036854775807L) {
                window.f += a;
            }
            return window;
        }
    }

    public final class IllegalClippingException extends IOException {
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public IllegalClippingException(int r3) {
            /*
                r2 = this;
                switch(r3) {
                    case 0: goto L_0x000c;
                    case 1: goto L_0x0009;
                    case 2: goto L_0x0006;
                    default: goto L_0x0003;
                }
            L_0x0003:
                java.lang.String r3 = "unknown"
                goto L_0x000e
            L_0x0006:
                java.lang.String r3 = "start exceeds end"
                goto L_0x000e
            L_0x0009:
                java.lang.String r3 = "not seekable to start"
                goto L_0x000e
            L_0x000c:
                java.lang.String r3 = "invalid period count"
            L_0x000e:
                java.lang.String r3 = java.lang.String.valueOf(r3)
                int r0 = r3.length()
                java.lang.String r1 = "Illegal clipping: "
                if (r0 == 0) goto L_0x001f
                java.lang.String r3 = r1.concat(r3)
                goto L_0x0024
            L_0x001f:
                java.lang.String r3 = new java.lang.String
                r3.<init>(r1)
            L_0x0024:
                r2.<init>(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.ClippingMediaSource.IllegalClippingException.<init>(int):void");
        }
    }

    public ClippingMediaSource(MediaSource mediaSource, long j2, long j3, boolean z, boolean z2, boolean z3) {
        Assertions.a(j2 >= 0);
        this.a = (MediaSource) Assertions.b((Object) mediaSource);
        this.b = j2;
        this.c = j3;
        this.d = z;
        this.e = z2;
        this.f = z3;
        this.g = new ArrayList();
        this.h = new Timeline.Window();
    }

    private void b(Timeline timeline) {
        long j2;
        long j3;
        timeline.a(0, this.h, 0);
        long j4 = this.h.p;
        long j5 = Long.MIN_VALUE;
        if (this.i == null || this.g.isEmpty() || this.e) {
            long j6 = this.b;
            long j7 = this.c;
            if (this.f) {
                long j8 = this.h.l;
                j6 += j8;
                j7 += j8;
            }
            this.k = j4 + j6;
            if (this.c != Long.MIN_VALUE) {
                j5 = j4 + j7;
            }
            this.l = j5;
            int size = this.g.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((ClippingMediaPeriod) this.g.get(i2)).a(this.k, this.l);
            }
            j3 = j6;
            j2 = j7;
        } else {
            long j9 = this.k - j4;
            if (this.c != Long.MIN_VALUE) {
                j5 = this.l - j4;
            }
            j2 = j5;
            j3 = j9;
        }
        try {
            ClippingTimeline clippingTimeline = new ClippingTimeline(timeline, j3, j2);
            this.i = clippingTimeline;
            a((Timeline) clippingTimeline);
        } catch (IllegalClippingException e2) {
            this.j = e2;
        }
    }

    public final MediaPeriod a(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j2) {
        ClippingMediaPeriod clippingMediaPeriod = new ClippingMediaPeriod(this.a.a(mediaPeriodId, allocator, j2), this.d, this.k, this.l);
        this.g.add(clippingMediaPeriod);
        return clippingMediaPeriod;
    }

    public final void a(MediaPeriod mediaPeriod) {
        Assertions.b(this.g.remove(mediaPeriod));
        this.a.a(((ClippingMediaPeriod) mediaPeriod).a);
        if (this.g.isEmpty() && !this.e) {
            b(((ClippingTimeline) Assertions.b((Object) this.i)).a);
        }
    }

    /* access modifiers changed from: protected */
    public final void a(TransferListener transferListener) {
        super.a(transferListener);
        a((Object) null, this.a);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void a(Object obj, MediaSource mediaSource, Timeline timeline) {
        if (this.j == null) {
            b(timeline);
        }
    }

    /* access modifiers changed from: protected */
    public final void c() {
        super.c();
        this.j = null;
        this.i = null;
    }

    public final MediaItem g() {
        return this.a.g();
    }

    public final void h() {
        IllegalClippingException illegalClippingException = this.j;
        if (illegalClippingException == null) {
            super.h();
            return;
        }
        throw illegalClippingException;
    }
}

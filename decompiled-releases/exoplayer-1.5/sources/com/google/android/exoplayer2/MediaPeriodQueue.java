package com.google.android.exoplayer2;

import android.os.Handler;
import android.util.Pair;
import com.dreamers.exoplayercore.repack.bG;
import com.dreamers.exoplayercore.repack.bH;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.analytics.AnalyticsCollector;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.util.Assertions;

final class MediaPeriodQueue {
    final AnalyticsCollector a;
    int b;
    boolean c;
    MediaPeriodHolder d;
    MediaPeriodHolder e;
    MediaPeriodHolder f;
    int g;
    Object h;
    private final Timeline.Period i = new Timeline.Period();
    private final Timeline.Window j = new Timeline.Window();
    private final Handler k;
    private long l;
    private long m;

    public MediaPeriodQueue(AnalyticsCollector analyticsCollector, Handler handler) {
        this.a = analyticsCollector;
        this.k = handler;
    }

    private long a(Timeline timeline, Object obj) {
        MediaPeriodHolder mediaPeriodHolder;
        int c2;
        int i2 = timeline.a(obj, this.i).c;
        Object obj2 = this.h;
        if (obj2 != null && (c2 = timeline.c(obj2)) != -1 && timeline.a(c2, this.i, false).c == i2) {
            return this.m;
        }
        MediaPeriodHolder mediaPeriodHolder2 = this.d;
        while (true) {
            if (mediaPeriodHolder == null) {
                mediaPeriodHolder = this.d;
                while (mediaPeriodHolder != null) {
                    int c3 = timeline.c(mediaPeriodHolder.b);
                    if (c3 == -1 || timeline.a(c3, this.i, false).c != i2) {
                        mediaPeriodHolder = mediaPeriodHolder.h;
                    }
                }
                long j2 = this.l;
                this.l = 1 + j2;
                if (this.d == null) {
                    this.h = obj;
                    this.m = j2;
                }
                return j2;
            } else if (mediaPeriodHolder.b.equals(obj)) {
                break;
            } else {
                mediaPeriodHolder2 = mediaPeriodHolder.h;
            }
        }
        return mediaPeriodHolder.f.a.d;
    }

    private MediaPeriodInfo a(Timeline timeline, Object obj, int i2, int i3, long j2, long j3) {
        MediaSource.MediaPeriodId mediaPeriodId = new MediaSource.MediaPeriodId(obj, i2, i3, j3);
        timeline.a(mediaPeriodId.a, this.i);
        this.i.b(i2);
        return new MediaPeriodInfo(mediaPeriodId, 0, j2, -9223372036854775807L, -9223372036854775807L, false, false, false);
    }

    private MediaPeriodInfo a(Timeline timeline, Object obj, long j2, long j3, long j4) {
        Timeline timeline2 = timeline;
        Object obj2 = obj;
        long j5 = j2;
        timeline2.a(obj2, this.i);
        int b2 = this.i.b(j5);
        MediaSource.MediaPeriodId mediaPeriodId = new MediaSource.MediaPeriodId(obj2, j4, b2);
        boolean a2 = a(mediaPeriodId);
        boolean a3 = a(timeline2, mediaPeriodId);
        boolean a4 = a(timeline2, mediaPeriodId, a2);
        long a5 = b2 != -1 ? this.i.a(b2) : -9223372036854775807L;
        long j6 = (a5 == -9223372036854775807L || a5 == Long.MIN_VALUE) ? this.i.d : a5;
        if (j6 != -9223372036854775807L && j5 >= j6) {
            j5 = Math.max(0, j6 - 1);
        }
        return new MediaPeriodInfo(mediaPeriodId, j5, j3, a5, j6, a2, a3, a4);
    }

    private static MediaSource.MediaPeriodId a(Timeline timeline, Object obj, long j2, long j3, Timeline.Period period) {
        timeline.a(obj, period);
        int a2 = period.a(j2);
        if (a2 == -1) {
            return new MediaSource.MediaPeriodId(obj, j3, period.b(j2));
        }
        return new MediaSource.MediaPeriodId(obj, a2, period.b(a2), j3);
    }

    static boolean a(long j2, long j3) {
        return j2 == -9223372036854775807L || j2 == j3;
    }

    static boolean a(MediaPeriodInfo mediaPeriodInfo, MediaPeriodInfo mediaPeriodInfo2) {
        return mediaPeriodInfo.b == mediaPeriodInfo2.b && mediaPeriodInfo.a.equals(mediaPeriodInfo2.a);
    }

    private boolean a(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId) {
        if (!a(mediaPeriodId)) {
            return false;
        }
        return timeline.a(timeline.a(mediaPeriodId.a, this.i).c, this.j, 0).o == timeline.c(mediaPeriodId.a);
    }

    private boolean a(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, boolean z) {
        int c2 = timeline.c(mediaPeriodId.a);
        if (!timeline.a(timeline.a(c2, this.i, false).c, this.j, 0).i) {
            return timeline.b(c2, this.i, this.j, this.b, this.c) && z;
        }
    }

    private static boolean a(MediaSource.MediaPeriodId mediaPeriodId) {
        return !mediaPeriodId.a() && mediaPeriodId.e == -1;
    }

    public final MediaPeriodHolder a() {
        MediaPeriodHolder mediaPeriodHolder = this.d;
        if (mediaPeriodHolder == null) {
            return null;
        }
        if (mediaPeriodHolder == this.e) {
            this.e = mediaPeriodHolder.h;
        }
        this.d.e();
        int i2 = this.g - 1;
        this.g = i2;
        if (i2 == 0) {
            this.f = null;
            this.h = this.d.b;
            this.m = this.d.f.a.d;
        }
        this.d = this.d.h;
        c();
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public final MediaPeriodInfo a(Timeline timeline, MediaPeriodHolder mediaPeriodHolder, long j2) {
        long j3;
        long j4;
        long j5;
        Timeline timeline2 = timeline;
        MediaPeriodHolder mediaPeriodHolder2 = mediaPeriodHolder;
        MediaPeriodInfo mediaPeriodInfo = mediaPeriodHolder2.f;
        long j6 = (mediaPeriodHolder2.k + mediaPeriodInfo.e) - j2;
        if (mediaPeriodInfo.f) {
            int a2 = timeline.a(timeline.c(mediaPeriodInfo.a.a), this.i, this.j, this.b, this.c);
            if (a2 == -1) {
                return null;
            }
            int i2 = timeline.a(a2, this.i, true).c;
            Object obj = this.i.b;
            long j7 = mediaPeriodInfo.a.d;
            if (timeline.a(i2, this.j, 0).n == a2) {
                Pair a3 = timeline.a(this.j, this.i, i2, -9223372036854775807L, Math.max(0, j6));
                if (a3 == null) {
                    return null;
                }
                obj = a3.first;
                long longValue = ((Long) a3.second).longValue();
                MediaPeriodHolder mediaPeriodHolder3 = mediaPeriodHolder2.h;
                if (mediaPeriodHolder3 == null || !mediaPeriodHolder3.b.equals(obj)) {
                    j5 = this.l;
                    this.l = 1 + j5;
                } else {
                    j5 = mediaPeriodHolder3.f.a.d;
                }
                j3 = longValue;
                j4 = -9223372036854775807L;
                j7 = j5;
            } else {
                j4 = 0;
                j3 = 0;
            }
            return a(timeline, a(timeline, obj, j3, j7, this.i), j4, j3);
        }
        MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodInfo.a;
        timeline.a(mediaPeriodId.a, this.i);
        if (mediaPeriodId.a()) {
            return null;
        }
        int b2 = this.i.b(mediaPeriodId.e);
        Object obj2 = mediaPeriodId.a;
        if (b2 == -1) {
            return a(timeline, obj2, mediaPeriodInfo.e, mediaPeriodInfo.e, mediaPeriodId.d);
        }
        return a(timeline, obj2, mediaPeriodId.e, b2, mediaPeriodInfo.e, mediaPeriodId.d);
    }

    public final MediaPeriodInfo a(Timeline timeline, MediaPeriodInfo mediaPeriodInfo) {
        MediaSource.MediaPeriodId mediaPeriodId = mediaPeriodInfo.a;
        boolean a2 = a(mediaPeriodId);
        boolean a3 = a(timeline, mediaPeriodId);
        boolean a4 = a(timeline, mediaPeriodId, a2);
        timeline.a(mediaPeriodInfo.a.a, this.i);
        long j2 = -9223372036854775807L;
        if (!mediaPeriodId.a()) {
            j2 = (mediaPeriodInfo.d == -9223372036854775807L || mediaPeriodInfo.d == Long.MIN_VALUE) ? this.i.d : mediaPeriodInfo.d;
        }
        return new MediaPeriodInfo(mediaPeriodId, mediaPeriodInfo.b, mediaPeriodInfo.c, mediaPeriodInfo.d, j2, a2, a3, a4);
    }

    /* access modifiers changed from: package-private */
    public final MediaPeriodInfo a(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId, long j2, long j3) {
        MediaSource.MediaPeriodId mediaPeriodId2 = mediaPeriodId;
        Timeline timeline2 = timeline;
        timeline.a(mediaPeriodId2.a, this.i);
        boolean a2 = mediaPeriodId.a();
        Object obj = mediaPeriodId2.a;
        if (a2) {
            return a(timeline, obj, mediaPeriodId2.b, mediaPeriodId2.c, j2, mediaPeriodId2.d);
        }
        return a(timeline, obj, j3, j2, mediaPeriodId2.d);
    }

    public final MediaSource.MediaPeriodId a(Timeline timeline, Object obj, long j2) {
        return a(timeline, obj, j2, a(timeline, obj), this.i);
    }

    public final void a(long j2) {
        MediaPeriodHolder mediaPeriodHolder = this.f;
        if (mediaPeriodHolder != null) {
            mediaPeriodHolder.a(j2);
        }
    }

    public final boolean a(MediaPeriodHolder mediaPeriodHolder) {
        boolean z = false;
        Assertions.b(mediaPeriodHolder != null);
        if (mediaPeriodHolder.equals(this.f)) {
            return false;
        }
        this.f = mediaPeriodHolder;
        while (mediaPeriodHolder.h != null) {
            mediaPeriodHolder = mediaPeriodHolder.h;
            if (mediaPeriodHolder == this.e) {
                this.e = this.d;
                z = true;
            }
            mediaPeriodHolder.e();
            this.g--;
        }
        this.f.a((MediaPeriodHolder) null);
        c();
        return z;
    }

    /* access modifiers changed from: package-private */
    public final boolean a(Timeline timeline) {
        MediaPeriodHolder mediaPeriodHolder = this.d;
        if (mediaPeriodHolder == null) {
            return true;
        }
        int c2 = timeline.c(mediaPeriodHolder.b);
        while (true) {
            c2 = timeline.a(c2, this.i, this.j, this.b, this.c);
            while (mediaPeriodHolder.h != null && !mediaPeriodHolder.f.f) {
                mediaPeriodHolder = mediaPeriodHolder.h;
            }
            MediaPeriodHolder mediaPeriodHolder2 = mediaPeriodHolder.h;
            if (c2 == -1 || mediaPeriodHolder2 == null || timeline.c(mediaPeriodHolder2.b) != c2) {
                boolean a2 = a(mediaPeriodHolder);
                mediaPeriodHolder.f = a(timeline, mediaPeriodHolder.f);
            } else {
                mediaPeriodHolder = mediaPeriodHolder2;
            }
        }
        boolean a22 = a(mediaPeriodHolder);
        mediaPeriodHolder.f = a(timeline, mediaPeriodHolder.f);
        return !a22;
    }

    public final boolean a(MediaPeriod mediaPeriod) {
        MediaPeriodHolder mediaPeriodHolder = this.f;
        return mediaPeriodHolder != null && mediaPeriodHolder.a == mediaPeriod;
    }

    public final void b() {
        if (this.g != 0) {
            MediaPeriodHolder mediaPeriodHolder = (MediaPeriodHolder) Assertions.a((Object) this.d);
            this.h = mediaPeriodHolder.b;
            this.m = mediaPeriodHolder.f.a.d;
            while (mediaPeriodHolder != null) {
                mediaPeriodHolder.e();
                mediaPeriodHolder = mediaPeriodHolder.h;
            }
            this.d = null;
            this.f = null;
            this.e = null;
            this.g = 0;
            c();
        }
    }

    /* access modifiers changed from: package-private */
    public final void c() {
        if (this.a != null) {
            bH i2 = bG.i();
            for (MediaPeriodHolder mediaPeriodHolder = this.d; mediaPeriodHolder != null; mediaPeriodHolder = mediaPeriodHolder.h) {
                i2.b(mediaPeriodHolder.f.a);
            }
            MediaPeriodHolder mediaPeriodHolder2 = this.e;
            this.k.post(new MediaPeriodQueue$$Lambda$0(this, i2, mediaPeriodHolder2 == null ? null : mediaPeriodHolder2.f.a));
        }
    }
}

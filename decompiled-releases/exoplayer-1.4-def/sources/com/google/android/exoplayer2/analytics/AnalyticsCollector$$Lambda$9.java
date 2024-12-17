package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$9 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final int b;
    private final long c;
    private final long d;

    AnalyticsCollector$$Lambda$9(AnalyticsListener.EventTime eventTime, int i, long j, long j2) {
        this.a = eventTime;
        this.b = i;
        this.c = j;
        this.d = j2;
    }

    public final void a(Object obj) {
        ((AnalyticsListener) obj).a(this.a, this.b, this.c, this.d);
    }
}

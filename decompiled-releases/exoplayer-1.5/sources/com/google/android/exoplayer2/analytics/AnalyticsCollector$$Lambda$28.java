package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$28 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final int b;
    private final int c;

    AnalyticsCollector$$Lambda$28(AnalyticsListener.EventTime eventTime, int i, int i2) {
        this.a = eventTime;
        this.b = i;
        this.c = i2;
    }

    public final void a(Object obj) {
        ((AnalyticsListener) obj).a(this.a, this.b, this.c);
    }
}

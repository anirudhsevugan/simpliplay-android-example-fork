package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$25 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final Object b;

    AnalyticsCollector$$Lambda$25(AnalyticsListener.EventTime eventTime, Object obj) {
        this.a = eventTime;
        this.b = obj;
    }

    public final void a(Object obj) {
        ((AnalyticsListener) obj).a(this.a, this.b);
    }
}

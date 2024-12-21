package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$55 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final Exception b;

    AnalyticsCollector$$Lambda$55(AnalyticsListener.EventTime eventTime, Exception exc) {
        this.a = eventTime;
        this.b = exc;
    }

    public final void a(Object obj) {
        ((AnalyticsListener) obj).a(this.a, this.b);
    }
}

package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$6 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final String b;

    AnalyticsCollector$$Lambda$6(AnalyticsListener.EventTime eventTime, String str, long j, long j2) {
        this.a = eventTime;
        this.b = str;
    }

    public final void a(Object obj) {
        AnalyticsCollector.d(this.a, this.b, (AnalyticsListener) obj);
    }
}

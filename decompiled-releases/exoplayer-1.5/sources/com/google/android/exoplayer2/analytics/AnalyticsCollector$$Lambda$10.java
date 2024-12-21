package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$10 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final String b;

    AnalyticsCollector$$Lambda$10(AnalyticsListener.EventTime eventTime, String str) {
        this.a = eventTime;
        this.b = str;
    }

    public final void a(Object obj) {
        ((AnalyticsListener) obj).b(this.a, this.b);
    }
}

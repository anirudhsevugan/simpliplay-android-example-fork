package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$7 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final Format b;

    AnalyticsCollector$$Lambda$7(AnalyticsListener.EventTime eventTime, Format format) {
        this.a = eventTime;
        this.b = format;
    }

    public final void a(Object obj) {
        AnalyticsCollector.b(this.a, this.b, (AnalyticsListener) obj);
    }
}

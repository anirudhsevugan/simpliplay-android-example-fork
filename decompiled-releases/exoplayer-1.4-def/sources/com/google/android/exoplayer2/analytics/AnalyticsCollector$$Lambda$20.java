package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$20 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final Format b;

    AnalyticsCollector$$Lambda$20(AnalyticsListener.EventTime eventTime, Format format) {
        this.a = eventTime;
        this.b = format;
    }

    public final void a(Object obj) {
        AnalyticsCollector.a(this.a, this.b, (AnalyticsListener) obj);
    }
}

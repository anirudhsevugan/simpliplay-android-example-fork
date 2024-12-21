package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$4 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final Metadata b;

    AnalyticsCollector$$Lambda$4(AnalyticsListener.EventTime eventTime, Metadata metadata) {
        this.a = eventTime;
        this.b = metadata;
    }

    public final void a(Object obj) {
        ((AnalyticsListener) obj).a(this.a, this.b);
    }
}

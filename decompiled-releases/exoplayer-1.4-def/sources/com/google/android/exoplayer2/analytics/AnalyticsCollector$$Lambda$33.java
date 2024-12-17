package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$33 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final MediaLoadData b;

    AnalyticsCollector$$Lambda$33(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
        this.a = eventTime;
        this.b = mediaLoadData;
    }

    public final void a(Object obj) {
        ((AnalyticsListener) obj).b(this.a, this.b);
    }
}

package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;
import java.util.List;

final /* synthetic */ class AnalyticsCollector$$Lambda$38 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final List b;

    AnalyticsCollector$$Lambda$38(AnalyticsListener.EventTime eventTime, List list) {
        this.a = eventTime;
        this.b = list;
    }

    public final void a(Object obj) {
        ((AnalyticsListener) obj).a(this.a, this.b);
    }
}

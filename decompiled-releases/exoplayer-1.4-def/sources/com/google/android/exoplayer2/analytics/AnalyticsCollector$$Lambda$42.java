package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$42 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final boolean b;
    private final int c;

    AnalyticsCollector$$Lambda$42(AnalyticsListener.EventTime eventTime, boolean z, int i) {
        this.a = eventTime;
        this.b = z;
        this.c = i;
    }

    public final void a(Object obj) {
        ((AnalyticsListener) obj).a(this.a, this.b, this.c);
    }
}

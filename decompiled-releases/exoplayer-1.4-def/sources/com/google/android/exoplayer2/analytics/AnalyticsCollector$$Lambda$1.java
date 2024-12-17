package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ExoFlags;
import com.google.android.exoplayer2.util.ListenerSet;

public final /* synthetic */ class AnalyticsCollector$$Lambda$1 implements ListenerSet.IterationFinishedEvent {
    private final AnalyticsCollector a;

    public AnalyticsCollector$$Lambda$1(AnalyticsCollector analyticsCollector) {
        this.a = analyticsCollector;
    }

    public final void a(Object obj, ExoFlags exoFlags) {
        new AnalyticsListener.Events(exoFlags, this.a.b);
        ((AnalyticsListener) obj).w();
    }
}

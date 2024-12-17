package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$47 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final ExoPlaybackException b;

    AnalyticsCollector$$Lambda$47(AnalyticsListener.EventTime eventTime, ExoPlaybackException exoPlaybackException) {
        this.a = eventTime;
        this.b = exoPlaybackException;
    }

    public final void a(Object obj) {
        ((AnalyticsListener) obj).a(this.a, this.b);
    }
}

package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$49 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final PlaybackParameters b;

    AnalyticsCollector$$Lambda$49(AnalyticsListener.EventTime eventTime, PlaybackParameters playbackParameters) {
        this.a = eventTime;
        this.b = playbackParameters;
    }

    public final void a(Object obj) {
        ((AnalyticsListener) obj).a(this.a, this.b);
    }
}

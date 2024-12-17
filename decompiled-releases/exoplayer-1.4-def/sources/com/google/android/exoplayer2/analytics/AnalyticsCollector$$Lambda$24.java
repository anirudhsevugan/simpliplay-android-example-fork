package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;
import com.google.android.exoplayer2.video.VideoSize;

final /* synthetic */ class AnalyticsCollector$$Lambda$24 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final VideoSize b;

    AnalyticsCollector$$Lambda$24(AnalyticsListener.EventTime eventTime, VideoSize videoSize) {
        this.a = eventTime;
        this.b = videoSize;
    }

    public final void a(Object obj) {
        AnalyticsCollector.a(this.a, this.b, (AnalyticsListener) obj);
    }
}

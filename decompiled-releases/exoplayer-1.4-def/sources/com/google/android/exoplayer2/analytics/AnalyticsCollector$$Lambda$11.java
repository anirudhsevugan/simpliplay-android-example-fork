package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$11 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;

    AnalyticsCollector$$Lambda$11(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
        this.a = eventTime;
    }

    public final void a(Object obj) {
        AnalyticsCollector.f(this.a, (AnalyticsListener) obj);
    }
}

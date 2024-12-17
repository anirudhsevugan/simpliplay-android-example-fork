package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$50 implements ListenerSet.Event {
    AnalyticsCollector$$Lambda$50(AnalyticsListener.EventTime eventTime, MediaMetadata mediaMetadata) {
    }

    public final void a(Object obj) {
        ((AnalyticsListener) obj).f();
    }
}

package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$37 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final TrackSelectionArray b;

    AnalyticsCollector$$Lambda$37(AnalyticsListener.EventTime eventTime, TrackSelectionArray trackSelectionArray) {
        this.a = eventTime;
        this.b = trackSelectionArray;
    }

    public final void a(Object obj) {
        ((AnalyticsListener) obj).a(this.a, this.b);
    }
}

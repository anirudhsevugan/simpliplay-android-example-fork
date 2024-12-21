package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.util.ExoFlags;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$0 implements ListenerSet.IterationFinishedEvent {
    static final ListenerSet.IterationFinishedEvent a = new AnalyticsCollector$$Lambda$0();

    private AnalyticsCollector$$Lambda$0() {
    }

    public final void a(Object obj, ExoFlags exoFlags) {
        AnalyticsCollector.j();
    }
}

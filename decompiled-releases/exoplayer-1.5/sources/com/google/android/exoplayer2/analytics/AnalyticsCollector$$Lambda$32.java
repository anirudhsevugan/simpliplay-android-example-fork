package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;
import java.io.IOException;

final /* synthetic */ class AnalyticsCollector$$Lambda$32 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final IOException b;

    AnalyticsCollector$$Lambda$32(AnalyticsListener.EventTime eventTime, IOException iOException) {
        this.a = eventTime;
        this.b = iOException;
    }

    public final void a(Object obj) {
        ((AnalyticsListener) obj).a(this.a, this.b);
    }
}

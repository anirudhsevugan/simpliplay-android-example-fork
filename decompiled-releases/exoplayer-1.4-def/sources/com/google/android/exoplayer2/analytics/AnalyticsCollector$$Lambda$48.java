package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;

final /* synthetic */ class AnalyticsCollector$$Lambda$48 implements ListenerSet.Event {
    private final AnalyticsListener.EventTime a;
    private final int b;
    private final Player.PositionInfo c;
    private final Player.PositionInfo d;

    AnalyticsCollector$$Lambda$48(AnalyticsListener.EventTime eventTime, int i, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2) {
        this.a = eventTime;
        this.b = i;
        this.c = positionInfo;
        this.d = positionInfo2;
    }

    public final void a(Object obj) {
        AnalyticsCollector.a(this.a, this.b, this.c, this.d, (AnalyticsListener) obj);
    }
}

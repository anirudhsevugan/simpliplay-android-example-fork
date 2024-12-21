package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.util.NetworkTypeObserver;

final /* synthetic */ class DefaultBandwidthMeter$$Lambda$0 implements NetworkTypeObserver.Listener {
    private final DefaultBandwidthMeter a;

    DefaultBandwidthMeter$$Lambda$0(DefaultBandwidthMeter defaultBandwidthMeter) {
        this.a = defaultBandwidthMeter;
    }

    public final void a(int i) {
        this.a.a(i);
    }
}

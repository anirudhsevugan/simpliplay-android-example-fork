package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.upstream.BandwidthMeter;

final /* synthetic */ class BandwidthMeter$EventListener$EventDispatcher$$Lambda$0 implements Runnable {
    private final BandwidthMeter.EventListener.EventDispatcher.HandlerAndListener a;
    private final int b;
    private final long c;
    private final long d;

    BandwidthMeter$EventListener$EventDispatcher$$Lambda$0(BandwidthMeter.EventListener.EventDispatcher.HandlerAndListener handlerAndListener, int i, long j, long j2) {
        this.a = handlerAndListener;
        this.b = i;
        this.c = j;
        this.d = j2;
    }

    public final void run() {
        this.a.c.b(this.b, this.c, this.d);
    }
}

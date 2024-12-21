package com.google.android.exoplayer2.source.hls;

final /* synthetic */ class HlsSampleStreamWrapper$$Lambda$1 implements Runnable {
    private final HlsSampleStreamWrapper a;

    HlsSampleStreamWrapper$$Lambda$1(HlsSampleStreamWrapper hlsSampleStreamWrapper) {
        this.a = hlsSampleStreamWrapper;
    }

    public final void run() {
        HlsSampleStreamWrapper hlsSampleStreamWrapper = this.a;
        hlsSampleStreamWrapper.l = true;
        hlsSampleStreamWrapper.l();
    }
}

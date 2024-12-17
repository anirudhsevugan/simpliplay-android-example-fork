package com.google.android.exoplayer2.source.hls;

import com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper;

final /* synthetic */ class HlsSampleStreamWrapper$$Lambda$2 implements Runnable {
    private final HlsSampleStreamWrapper.Callback a;

    private HlsSampleStreamWrapper$$Lambda$2(HlsSampleStreamWrapper.Callback callback) {
        this.a = callback;
    }

    static Runnable a(HlsSampleStreamWrapper.Callback callback) {
        return new HlsSampleStreamWrapper$$Lambda$2(callback);
    }

    public final void run() {
        this.a.g();
    }
}

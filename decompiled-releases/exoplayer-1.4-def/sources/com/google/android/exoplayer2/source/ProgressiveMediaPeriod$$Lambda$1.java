package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.util.Assertions;

final /* synthetic */ class ProgressiveMediaPeriod$$Lambda$1 implements Runnable {
    private final ProgressiveMediaPeriod a;

    ProgressiveMediaPeriod$$Lambda$1(ProgressiveMediaPeriod progressiveMediaPeriod) {
        this.a = progressiveMediaPeriod;
    }

    public final void run() {
        ProgressiveMediaPeriod progressiveMediaPeriod = this.a;
        if (!progressiveMediaPeriod.o) {
            ((MediaPeriod.Callback) Assertions.b((Object) progressiveMediaPeriod.e)).a(progressiveMediaPeriod);
        }
    }
}

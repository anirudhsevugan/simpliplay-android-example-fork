package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.extractor.SeekMap;

final /* synthetic */ class ProgressiveMediaPeriod$$Lambda$2 implements Runnable {
    private final ProgressiveMediaPeriod a;
    private final SeekMap b;

    ProgressiveMediaPeriod$$Lambda$2(ProgressiveMediaPeriod progressiveMediaPeriod, SeekMap seekMap) {
        this.a = progressiveMediaPeriod;
        this.b = seekMap;
    }

    public final void run() {
        ProgressiveMediaPeriod progressiveMediaPeriod = this.a;
        SeekMap seekMap = this.b;
        progressiveMediaPeriod.i = progressiveMediaPeriod.f == null ? seekMap : new SeekMap.Unseekable(-9223372036854775807L);
        progressiveMediaPeriod.j = seekMap.b();
        int i = 1;
        progressiveMediaPeriod.k = progressiveMediaPeriod.m == -1 && seekMap.b() == -9223372036854775807L;
        if (progressiveMediaPeriod.k) {
            i = 7;
        }
        progressiveMediaPeriod.l = i;
        progressiveMediaPeriod.b.a(progressiveMediaPeriod.j, seekMap.a(), progressiveMediaPeriod.k);
        if (!progressiveMediaPeriod.h) {
            progressiveMediaPeriod.k();
        }
    }
}

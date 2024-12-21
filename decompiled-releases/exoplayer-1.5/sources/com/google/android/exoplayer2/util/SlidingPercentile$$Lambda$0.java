package com.google.android.exoplayer2.util;

import com.google.android.exoplayer2.util.SlidingPercentile;
import java.util.Comparator;

final /* synthetic */ class SlidingPercentile$$Lambda$0 implements Comparator {
    static final Comparator a = new SlidingPercentile$$Lambda$0();

    private SlidingPercentile$$Lambda$0() {
    }

    public final int compare(Object obj, Object obj2) {
        return SlidingPercentile.b((SlidingPercentile.Sample) obj, (SlidingPercentile.Sample) obj2);
    }
}

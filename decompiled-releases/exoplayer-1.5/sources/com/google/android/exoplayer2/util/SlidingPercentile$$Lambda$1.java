package com.google.android.exoplayer2.util;

import com.google.android.exoplayer2.util.SlidingPercentile;
import java.util.Comparator;

final /* synthetic */ class SlidingPercentile$$Lambda$1 implements Comparator {
    static final Comparator a = new SlidingPercentile$$Lambda$1();

    private SlidingPercentile$$Lambda$1() {
    }

    public final int compare(Object obj, Object obj2) {
        return Float.compare(((SlidingPercentile.Sample) obj).c, ((SlidingPercentile.Sample) obj2).c);
    }
}

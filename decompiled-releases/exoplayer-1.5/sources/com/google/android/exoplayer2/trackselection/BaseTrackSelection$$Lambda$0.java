package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.Format;
import java.util.Comparator;

final /* synthetic */ class BaseTrackSelection$$Lambda$0 implements Comparator {
    static final Comparator a = new BaseTrackSelection$$Lambda$0();

    private BaseTrackSelection$$Lambda$0() {
    }

    public final int compare(Object obj, Object obj2) {
        return BaseTrackSelection.a((Format) obj, (Format) obj2);
    }
}

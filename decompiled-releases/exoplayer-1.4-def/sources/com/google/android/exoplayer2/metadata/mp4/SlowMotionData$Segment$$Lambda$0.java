package com.google.android.exoplayer2.metadata.mp4;

import com.dreamers.exoplayercore.repack.C0052by;
import com.google.android.exoplayer2.metadata.mp4.SlowMotionData;
import java.util.Comparator;

final /* synthetic */ class SlowMotionData$Segment$$Lambda$0 implements Comparator {
    static final Comparator a = new SlowMotionData$Segment$$Lambda$0();

    private SlowMotionData$Segment$$Lambda$0() {
    }

    public final int compare(Object obj, Object obj2) {
        return C0052by.a().a(((SlowMotionData.Segment) obj).a, ((SlowMotionData.Segment) obj2).a).a(((SlowMotionData.Segment) obj).b, ((SlowMotionData.Segment) obj2).b).a(((SlowMotionData.Segment) obj).c, ((SlowMotionData.Segment) obj2).c).b();
    }
}

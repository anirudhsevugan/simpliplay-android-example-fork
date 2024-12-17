package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.util.Consumer;

final /* synthetic */ class SampleQueue$$Lambda$0 implements Consumer {
    static final Consumer a = new SampleQueue$$Lambda$0();

    private SampleQueue$$Lambda$0() {
    }

    public final void a(Object obj) {
        ((SampleQueue.SharedSampleMetadata) obj).b.a();
    }
}

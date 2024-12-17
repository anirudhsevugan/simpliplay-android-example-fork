package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;

final /* synthetic */ class CompositeMediaSource$$Lambda$0 implements MediaSource.MediaSourceCaller {
    private final CompositeMediaSource a;
    private final Object b;

    CompositeMediaSource$$Lambda$0(CompositeMediaSource compositeMediaSource, Object obj) {
        this.a = compositeMediaSource;
        this.b = obj;
    }

    public final void a(MediaSource mediaSource, Timeline timeline) {
        this.a.b(this.b, mediaSource, timeline);
    }
}

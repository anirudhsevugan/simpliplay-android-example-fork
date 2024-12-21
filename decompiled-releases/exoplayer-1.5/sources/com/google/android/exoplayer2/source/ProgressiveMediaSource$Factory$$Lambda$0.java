package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ProgressiveMediaExtractor;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;

final /* synthetic */ class ProgressiveMediaSource$Factory$$Lambda$0 implements ProgressiveMediaExtractor.Factory {
    private final ExtractorsFactory a;

    ProgressiveMediaSource$Factory$$Lambda$0(ExtractorsFactory extractorsFactory) {
        this.a = extractorsFactory;
    }

    public final ProgressiveMediaExtractor a() {
        return ProgressiveMediaSource.Factory.a(this.a);
    }
}

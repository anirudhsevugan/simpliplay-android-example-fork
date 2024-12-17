package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$Lambda$8 implements Runnable {
    private final VideoRendererEventListener.EventDispatcher a;
    private final DecoderCounters b;

    VideoRendererEventListener$EventDispatcher$$Lambda$8(VideoRendererEventListener.EventDispatcher eventDispatcher, DecoderCounters decoderCounters) {
        this.a = eventDispatcher;
        this.b = decoderCounters;
    }

    public final void run() {
        VideoRendererEventListener.EventDispatcher eventDispatcher = this.a;
        ((VideoRendererEventListener) Util.a((Object) eventDispatcher.b)).b(this.b);
    }
}

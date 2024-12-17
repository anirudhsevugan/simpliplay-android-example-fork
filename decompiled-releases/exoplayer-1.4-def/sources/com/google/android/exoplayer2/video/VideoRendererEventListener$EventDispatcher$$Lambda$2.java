package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$Lambda$2 implements Runnable {
    private final VideoRendererEventListener.EventDispatcher a;
    private final Format b;
    private final DecoderReuseEvaluation c;

    VideoRendererEventListener$EventDispatcher$$Lambda$2(VideoRendererEventListener.EventDispatcher eventDispatcher, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
        this.a = eventDispatcher;
        this.b = format;
        this.c = decoderReuseEvaluation;
    }

    public final void run() {
        VideoRendererEventListener.EventDispatcher eventDispatcher = this.a;
        Format format = this.b;
        DecoderReuseEvaluation decoderReuseEvaluation = this.c;
        ((VideoRendererEventListener) Util.a((Object) eventDispatcher.b)).h();
        ((VideoRendererEventListener) Util.a((Object) eventDispatcher.b)).a(format, decoderReuseEvaluation);
    }
}

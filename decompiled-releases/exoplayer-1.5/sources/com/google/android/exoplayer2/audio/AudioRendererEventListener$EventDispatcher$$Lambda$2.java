package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.util.Util;

final /* synthetic */ class AudioRendererEventListener$EventDispatcher$$Lambda$2 implements Runnable {
    private final AudioRendererEventListener.EventDispatcher a;
    private final Format b;
    private final DecoderReuseEvaluation c;

    AudioRendererEventListener$EventDispatcher$$Lambda$2(AudioRendererEventListener.EventDispatcher eventDispatcher, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
        this.a = eventDispatcher;
        this.b = format;
        this.c = decoderReuseEvaluation;
    }

    public final void run() {
        AudioRendererEventListener.EventDispatcher eventDispatcher = this.a;
        Format format = this.b;
        DecoderReuseEvaluation decoderReuseEvaluation = this.c;
        ((AudioRendererEventListener) Util.a((Object) eventDispatcher.b)).g();
        ((AudioRendererEventListener) Util.a((Object) eventDispatcher.b)).b(format, decoderReuseEvaluation);
    }
}

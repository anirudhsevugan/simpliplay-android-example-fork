package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.util.Util;

final /* synthetic */ class AudioRendererEventListener$EventDispatcher$$Lambda$6 implements Runnable {
    private final AudioRendererEventListener.EventDispatcher a;
    private final DecoderCounters b;

    AudioRendererEventListener$EventDispatcher$$Lambda$6(AudioRendererEventListener.EventDispatcher eventDispatcher, DecoderCounters decoderCounters) {
        this.a = eventDispatcher;
        this.b = decoderCounters;
    }

    public final void run() {
        AudioRendererEventListener.EventDispatcher eventDispatcher = this.a;
        ((AudioRendererEventListener) Util.a((Object) eventDispatcher.b)).d(this.b);
    }
}

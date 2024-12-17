package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.util.Util;

final /* synthetic */ class AudioRendererEventListener$EventDispatcher$$Lambda$8 implements Runnable {
    private final AudioRendererEventListener.EventDispatcher a;
    private final Exception b;

    AudioRendererEventListener$EventDispatcher$$Lambda$8(AudioRendererEventListener.EventDispatcher eventDispatcher, Exception exc) {
        this.a = eventDispatcher;
        this.b = exc;
    }

    public final void run() {
        AudioRendererEventListener.EventDispatcher eventDispatcher = this.a;
        ((AudioRendererEventListener) Util.a((Object) eventDispatcher.b)).b(this.b);
    }
}

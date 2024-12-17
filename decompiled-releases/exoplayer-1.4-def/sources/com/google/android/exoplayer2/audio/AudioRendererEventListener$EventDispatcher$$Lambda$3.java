package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.util.Util;

final /* synthetic */ class AudioRendererEventListener$EventDispatcher$$Lambda$3 implements Runnable {
    private final AudioRendererEventListener.EventDispatcher a;
    private final long b;

    AudioRendererEventListener$EventDispatcher$$Lambda$3(AudioRendererEventListener.EventDispatcher eventDispatcher, long j) {
        this.a = eventDispatcher;
        this.b = j;
    }

    public final void run() {
        AudioRendererEventListener.EventDispatcher eventDispatcher = this.a;
        ((AudioRendererEventListener) Util.a((Object) eventDispatcher.b)).a(this.b);
    }
}

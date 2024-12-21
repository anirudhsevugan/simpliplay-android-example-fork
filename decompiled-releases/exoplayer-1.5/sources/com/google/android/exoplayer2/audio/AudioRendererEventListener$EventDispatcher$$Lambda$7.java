package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.util.Util;

final /* synthetic */ class AudioRendererEventListener$EventDispatcher$$Lambda$7 implements Runnable {
    private final AudioRendererEventListener.EventDispatcher a;
    private final boolean b;

    AudioRendererEventListener$EventDispatcher$$Lambda$7(AudioRendererEventListener.EventDispatcher eventDispatcher, boolean z) {
        this.a = eventDispatcher;
        this.b = z;
    }

    public final void run() {
        AudioRendererEventListener.EventDispatcher eventDispatcher = this.a;
        ((AudioRendererEventListener) Util.a((Object) eventDispatcher.b)).onSkipSilenceEnabledChanged(this.b);
    }
}

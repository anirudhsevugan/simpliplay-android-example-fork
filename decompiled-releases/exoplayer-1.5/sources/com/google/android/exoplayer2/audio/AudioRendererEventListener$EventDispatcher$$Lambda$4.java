package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.util.Util;

final /* synthetic */ class AudioRendererEventListener$EventDispatcher$$Lambda$4 implements Runnable {
    private final AudioRendererEventListener.EventDispatcher a;
    private final int b;
    private final long c;
    private final long d;

    AudioRendererEventListener$EventDispatcher$$Lambda$4(AudioRendererEventListener.EventDispatcher eventDispatcher, int i, long j, long j2) {
        this.a = eventDispatcher;
        this.b = i;
        this.c = j;
        this.d = j2;
    }

    public final void run() {
        AudioRendererEventListener.EventDispatcher eventDispatcher = this.a;
        ((AudioRendererEventListener) Util.a((Object) eventDispatcher.b)).a(this.b, this.c, this.d);
    }
}

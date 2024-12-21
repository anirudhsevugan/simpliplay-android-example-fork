package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$Lambda$4 implements Runnable {
    private final VideoRendererEventListener.EventDispatcher a;
    private final long b;
    private final int c;

    VideoRendererEventListener$EventDispatcher$$Lambda$4(VideoRendererEventListener.EventDispatcher eventDispatcher, long j, int i) {
        this.a = eventDispatcher;
        this.b = j;
        this.c = i;
    }

    public final void run() {
        VideoRendererEventListener.EventDispatcher eventDispatcher = this.a;
        ((VideoRendererEventListener) Util.a((Object) eventDispatcher.b)).a(this.b, this.c);
    }
}

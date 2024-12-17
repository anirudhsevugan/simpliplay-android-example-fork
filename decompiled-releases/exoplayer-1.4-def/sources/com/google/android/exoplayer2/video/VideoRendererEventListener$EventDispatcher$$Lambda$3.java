package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$Lambda$3 implements Runnable {
    private final VideoRendererEventListener.EventDispatcher a;
    private final int b;
    private final long c;

    VideoRendererEventListener$EventDispatcher$$Lambda$3(VideoRendererEventListener.EventDispatcher eventDispatcher, int i, long j) {
        this.a = eventDispatcher;
        this.b = i;
        this.c = j;
    }

    public final void run() {
        VideoRendererEventListener.EventDispatcher eventDispatcher = this.a;
        ((VideoRendererEventListener) Util.a((Object) eventDispatcher.b)).a(this.b, this.c);
    }
}

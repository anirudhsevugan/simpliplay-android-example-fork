package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$Lambda$6 implements Runnable {
    private final VideoRendererEventListener.EventDispatcher a;
    private final Object b;
    private final long c;

    VideoRendererEventListener$EventDispatcher$$Lambda$6(VideoRendererEventListener.EventDispatcher eventDispatcher, Object obj, long j) {
        this.a = eventDispatcher;
        this.b = obj;
        this.c = j;
    }

    public final void run() {
        VideoRendererEventListener.EventDispatcher eventDispatcher = this.a;
        ((VideoRendererEventListener) Util.a((Object) eventDispatcher.b)).a(this.b, this.c);
    }
}

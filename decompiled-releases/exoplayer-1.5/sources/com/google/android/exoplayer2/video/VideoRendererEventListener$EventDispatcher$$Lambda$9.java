package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$Lambda$9 implements Runnable {
    private final VideoRendererEventListener.EventDispatcher a;
    private final Exception b;

    VideoRendererEventListener$EventDispatcher$$Lambda$9(VideoRendererEventListener.EventDispatcher eventDispatcher, Exception exc) {
        this.a = eventDispatcher;
        this.b = exc;
    }

    public final void run() {
        VideoRendererEventListener.EventDispatcher eventDispatcher = this.a;
        ((VideoRendererEventListener) Util.a((Object) eventDispatcher.b)).a(this.b);
    }
}

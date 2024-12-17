package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$Lambda$5 implements Runnable {
    private final VideoRendererEventListener.EventDispatcher a;
    private final VideoSize b;

    VideoRendererEventListener$EventDispatcher$$Lambda$5(VideoRendererEventListener.EventDispatcher eventDispatcher, VideoSize videoSize) {
        this.a = eventDispatcher;
        this.b = videoSize;
    }

    public final void run() {
        VideoRendererEventListener.EventDispatcher eventDispatcher = this.a;
        ((VideoRendererEventListener) Util.a((Object) eventDispatcher.b)).onVideoSizeChanged(this.b);
    }
}

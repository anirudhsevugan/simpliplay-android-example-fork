package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

final /* synthetic */ class VideoRendererEventListener$EventDispatcher$$Lambda$1 implements Runnable {
    private final VideoRendererEventListener.EventDispatcher a;
    private final String b;
    private final long c;
    private final long d;

    VideoRendererEventListener$EventDispatcher$$Lambda$1(VideoRendererEventListener.EventDispatcher eventDispatcher, String str, long j, long j2) {
        this.a = eventDispatcher;
        this.b = str;
        this.c = j;
        this.d = j2;
    }

    public final void run() {
        VideoRendererEventListener.EventDispatcher eventDispatcher = this.a;
        ((VideoRendererEventListener) Util.a((Object) eventDispatcher.b)).a(this.b, this.c, this.d);
    }
}

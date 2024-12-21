package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.source.MediaSourceEventListener;

final /* synthetic */ class MediaSourceEventListener$EventDispatcher$$Lambda$2 implements Runnable {
    private final MediaSourceEventListener.EventDispatcher a;
    private final MediaSourceEventListener b;
    private final LoadEventInfo c;
    private final MediaLoadData d;

    MediaSourceEventListener$EventDispatcher$$Lambda$2(MediaSourceEventListener.EventDispatcher eventDispatcher, MediaSourceEventListener mediaSourceEventListener, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        this.a = eventDispatcher;
        this.b = mediaSourceEventListener;
        this.c = loadEventInfo;
        this.d = mediaLoadData;
    }

    public final void run() {
        MediaSourceEventListener.EventDispatcher eventDispatcher = this.a;
        this.b.c(eventDispatcher.a, eventDispatcher.b, this.c, this.d);
    }
}

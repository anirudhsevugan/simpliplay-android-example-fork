package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.source.MediaSourceEventListener;

final /* synthetic */ class MediaSourceEventListener$EventDispatcher$$Lambda$5 implements Runnable {
    private final MediaSourceEventListener.EventDispatcher a;
    private final MediaSourceEventListener b;
    private final MediaLoadData c;

    MediaSourceEventListener$EventDispatcher$$Lambda$5(MediaSourceEventListener.EventDispatcher eventDispatcher, MediaSourceEventListener mediaSourceEventListener, MediaLoadData mediaLoadData) {
        this.a = eventDispatcher;
        this.b = mediaSourceEventListener;
        this.c = mediaLoadData;
    }

    public final void run() {
        MediaSourceEventListener.EventDispatcher eventDispatcher = this.a;
        this.b.b(eventDispatcher.a, eventDispatcher.b, this.c);
    }
}

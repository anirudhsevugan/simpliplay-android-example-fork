package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;

final /* synthetic */ class MediaSourceEventListener$EventDispatcher$$Lambda$4 implements Runnable {
    private final MediaSourceEventListener.EventDispatcher a;
    private final MediaSourceEventListener b;
    private final MediaSource.MediaPeriodId c;
    private final MediaLoadData d;

    MediaSourceEventListener$EventDispatcher$$Lambda$4(MediaSourceEventListener.EventDispatcher eventDispatcher, MediaSourceEventListener mediaSourceEventListener, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
        this.a = eventDispatcher;
        this.b = mediaSourceEventListener;
        this.c = mediaPeriodId;
        this.d = mediaLoadData;
    }

    public final void run() {
        MediaSourceEventListener.EventDispatcher eventDispatcher = this.a;
        this.b.a(eventDispatcher.a, this.c, this.d);
    }
}

package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.source.MediaSourceEventListener;
import java.io.IOException;

final /* synthetic */ class MediaSourceEventListener$EventDispatcher$$Lambda$3 implements Runnable {
    private final MediaSourceEventListener.EventDispatcher a;
    private final MediaSourceEventListener b;
    private final LoadEventInfo c;
    private final MediaLoadData d;
    private final IOException e;
    private final boolean f;

    MediaSourceEventListener$EventDispatcher$$Lambda$3(MediaSourceEventListener.EventDispatcher eventDispatcher, MediaSourceEventListener mediaSourceEventListener, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        this.a = eventDispatcher;
        this.b = mediaSourceEventListener;
        this.c = loadEventInfo;
        this.d = mediaLoadData;
        this.e = iOException;
        this.f = z;
    }

    public final void run() {
        MediaSourceEventListener.EventDispatcher eventDispatcher = this.a;
        this.b.a(eventDispatcher.a, eventDispatcher.b, this.c, this.d, this.e, this.f);
    }
}

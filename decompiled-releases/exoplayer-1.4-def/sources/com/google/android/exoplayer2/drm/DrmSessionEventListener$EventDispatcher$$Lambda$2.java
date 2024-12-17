package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.DrmSessionEventListener;

final /* synthetic */ class DrmSessionEventListener$EventDispatcher$$Lambda$2 implements Runnable {
    private final DrmSessionEventListener.EventDispatcher a;
    private final DrmSessionEventListener b;
    private final Exception c;

    DrmSessionEventListener$EventDispatcher$$Lambda$2(DrmSessionEventListener.EventDispatcher eventDispatcher, DrmSessionEventListener drmSessionEventListener, Exception exc) {
        this.a = eventDispatcher;
        this.b = drmSessionEventListener;
        this.c = exc;
    }

    public final void run() {
        DrmSessionEventListener.EventDispatcher eventDispatcher = this.a;
        this.b.a(eventDispatcher.a, eventDispatcher.b, this.c);
    }
}

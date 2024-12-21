package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.DrmSessionEventListener;

final /* synthetic */ class DrmSessionEventListener$EventDispatcher$$Lambda$5 implements Runnable {
    private final DrmSessionEventListener.EventDispatcher a;
    private final DrmSessionEventListener b;

    DrmSessionEventListener$EventDispatcher$$Lambda$5(DrmSessionEventListener.EventDispatcher eventDispatcher, DrmSessionEventListener drmSessionEventListener) {
        this.a = eventDispatcher;
        this.b = drmSessionEventListener;
    }

    public final void run() {
        DrmSessionEventListener.EventDispatcher eventDispatcher = this.a;
        this.b.c(eventDispatcher.a, eventDispatcher.b);
    }
}

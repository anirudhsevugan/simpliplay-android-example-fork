package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.DrmSessionEventListener;

final /* synthetic */ class DrmSessionEventListener$EventDispatcher$$Lambda$0 implements Runnable {
    private final DrmSessionEventListener.EventDispatcher a;
    private final DrmSessionEventListener b;
    private final int c;

    DrmSessionEventListener$EventDispatcher$$Lambda$0(DrmSessionEventListener.EventDispatcher eventDispatcher, DrmSessionEventListener drmSessionEventListener, int i) {
        this.a = eventDispatcher;
        this.b = drmSessionEventListener;
        this.c = i;
    }

    public final void run() {
        DrmSessionEventListener.EventDispatcher eventDispatcher = this.a;
        DrmSessionEventListener drmSessionEventListener = this.b;
        int i = this.c;
        drmSessionEventListener.a();
        drmSessionEventListener.a(eventDispatcher.a, eventDispatcher.b, i);
    }
}

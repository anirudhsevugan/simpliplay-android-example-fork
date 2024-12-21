package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.DrmSessionEventListener;

final /* synthetic */ class DefaultDrmSessionManager$ReferenceCountListenerImpl$$Lambda$0 implements Runnable {
    private final DefaultDrmSession a;

    DefaultDrmSessionManager$ReferenceCountListenerImpl$$Lambda$0(DefaultDrmSession defaultDrmSession) {
        this.a = defaultDrmSession;
    }

    public final void run() {
        this.a.b((DrmSessionEventListener.EventDispatcher) null);
    }
}

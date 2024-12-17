package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;

final /* synthetic */ class DefaultDrmSessionManager$PreacquiredSessionReference$$Lambda$1 implements Runnable {
    private final DefaultDrmSessionManager.PreacquiredSessionReference a;

    DefaultDrmSessionManager$PreacquiredSessionReference$$Lambda$1(DefaultDrmSessionManager.PreacquiredSessionReference preacquiredSessionReference) {
        this.a = preacquiredSessionReference;
    }

    public final void run() {
        DefaultDrmSessionManager.PreacquiredSessionReference preacquiredSessionReference = this.a;
        if (!preacquiredSessionReference.c) {
            if (preacquiredSessionReference.b != null) {
                preacquiredSessionReference.b.b(preacquiredSessionReference.a);
            }
            DefaultDrmSessionManager.this.q.remove(preacquiredSessionReference);
            preacquiredSessionReference.c = true;
        }
    }
}

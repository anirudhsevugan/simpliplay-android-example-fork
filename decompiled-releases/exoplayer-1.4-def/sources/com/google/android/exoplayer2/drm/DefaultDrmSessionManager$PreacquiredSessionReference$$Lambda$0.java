package com.google.android.exoplayer2.drm;

import android.os.Looper;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.util.Assertions;

final /* synthetic */ class DefaultDrmSessionManager$PreacquiredSessionReference$$Lambda$0 implements Runnable {
    private final DefaultDrmSessionManager.PreacquiredSessionReference a;
    private final Format b;

    DefaultDrmSessionManager$PreacquiredSessionReference$$Lambda$0(DefaultDrmSessionManager.PreacquiredSessionReference preacquiredSessionReference, Format format) {
        this.a = preacquiredSessionReference;
        this.b = format;
    }

    public final void run() {
        DefaultDrmSessionManager.PreacquiredSessionReference preacquiredSessionReference = this.a;
        Format format = this.b;
        if (DefaultDrmSessionManager.this.s != 0 && !preacquiredSessionReference.c) {
            preacquiredSessionReference.b = DefaultDrmSessionManager.this.a((Looper) Assertions.b((Object) DefaultDrmSessionManager.this.w), preacquiredSessionReference.a, format, false);
            DefaultDrmSessionManager.this.q.add(preacquiredSessionReference);
        }
    }
}

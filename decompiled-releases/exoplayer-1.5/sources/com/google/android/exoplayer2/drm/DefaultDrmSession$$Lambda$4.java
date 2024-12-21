package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.util.Consumer;

final /* synthetic */ class DefaultDrmSession$$Lambda$4 implements Consumer {
    private final Exception a;

    DefaultDrmSession$$Lambda$4(Exception exc) {
        this.a = exc;
    }

    public final void a(Object obj) {
        ((DrmSessionEventListener.EventDispatcher) obj).a(this.a);
    }
}

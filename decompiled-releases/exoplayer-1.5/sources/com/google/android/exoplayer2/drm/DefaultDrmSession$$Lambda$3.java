package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.util.Consumer;

final /* synthetic */ class DefaultDrmSession$$Lambda$3 implements Consumer {
    static final Consumer a = new DefaultDrmSession$$Lambda$3();

    private DefaultDrmSession$$Lambda$3() {
    }

    public final void a(Object obj) {
        ((DrmSessionEventListener.EventDispatcher) obj).a();
    }
}

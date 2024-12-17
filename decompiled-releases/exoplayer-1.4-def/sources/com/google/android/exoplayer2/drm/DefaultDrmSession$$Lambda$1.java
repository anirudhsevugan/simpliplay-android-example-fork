package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.util.Consumer;

final /* synthetic */ class DefaultDrmSession$$Lambda$1 implements Consumer {
    static final Consumer a = new DefaultDrmSession$$Lambda$1();

    private DefaultDrmSession$$Lambda$1() {
    }

    public final void a(Object obj) {
        ((DrmSessionEventListener.EventDispatcher) obj).b();
    }
}

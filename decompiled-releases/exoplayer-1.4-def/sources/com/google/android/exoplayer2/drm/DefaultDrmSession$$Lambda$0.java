package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.util.Consumer;

final /* synthetic */ class DefaultDrmSession$$Lambda$0 implements Consumer {
    private final int a;

    DefaultDrmSession$$Lambda$0(int i) {
        this.a = i;
    }

    public final void a(Object obj) {
        ((DrmSessionEventListener.EventDispatcher) obj).a(this.a);
    }
}

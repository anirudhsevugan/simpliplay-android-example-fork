package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.DrmSessionEventListener;

public abstract /* synthetic */ class DrmSession$$CC {
    public static void a(DrmSession drmSession, DrmSession drmSession2) {
        if (drmSession != drmSession2) {
            if (drmSession2 != null) {
                drmSession2.a((DrmSessionEventListener.EventDispatcher) null);
            }
            if (drmSession != null) {
                drmSession.b((DrmSessionEventListener.EventDispatcher) null);
            }
        }
    }
}

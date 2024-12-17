package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.util.Assertions;
import java.util.Map;
import java.util.UUID;

public final class ErrorStateDrmSession implements DrmSession {
    private final DrmSession.DrmSessionException a;

    public ErrorStateDrmSession(DrmSession.DrmSessionException drmSessionException) {
        this.a = (DrmSession.DrmSessionException) Assertions.b((Object) drmSessionException);
    }

    public final void a(DrmSessionEventListener.EventDispatcher eventDispatcher) {
    }

    public final int b() {
        return 1;
    }

    public final void b(DrmSessionEventListener.EventDispatcher eventDispatcher) {
    }

    public final boolean c() {
        return false;
    }

    public final DrmSession.DrmSessionException d() {
        return this.a;
    }

    public final UUID e() {
        return C.a;
    }

    public final ExoMediaCrypto f() {
        return null;
    }

    public final Map g() {
        return null;
    }
}

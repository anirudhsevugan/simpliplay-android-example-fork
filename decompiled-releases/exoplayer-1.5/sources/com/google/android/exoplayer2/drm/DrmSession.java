package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public interface DrmSession {

    public class DrmSessionException extends IOException {
        public DrmSessionException(Throwable th) {
            super(th);
        }
    }

    void a(DrmSessionEventListener.EventDispatcher eventDispatcher);

    int b();

    void b(DrmSessionEventListener.EventDispatcher eventDispatcher);

    boolean c();

    DrmSessionException d();

    UUID e();

    ExoMediaCrypto f();

    Map g();
}

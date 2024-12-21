package com.google.android.exoplayer2.drm;

import java.io.IOException;

public final class MediaDrmCallbackException extends IOException {
    public MediaDrmCallbackException(Throwable th) {
        super(th);
    }
}

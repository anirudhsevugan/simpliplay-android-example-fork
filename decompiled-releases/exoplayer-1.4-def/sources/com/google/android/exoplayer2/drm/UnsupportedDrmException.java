package com.google.android.exoplayer2.drm;

public final class UnsupportedDrmException extends Exception {
    public UnsupportedDrmException() {
    }

    public UnsupportedDrmException(Exception exc) {
        super(exc);
    }
}

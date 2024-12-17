package com.google.android.exoplayer2.drm;

import android.media.MediaDrmException;
import com.google.android.exoplayer2.drm.ExoMediaDrm;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DummyExoMediaDrm implements ExoMediaDrm {
    public final ExoMediaDrm.KeyRequest a(byte[] bArr, List list, int i, HashMap hashMap) {
        throw new IllegalStateException();
    }

    public final void a(ExoMediaDrm.OnEventListener onEventListener) {
    }

    public final void a(byte[] bArr) {
    }

    public final byte[] a() {
        throw new MediaDrmException("Attempting to open a session using a dummy ExoMediaDrm.");
    }

    public final byte[] a(byte[] bArr, byte[] bArr2) {
        throw new IllegalStateException();
    }

    public final ExoMediaDrm.ProvisionRequest b() {
        throw new IllegalStateException();
    }

    public final void b(byte[] bArr) {
        throw new IllegalStateException();
    }

    public final void b(byte[] bArr, byte[] bArr2) {
        throw new IllegalStateException();
    }

    public final Map c(byte[] bArr) {
        throw new IllegalStateException();
    }

    public final void c() {
    }

    public final ExoMediaCrypto d(byte[] bArr) {
        throw new IllegalStateException();
    }

    public final Class d() {
        return UnsupportedMediaCrypto.class;
    }
}

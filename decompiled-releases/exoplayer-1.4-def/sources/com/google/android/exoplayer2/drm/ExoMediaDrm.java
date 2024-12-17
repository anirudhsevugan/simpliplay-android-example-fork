package com.google.android.exoplayer2.drm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ExoMediaDrm {

    public final class KeyRequest {
        final byte[] a;
        final String b;

        public KeyRequest(byte[] bArr, String str) {
            this.a = bArr;
            this.b = str;
        }
    }

    public interface OnEventListener {
        void a(byte[] bArr, int i);
    }

    public interface Provider {
        ExoMediaDrm a(UUID uuid);
    }

    public final class ProvisionRequest {
        final byte[] a;
        final String b;

        public ProvisionRequest(byte[] bArr, String str) {
            this.a = bArr;
            this.b = str;
        }
    }

    KeyRequest a(byte[] bArr, List list, int i, HashMap hashMap);

    void a(OnEventListener onEventListener);

    void a(byte[] bArr);

    byte[] a();

    byte[] a(byte[] bArr, byte[] bArr2);

    ProvisionRequest b();

    void b(byte[] bArr);

    void b(byte[] bArr, byte[] bArr2);

    Map c(byte[] bArr);

    void c();

    ExoMediaCrypto d(byte[] bArr);

    Class d();
}

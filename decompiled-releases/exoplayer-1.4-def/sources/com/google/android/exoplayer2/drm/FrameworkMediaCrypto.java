package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.util.Util;
import java.util.UUID;

public final class FrameworkMediaCrypto implements ExoMediaCrypto {
    public static final boolean a = ("Amazon".equals(Util.c) && ("AFTM".equals(Util.d) || "AFTB".equals(Util.d)));
    public final UUID b;
    public final byte[] c;
    public final boolean d;

    public FrameworkMediaCrypto(UUID uuid, byte[] bArr, boolean z) {
        this.b = uuid;
        this.c = bArr;
        this.d = z;
    }
}

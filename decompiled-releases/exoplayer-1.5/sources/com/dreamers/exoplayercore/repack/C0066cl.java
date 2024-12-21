package com.dreamers.exoplayercore.repack;

import java.io.Serializable;
import java.util.ArrayList;

/* renamed from: com.dreamers.exoplayercore.repack.cl  reason: case insensitive filesystem */
final class C0066cl implements aK, Serializable {
    private final int a;

    C0066cl(int i) {
        this.a = C0000a.a(i, "expectedValuesPerKey");
    }

    public final /* synthetic */ Object a() {
        return new ArrayList(this.a);
    }
}

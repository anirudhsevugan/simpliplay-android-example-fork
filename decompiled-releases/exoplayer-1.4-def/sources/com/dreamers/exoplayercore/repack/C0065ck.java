package com.dreamers.exoplayercore.repack;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/* renamed from: com.dreamers.exoplayercore.repack.ck  reason: case insensitive filesystem */
public final class C0065ck extends C0068cn {
    private /* synthetic */ Comparator a;

    public C0065ck(Comparator comparator) {
        this.a = comparator;
    }

    /* access modifiers changed from: package-private */
    public final Map a() {
        return new TreeMap(this.a);
    }
}

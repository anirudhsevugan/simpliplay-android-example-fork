package com.dreamers.exoplayercore.repack;

import java.util.AbstractMap;

/* renamed from: com.dreamers.exoplayercore.repack.cy  reason: case insensitive filesystem */
final class C0079cy extends bG {
    private /* synthetic */ C0078cx a;

    C0079cy(C0078cx cxVar) {
        this.a = cxVar;
    }

    public final boolean f() {
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        C0000a.b(i, this.a.c);
        int i2 = i * 2;
        Object obj = this.a.b[C0078cx.i() + i2];
        Object[] b = this.a.b;
        C0078cx.i();
        return new AbstractMap.SimpleImmutableEntry(obj, b[i2 + 1]);
    }

    public final int size() {
        return this.a.c;
    }
}

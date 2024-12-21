package com.dreamers.exoplayercore.repack;

import java.util.AbstractCollection;
import java.util.Iterator;

/* renamed from: com.dreamers.exoplayercore.repack.bm  reason: case insensitive filesystem */
final class C0040bm extends AbstractCollection {
    private /* synthetic */ C0037bj a;

    C0040bm(C0037bj bjVar) {
        this.a = bjVar;
    }

    public final void clear() {
        this.a.e();
    }

    public final boolean contains(Object obj) {
        return this.a.b(obj);
    }

    public final Iterator iterator() {
        return this.a.j();
    }

    public final int size() {
        return this.a.d();
    }
}

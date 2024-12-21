package com.dreamers.exoplayercore.repack;

import java.util.Iterator;

final class bT extends bC {
    private final transient bO a;

    bT(bO bOVar) {
        this.a = bOVar;
    }

    /* access modifiers changed from: package-private */
    public final int a(Object[] objArr, int i) {
        cM a2 = this.a.b.values().iterator();
        while (a2.hasNext()) {
            i = ((bC) a2.next()).a(objArr, i);
        }
        return i;
    }

    public final cM a() {
        return this.a.j();
    }

    public final boolean contains(Object obj) {
        return this.a.b(obj);
    }

    /* access modifiers changed from: package-private */
    public final boolean f() {
        return true;
    }

    public final /* synthetic */ Iterator iterator() {
        return this.a.j();
    }

    public final int size() {
        return this.a.c;
    }
}

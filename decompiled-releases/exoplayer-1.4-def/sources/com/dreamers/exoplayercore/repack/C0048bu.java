package com.dreamers.exoplayercore.repack;

import java.util.AbstractSet;
import java.util.Iterator;

/* renamed from: com.dreamers.exoplayercore.repack.bu  reason: case insensitive filesystem */
final class C0048bu extends AbstractSet {
    private /* synthetic */ C0042bo a;

    C0048bu(C0042bo boVar) {
        this.a = boVar;
    }

    public final void clear() {
        this.a.clear();
    }

    public final boolean contains(Object obj) {
        return this.a.containsKey(obj);
    }

    public final Iterator iterator() {
        return new C0043bp(this.a);
    }

    public final boolean remove(Object obj) {
        int a2 = this.a.a(obj);
        if (a2 == -1) {
            return false;
        }
        Object unused = this.a.a(this.a.a[a2], (int) (this.a.f[a2] >>> 32));
        return true;
    }

    public final int size() {
        return this.a.d;
    }
}

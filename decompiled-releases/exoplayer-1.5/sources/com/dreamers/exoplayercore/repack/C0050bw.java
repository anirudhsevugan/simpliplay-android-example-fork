package com.dreamers.exoplayercore.repack;

import java.util.AbstractCollection;
import java.util.Iterator;

/* renamed from: com.dreamers.exoplayercore.repack.bw  reason: case insensitive filesystem */
final class C0050bw extends AbstractCollection {
    private /* synthetic */ C0042bo a;

    C0050bw(C0042bo boVar) {
        this.a = boVar;
    }

    public final void clear() {
        this.a.clear();
    }

    public final Iterator iterator() {
        return new C0045br(this.a);
    }

    public final int size() {
        return this.a.d;
    }
}

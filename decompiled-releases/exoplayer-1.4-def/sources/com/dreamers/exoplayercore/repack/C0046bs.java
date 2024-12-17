package com.dreamers.exoplayercore.repack;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.dreamers.exoplayercore.repack.bs  reason: case insensitive filesystem */
final class C0046bs extends AbstractSet {
    private /* synthetic */ C0042bo a;

    C0046bs(C0042bo boVar) {
        this.a = boVar;
    }

    public final void clear() {
        this.a.clear();
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            int a2 = this.a.a(entry.getKey());
            return a2 != -1 && P.a(this.a.b[a2], entry.getValue());
        }
    }

    public final Iterator iterator() {
        return new C0044bq(this.a);
    }

    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        int a2 = this.a.a(entry.getKey());
        if (a2 == -1 || !P.a(this.a.b[a2], entry.getValue())) {
            return false;
        }
        Object unused = this.a.a(this.a.a[a2], (int) (this.a.f[a2] >>> 32));
        return true;
    }

    public final int size() {
        return this.a.d;
    }
}

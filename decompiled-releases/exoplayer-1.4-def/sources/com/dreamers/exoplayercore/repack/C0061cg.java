package com.dreamers.exoplayercore.repack;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Set;

/* renamed from: com.dreamers.exoplayercore.repack.cg  reason: case insensitive filesystem */
abstract class C0061cg extends AbstractMap {
    private transient Set a;
    private transient Set b;
    private transient Collection c;

    C0061cg() {
    }

    /* access modifiers changed from: package-private */
    public abstract Set a();

    /* access modifiers changed from: package-private */
    public Set e() {
        return new C0059ce(this);
    }

    public Set entrySet() {
        Set set = this.a;
        if (set != null) {
            return set;
        }
        Set a2 = a();
        this.a = a2;
        return a2;
    }

    public Set keySet() {
        Set set = this.b;
        if (set != null) {
            return set;
        }
        Set e = e();
        this.b = e;
        return e;
    }

    public Collection values() {
        Collection collection = this.c;
        if (collection != null) {
            return collection;
        }
        C0060cf cfVar = new C0060cf(this);
        this.c = cfVar;
        return cfVar;
    }
}

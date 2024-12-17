package com.dreamers.exoplayercore.repack;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* renamed from: com.dreamers.exoplayercore.repack.aT  reason: case insensitive filesystem */
class C0001aT extends C0061cg {
    final transient Map a;
    final /* synthetic */ aQ b;

    C0001aT(aQ aQVar, Map map) {
        this.b = aQVar;
        this.a = map;
    }

    /* access modifiers changed from: package-private */
    public final Map.Entry a(Map.Entry entry) {
        Object key = entry.getKey();
        return C0055ca.a(key, (Object) this.b.a(key, (Collection) entry.getValue()));
    }

    /* access modifiers changed from: protected */
    public final Set a() {
        return new aU(this);
    }

    public void clear() {
        if (this.a == this.b.a) {
            this.b.e();
        } else {
            bV.c(new aV(this));
        }
    }

    public boolean containsKey(Object obj) {
        return C0055ca.b(this.a, obj);
    }

    public boolean equals(Object obj) {
        return this == obj || this.a.equals(obj);
    }

    public /* synthetic */ Object get(Object obj) {
        Collection collection = (Collection) C0055ca.a(this.a, obj);
        if (collection == null) {
            return null;
        }
        return this.b.a(obj, collection);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public Set keySet() {
        return this.b.p();
    }

    public /* synthetic */ Object remove(Object obj) {
        Collection collection = (Collection) this.a.remove(obj);
        if (collection == null) {
            return null;
        }
        Collection c = this.b.c();
        c.addAll(collection);
        aQ aQVar = this.b;
        int unused = aQVar.b = aQVar.b - collection.size();
        collection.clear();
        return c;
    }

    public int size() {
        return this.a.size();
    }

    public String toString() {
        return this.a.toString();
    }
}

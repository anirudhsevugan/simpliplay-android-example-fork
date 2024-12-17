package com.dreamers.exoplayercore.repack;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: com.dreamers.exoplayercore.repack.bj  reason: case insensitive filesystem */
abstract class C0037bj implements C0062ch {
    private transient Collection a;
    private transient Set b;
    private transient Collection c;
    private transient Map d;

    C0037bj() {
    }

    public boolean a(Object obj, Object obj2) {
        return a(obj).add(obj2);
    }

    public Map b() {
        Map map = this.d;
        if (map != null) {
            return map;
        }
        Map n = n();
        this.d = n;
        return n;
    }

    public boolean b(Object obj) {
        for (Collection contains : b().values()) {
            if (contains.contains(obj)) {
                return true;
            }
        }
        return false;
    }

    public boolean b(Object obj, Object obj2) {
        Collection collection = (Collection) b().get(obj);
        return collection != null && collection.contains(obj2);
    }

    public boolean c(Object obj, Object obj2) {
        Collection collection = (Collection) b().get(obj);
        return collection != null && collection.remove(obj2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C0062ch) {
            return b().equals(((C0062ch) obj).b());
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public abstract Set f();

    public Collection h() {
        Collection collection = this.c;
        if (collection != null) {
            return collection;
        }
        Collection i = i();
        this.c = i;
        return i;
    }

    public int hashCode() {
        return b().hashCode();
    }

    /* access modifiers changed from: package-private */
    public abstract Collection i();

    /* access modifiers changed from: package-private */
    public Iterator j() {
        return C0055ca.a(k().iterator());
    }

    public Collection k() {
        Collection collection = this.a;
        if (collection != null) {
            return collection;
        }
        Collection l = l();
        this.a = l;
        return l;
    }

    /* access modifiers changed from: package-private */
    public abstract Collection l();

    /* access modifiers changed from: package-private */
    public abstract Iterator m();

    /* access modifiers changed from: package-private */
    public abstract Map n();

    public Set p() {
        Set set = this.b;
        if (set != null) {
            return set;
        }
        Set f = f();
        this.b = f;
        return f;
    }

    public String toString() {
        return b().toString();
    }
}

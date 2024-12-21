package com.dreamers.exoplayercore.repack;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;

abstract class aQ extends C0037bj implements Serializable {
    /* access modifiers changed from: private */
    public transient Map a;
    /* access modifiers changed from: private */
    public transient int b;

    protected aQ(Map map) {
        C0000a.a(map.isEmpty());
        this.a = map;
    }

    static /* synthetic */ void a(aQ aQVar, Object obj) {
        Collection collection = (Collection) C0055ca.c(aQVar.a, obj);
        if (collection != null) {
            int size = collection.size();
            collection.clear();
            aQVar.b -= size;
        }
    }

    static /* synthetic */ int b(aQ aQVar) {
        int i = aQVar.b;
        aQVar.b = i - 1;
        return i;
    }

    static /* synthetic */ Iterator b(Collection collection) {
        return collection instanceof List ? ((List) collection).listIterator() : collection.iterator();
    }

    static /* synthetic */ int c(aQ aQVar) {
        int i = aQVar.b;
        aQVar.b = i + 1;
        return i;
    }

    public Collection a(Object obj) {
        Collection collection = (Collection) this.a.get(obj);
        if (collection == null) {
            collection = c();
        }
        return a(obj, collection);
    }

    /* access modifiers changed from: package-private */
    public Collection a(Object obj, Collection collection) {
        return new C0032be(this, obj, collection, (C0032be) null);
    }

    /* access modifiers changed from: package-private */
    public Collection a(Collection collection) {
        return Collections.unmodifiableCollection(collection);
    }

    /* access modifiers changed from: package-private */
    public final List a(Object obj, List list, C0032be beVar) {
        return list instanceof RandomAccess ? new C0029bb(this, obj, list, beVar) : new C0034bg(this, obj, list, beVar);
    }

    public boolean a(Object obj, Object obj2) {
        Collection collection = (Collection) this.a.get(obj);
        if (collection == null) {
            Collection c = c();
            if (c.add(obj2)) {
                this.b++;
                this.a.put(obj, c);
                return true;
            }
            throw new AssertionError("New Collection violated the Collection spec");
        } else if (!collection.add(obj2)) {
            return false;
        } else {
            this.b++;
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public abstract Collection c();

    public final int d() {
        return this.b;
    }

    public final void e() {
        for (Collection clear : this.a.values()) {
            clear.clear();
        }
        this.a.clear();
        this.b = 0;
    }

    /* access modifiers changed from: package-private */
    public Set f() {
        return new aX(this, this.a);
    }

    /* access modifiers changed from: package-private */
    public final Set g() {
        Map map = this.a;
        return map instanceof NavigableMap ? new C0028ba(this, (NavigableMap) this.a) : map instanceof SortedMap ? new C0031bd(this, (SortedMap) this.a) : new aX(this, this.a);
    }

    public final Collection h() {
        return super.h();
    }

    /* access modifiers changed from: package-private */
    public final Collection i() {
        return new C0040bm(this);
    }

    /* access modifiers changed from: package-private */
    public final Iterator j() {
        return new aR(this);
    }

    public final Collection k() {
        return super.k();
    }

    /* access modifiers changed from: package-private */
    public final Collection l() {
        return this instanceof cE ? new C0039bl(this) : new C0038bk(this);
    }

    /* access modifiers changed from: package-private */
    public final Iterator m() {
        return new aS(this);
    }

    /* access modifiers changed from: package-private */
    public Map n() {
        return new C0001aT(this, this.a);
    }

    /* access modifiers changed from: package-private */
    public final Map o() {
        Map map = this.a;
        return map instanceof NavigableMap ? new aZ(this, (NavigableMap) this.a) : map instanceof SortedMap ? new C0030bc(this, (SortedMap) this.a) : new C0001aT(this, this.a);
    }
}

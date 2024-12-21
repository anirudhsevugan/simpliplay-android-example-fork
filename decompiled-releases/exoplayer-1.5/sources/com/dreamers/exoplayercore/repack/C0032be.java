package com.dreamers.exoplayercore.repack;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/* renamed from: com.dreamers.exoplayercore.repack.be  reason: case insensitive filesystem */
class C0032be extends AbstractCollection {
    final Object a;
    Collection b;
    final C0032be c;
    final /* synthetic */ aQ d;
    private Collection e;

    C0032be(aQ aQVar, Object obj, Collection collection, C0032be beVar) {
        this.d = aQVar;
        this.a = obj;
        this.b = collection;
        this.c = beVar;
        this.e = beVar == null ? null : beVar.b;
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        Collection collection;
        C0032be beVar = this.c;
        if (beVar != null) {
            beVar.a();
            if (this.c.b != this.e) {
                throw new ConcurrentModificationException();
            }
        } else if (this.b.isEmpty() && (collection = (Collection) this.d.a.get(this.a)) != null) {
            this.b = collection;
        }
    }

    public boolean add(Object obj) {
        a();
        boolean isEmpty = this.b.isEmpty();
        boolean add = this.b.add(obj);
        if (add) {
            aQ.c(this.d);
            if (isEmpty) {
                c();
            }
        }
        return add;
    }

    public boolean addAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = this.b.addAll(collection);
        if (addAll) {
            int size2 = this.b.size();
            aQ aQVar = this.d;
            int unused = aQVar.b = aQVar.b + (size2 - size);
            if (size == 0) {
                c();
            }
        }
        return addAll;
    }

    /* access modifiers changed from: package-private */
    public final void b() {
        C0032be beVar = this;
        while (true) {
            C0032be beVar2 = beVar.c;
            if (beVar2 == null) {
                break;
            }
            beVar = beVar2;
        }
        if (beVar.b.isEmpty()) {
            beVar.d.a.remove(beVar.a);
        }
    }

    /* access modifiers changed from: package-private */
    public final void c() {
        C0032be beVar = this;
        while (true) {
            C0032be beVar2 = beVar.c;
            if (beVar2 != null) {
                beVar = beVar2;
            } else {
                beVar.d.a.put(beVar.a, beVar.b);
                return;
            }
        }
    }

    public void clear() {
        int size = size();
        if (size != 0) {
            this.b.clear();
            aQ aQVar = this.d;
            int unused = aQVar.b = aQVar.b - size;
            b();
        }
    }

    public boolean contains(Object obj) {
        a();
        return this.b.contains(obj);
    }

    public boolean containsAll(Collection collection) {
        a();
        return this.b.containsAll(collection);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        a();
        return this.b.equals(obj);
    }

    public int hashCode() {
        a();
        return this.b.hashCode();
    }

    public Iterator iterator() {
        a();
        return new C0033bf(this);
    }

    public boolean remove(Object obj) {
        a();
        boolean remove = this.b.remove(obj);
        if (remove) {
            aQ.b(this.d);
            b();
        }
        return remove;
    }

    public boolean removeAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean removeAll = this.b.removeAll(collection);
        if (removeAll) {
            int size2 = this.b.size();
            aQ aQVar = this.d;
            int unused = aQVar.b = aQVar.b + (size2 - size);
            b();
        }
        return removeAll;
    }

    public boolean retainAll(Collection collection) {
        C0000a.a((Object) collection);
        int size = size();
        boolean retainAll = this.b.retainAll(collection);
        if (retainAll) {
            int size2 = this.b.size();
            aQ aQVar = this.d;
            int unused = aQVar.b = aQVar.b + (size2 - size);
            b();
        }
        return retainAll;
    }

    public int size() {
        a();
        return this.b.size();
    }

    public String toString() {
        a();
        return this.b.toString();
    }
}

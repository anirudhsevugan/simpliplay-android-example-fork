package com.dreamers.exoplayercore.repack;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/* renamed from: com.dreamers.exoplayercore.repack.bg  reason: case insensitive filesystem */
class C0034bg extends C0032be implements List {
    final /* synthetic */ aQ e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0034bg(aQ aQVar, Object obj, List list, C0032be beVar) {
        super(aQVar, obj, list, beVar);
        this.e = aQVar;
    }

    public void add(int i, Object obj) {
        a();
        boolean isEmpty = this.b.isEmpty();
        ((List) this.b).add(i, obj);
        aQ.c(this.e);
        if (isEmpty) {
            c();
        }
    }

    public boolean addAll(int i, Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = ((List) this.b).addAll(i, collection);
        if (addAll) {
            int size2 = this.b.size();
            aQ aQVar = this.e;
            int unused = aQVar.b = aQVar.b + (size2 - size);
            if (size == 0) {
                c();
            }
        }
        return addAll;
    }

    public Object get(int i) {
        a();
        return ((List) this.b).get(i);
    }

    public int indexOf(Object obj) {
        a();
        return ((List) this.b).indexOf(obj);
    }

    public int lastIndexOf(Object obj) {
        a();
        return ((List) this.b).lastIndexOf(obj);
    }

    public ListIterator listIterator() {
        a();
        return new C0035bh(this);
    }

    public ListIterator listIterator(int i) {
        a();
        return new C0035bh(this, i);
    }

    public Object remove(int i) {
        a();
        Object remove = ((List) this.b).remove(i);
        aQ.b(this.e);
        b();
        return remove;
    }

    public Object set(int i, Object obj) {
        a();
        return ((List) this.b).set(i, obj);
    }

    public List subList(int i, int i2) {
        a();
        return this.e.a(this.a, ((List) this.b).subList(i, i2), this.c == null ? this : this.c);
    }
}

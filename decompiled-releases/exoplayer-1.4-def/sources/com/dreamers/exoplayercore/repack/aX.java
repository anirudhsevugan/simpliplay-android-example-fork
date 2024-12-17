package com.dreamers.exoplayercore.repack;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

class aX extends C0059ce {
    final /* synthetic */ aQ a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    aX(aQ aQVar, Map map) {
        super(map);
        this.a = aQVar;
    }

    public void clear() {
        bV.c(iterator());
    }

    public boolean containsAll(Collection collection) {
        return this.b.keySet().containsAll(collection);
    }

    public boolean equals(Object obj) {
        return this == obj || this.b.keySet().equals(obj);
    }

    public int hashCode() {
        return this.b.keySet().hashCode();
    }

    public Iterator iterator() {
        return new aY(this, this.b.entrySet().iterator());
    }

    public boolean remove(Object obj) {
        int i;
        Collection collection = (Collection) this.b.remove(obj);
        if (collection != null) {
            i = collection.size();
            collection.clear();
            aQ aQVar = this.a;
            int unused = aQVar.b = aQVar.b - i;
        } else {
            i = 0;
        }
        return i > 0;
    }
}

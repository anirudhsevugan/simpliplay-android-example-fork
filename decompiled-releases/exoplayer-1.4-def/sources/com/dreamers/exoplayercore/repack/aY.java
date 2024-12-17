package com.dreamers.exoplayercore.repack;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

final class aY implements Iterator {
    private Map.Entry a;
    private /* synthetic */ Iterator b;
    private /* synthetic */ aX c;

    aY(aX aXVar, Iterator it) {
        this.c = aXVar;
        this.b = it;
    }

    public final boolean hasNext() {
        return this.b.hasNext();
    }

    public final Object next() {
        Map.Entry entry = (Map.Entry) this.b.next();
        this.a = entry;
        return entry.getKey();
    }

    public final void remove() {
        C0000a.c(this.a != null);
        Collection collection = (Collection) this.a.getValue();
        this.b.remove();
        int unused = this.c.a.b = this.c.a.b - collection.size();
        collection.clear();
        this.a = null;
    }
}

package com.dreamers.exoplayercore.repack;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

abstract class aW implements Iterator {
    private Iterator a;
    private Object b = null;
    private Collection c = null;
    private Iterator d = bY.a;
    private /* synthetic */ aQ e;

    aW(aQ aQVar) {
        this.e = aQVar;
        this.a = aQVar.a.entrySet().iterator();
    }

    /* access modifiers changed from: package-private */
    public abstract Object a(Object obj, Object obj2);

    public boolean hasNext() {
        return this.a.hasNext() || this.d.hasNext();
    }

    public Object next() {
        if (!this.d.hasNext()) {
            Map.Entry entry = (Map.Entry) this.a.next();
            this.b = entry.getKey();
            Collection collection = (Collection) entry.getValue();
            this.c = collection;
            this.d = collection.iterator();
        }
        return a(this.b, this.d.next());
    }

    public void remove() {
        this.d.remove();
        if (this.c.isEmpty()) {
            this.a.remove();
        }
        aQ.b(this.e);
    }
}

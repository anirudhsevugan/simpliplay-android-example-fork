package com.dreamers.exoplayercore.repack;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

final class aV implements Iterator {
    private Iterator a = this.c.a.entrySet().iterator();
    private Collection b;
    private /* synthetic */ C0001aT c;

    aV(C0001aT aTVar) {
        this.c = aTVar;
    }

    public final boolean hasNext() {
        return this.a.hasNext();
    }

    public final /* synthetic */ Object next() {
        Map.Entry entry = (Map.Entry) this.a.next();
        this.b = (Collection) entry.getValue();
        return this.c.a(entry);
    }

    public final void remove() {
        C0000a.c(this.b != null);
        this.a.remove();
        int unused = this.c.b.b = this.c.b.b - this.b.size();
        this.b.clear();
        this.b = null;
    }
}

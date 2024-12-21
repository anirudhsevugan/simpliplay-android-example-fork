package com.dreamers.exoplayercore.repack;

import java.util.Iterator;

abstract class cL implements Iterator {
    private Iterator a;

    cL(Iterator it) {
        this.a = (Iterator) C0000a.a((Object) it);
    }

    /* access modifiers changed from: package-private */
    public abstract Object a(Object obj);

    public final boolean hasNext() {
        return this.a.hasNext();
    }

    public final Object next() {
        return a(this.a.next());
    }

    public final void remove() {
        this.a.remove();
    }
}

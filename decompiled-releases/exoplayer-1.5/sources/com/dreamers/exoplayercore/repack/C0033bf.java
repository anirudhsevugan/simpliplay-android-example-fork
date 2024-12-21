package com.dreamers.exoplayercore.repack;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/* renamed from: com.dreamers.exoplayercore.repack.bf  reason: case insensitive filesystem */
class C0033bf implements Iterator {
    final Iterator a;
    private Collection b = this.c.b;
    private /* synthetic */ C0032be c;

    C0033bf(C0032be beVar) {
        this.c = beVar;
        this.a = aQ.b(beVar.b);
    }

    C0033bf(C0032be beVar, Iterator it) {
        this.c = beVar;
        this.a = it;
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        this.c.a();
        if (this.c.b != this.b) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean hasNext() {
        a();
        return this.a.hasNext();
    }

    public Object next() {
        a();
        return this.a.next();
    }

    public void remove() {
        this.a.remove();
        aQ.b(this.c.d);
        this.c.b();
    }
}

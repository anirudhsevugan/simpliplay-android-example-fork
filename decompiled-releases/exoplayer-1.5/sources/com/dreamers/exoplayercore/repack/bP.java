package com.dreamers.exoplayercore.repack;

import java.util.Iterator;
import java.util.Map;

final class bP extends cM {
    private Iterator a = this.d.b.entrySet().iterator();
    private Object b = null;
    private Iterator c = bX.a;
    private /* synthetic */ bO d;

    bP(bO bOVar) {
        this.d = bOVar;
    }

    public final boolean hasNext() {
        return this.c.hasNext() || this.a.hasNext();
    }

    public final /* synthetic */ Object next() {
        if (!this.c.hasNext()) {
            Map.Entry entry = (Map.Entry) this.a.next();
            this.b = entry.getKey();
            this.c = ((bC) entry.getValue()).iterator();
        }
        return C0055ca.a(this.b, this.c.next());
    }
}

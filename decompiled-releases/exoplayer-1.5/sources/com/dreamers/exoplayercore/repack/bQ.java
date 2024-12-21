package com.dreamers.exoplayercore.repack;

import java.util.Iterator;

final class bQ extends cM {
    private Iterator a = this.c.b.values().iterator();
    private Iterator b = bX.a;
    private /* synthetic */ bO c;

    bQ(bO bOVar) {
        this.c = bOVar;
    }

    public final boolean hasNext() {
        return this.b.hasNext() || this.a.hasNext();
    }

    public final Object next() {
        if (!this.b.hasNext()) {
            this.b = ((bC) this.a.next()).iterator();
        }
        return this.b.next();
    }
}

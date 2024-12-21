package com.dreamers.exoplayercore.repack;

import java.util.Iterator;

final class cH extends aM {
    private Iterator a = this.b.a.iterator();
    private /* synthetic */ cG b;

    cH(cG cGVar) {
        this.b = cGVar;
    }

    /* access modifiers changed from: protected */
    public final Object a() {
        while (this.a.hasNext()) {
            Object next = this.a.next();
            if (this.b.b.contains(next)) {
                return next;
            }
        }
        return b();
    }
}

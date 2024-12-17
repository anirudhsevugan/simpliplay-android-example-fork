package com.dreamers.exoplayercore.repack;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

final class aU extends C0058cd {
    private /* synthetic */ C0001aT a;

    aU(C0001aT aTVar) {
        this.a = aTVar;
    }

    /* access modifiers changed from: package-private */
    public final Map a() {
        return this.a;
    }

    public final boolean contains(Object obj) {
        return C0000a.a((Collection) this.a.a.entrySet(), obj);
    }

    public final Iterator iterator() {
        return new aV(this.a);
    }

    public final boolean remove(Object obj) {
        if (!contains(obj)) {
            return false;
        }
        aQ.a(this.a.b, ((Map.Entry) obj).getKey());
        return true;
    }
}

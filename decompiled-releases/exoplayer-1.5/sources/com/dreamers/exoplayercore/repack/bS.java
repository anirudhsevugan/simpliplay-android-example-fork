package com.dreamers.exoplayercore.repack;

import java.util.Iterator;
import java.util.Map;

final class bS extends bC {
    private bO a;

    bS(bO bOVar) {
        this.a = bOVar;
    }

    public final cM a() {
        return this.a.m();
    }

    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return this.a.b(entry.getKey(), entry.getValue());
    }

    /* access modifiers changed from: package-private */
    public final boolean f() {
        return false;
    }

    public final /* synthetic */ Iterator iterator() {
        return this.a.m();
    }

    public final int size() {
        return this.a.c;
    }
}

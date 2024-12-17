package com.dreamers.exoplayercore.repack;

import java.io.Serializable;

final class cC extends C0075cu implements Serializable {
    static final cC a = new cC();

    private cC() {
    }

    public final C0075cu a() {
        return C0074ct.a;
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        Comparable comparable = (Comparable) obj;
        Comparable comparable2 = (Comparable) obj2;
        C0000a.a((Object) comparable);
        if (comparable == comparable2) {
            return 0;
        }
        return comparable2.compareTo(comparable);
    }

    public final String toString() {
        return "Ordering.natural().reverse()";
    }
}

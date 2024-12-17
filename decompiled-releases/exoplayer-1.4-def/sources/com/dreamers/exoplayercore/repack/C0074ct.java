package com.dreamers.exoplayercore.repack;

import java.io.Serializable;

/* renamed from: com.dreamers.exoplayercore.repack.ct  reason: case insensitive filesystem */
final class C0074ct extends C0075cu implements Serializable {
    static final C0074ct a = new C0074ct();

    private C0074ct() {
    }

    public final C0075cu a() {
        return cC.a;
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        Comparable comparable = (Comparable) obj;
        Comparable comparable2 = (Comparable) obj2;
        C0000a.a((Object) comparable);
        C0000a.a((Object) comparable2);
        return comparable.compareTo(comparable2);
    }

    public final String toString() {
        return "Ordering.natural()";
    }
}

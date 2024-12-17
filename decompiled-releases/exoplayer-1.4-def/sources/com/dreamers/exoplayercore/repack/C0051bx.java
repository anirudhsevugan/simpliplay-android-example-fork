package com.dreamers.exoplayercore.repack;

import java.io.Serializable;
import java.util.Comparator;

/* renamed from: com.dreamers.exoplayercore.repack.bx  reason: case insensitive filesystem */
final class C0051bx extends C0075cu implements Serializable {
    private Comparator a;

    C0051bx(Comparator comparator) {
        this.a = (Comparator) C0000a.a((Object) comparator);
    }

    public final int compare(Object obj, Object obj2) {
        return this.a.compare(obj, obj2);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C0051bx) {
            return this.a.equals(((C0051bx) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a.toString();
    }
}

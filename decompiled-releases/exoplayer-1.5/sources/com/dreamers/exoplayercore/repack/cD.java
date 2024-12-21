package com.dreamers.exoplayercore.repack;

import java.io.Serializable;

final class cD extends C0075cu implements Serializable {
    private C0075cu a;

    cD(C0075cu cuVar) {
        this.a = (C0075cu) C0000a.a((Object) cuVar);
    }

    public final C0075cu a() {
        return this.a;
    }

    public final int compare(Object obj, Object obj2) {
        return this.a.compare(obj2, obj);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof cD) {
            return this.a.equals(((cD) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return -this.a.hashCode();
    }

    public final String toString() {
        return this.a + ".reverse()";
    }
}

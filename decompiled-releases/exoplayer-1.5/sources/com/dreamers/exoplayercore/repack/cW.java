package com.dreamers.exoplayercore.repack;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class cW extends WeakReference {
    private final int a;

    public cW(Throwable th, ReferenceQueue referenceQueue) {
        super(th, referenceQueue);
        if (th != null) {
            this.a = System.identityHashCode(th);
            return;
        }
        throw new NullPointerException("The referent cannot be null");
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (this == obj) {
                return true;
            }
            cW cWVar = (cW) obj;
            return this.a == cWVar.a && get() == cWVar.get();
        }
    }

    public final int hashCode() {
        return this.a;
    }
}

package com.dreamers.exoplayercore.repack;

import java.util.AbstractCollection;
import java.util.Map;

/* renamed from: com.dreamers.exoplayercore.repack.cr  reason: case insensitive filesystem */
abstract class C0072cr extends AbstractCollection {
    C0072cr() {
    }

    /* access modifiers changed from: package-private */
    public abstract C0062ch a();

    public void clear() {
        a().e();
    }

    public boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return a().b(entry.getKey(), entry.getValue());
    }

    public boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return a().c(entry.getKey(), entry.getValue());
    }

    public int size() {
        return a().d();
    }
}

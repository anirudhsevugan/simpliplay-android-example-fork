package com.dreamers.exoplayercore.repack;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

final class cV {
    private final ConcurrentHashMap a = new ConcurrentHashMap(16, 0.75f, 10);
    private final ReferenceQueue b = new ReferenceQueue();

    cV() {
    }

    public final List a(Throwable th) {
        while (true) {
            Reference poll = this.b.poll();
            if (poll == null) {
                break;
            }
            this.a.remove(poll);
        }
        List list = (List) this.a.get(new cW(th, (ReferenceQueue) null));
        if (list != null) {
            return list;
        }
        Vector vector = new Vector(2);
        List list2 = (List) this.a.putIfAbsent(new cW(th, this.b), vector);
        return list2 == null ? vector : list2;
    }
}

package com.dreamers.exoplayercore.repack;

import java.util.Iterator;
import java.util.Map;

/* renamed from: com.dreamers.exoplayercore.repack.ce  reason: case insensitive filesystem */
class C0059ce extends cI {
    final Map b;

    C0059ce(Map map) {
        this.b = (Map) C0000a.a((Object) map);
    }

    public void clear() {
        this.b.clear();
    }

    public boolean contains(Object obj) {
        return this.b.containsKey(obj);
    }

    public boolean isEmpty() {
        return this.b.isEmpty();
    }

    public Iterator iterator() {
        return new C0056cb(this.b.entrySet().iterator());
    }

    public boolean remove(Object obj) {
        if (!contains(obj)) {
            return false;
        }
        this.b.remove(obj);
        return true;
    }

    public int size() {
        return this.b.size();
    }
}

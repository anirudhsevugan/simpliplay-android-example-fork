package com.google.android.exoplayer2.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class CopyOnWriteMultiset implements Iterable {
    public final Object a = new Object();
    public final Map b = new HashMap();
    public Set c = Collections.emptySet();
    public List d = Collections.emptyList();

    public final int a(Object obj) {
        int intValue;
        synchronized (this.a) {
            intValue = this.b.containsKey(obj) ? ((Integer) this.b.get(obj)).intValue() : 0;
        }
        return intValue;
    }

    public final Set a() {
        Set set;
        synchronized (this.a) {
            set = this.c;
        }
        return set;
    }

    public final Iterator iterator() {
        Iterator it;
        synchronized (this.a) {
            it = this.d.iterator();
        }
        return it;
    }
}

package com.dreamers.exoplayercore.repack;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public abstract class bM implements Serializable, Map {
    private transient bU a;
    private transient bU b;
    private transient bC c;

    bM() {
    }

    public static bM a() {
        return C0077cw.a;
    }

    public static bM a(Map map) {
        if ((map instanceof bM) && !(map instanceof SortedMap)) {
            return (bM) map;
        }
        Set<Map.Entry> entrySet = map.entrySet();
        boolean z = entrySet instanceof Collection;
        bN bNVar = new bN(z ? entrySet.size() : 4);
        if (z) {
            bNVar.a(bNVar.a + entrySet.size());
        }
        for (Map.Entry entry : entrySet) {
            bNVar.a(entry.getKey(), entry.getValue());
        }
        return bNVar.a();
    }

    public static bN b() {
        return new bN();
    }

    /* renamed from: c */
    public final bU entrySet() {
        bU bUVar = this.a;
        if (bUVar != null) {
            return bUVar;
        }
        bU d = d();
        this.a = d;
        return d;
    }

    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    public boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    /* access modifiers changed from: package-private */
    public abstract bU d();

    /* renamed from: e */
    public final bU keySet() {
        bU bUVar = this.b;
        if (bUVar != null) {
            return bUVar;
        }
        bU f = f();
        this.b = f;
        return f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public abstract bU f();

    /* renamed from: g */
    public final bC values() {
        bC bCVar = this.c;
        if (bCVar != null) {
            return bCVar;
        }
        bC h = h();
        this.c = h;
        return h;
    }

    public abstract Object get(Object obj);

    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    /* access modifiers changed from: package-private */
    public abstract bC h();

    public int hashCode() {
        return cF.a(entrySet());
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        int size = size();
        C0000a.a(size, "size");
        StringBuilder append = new StringBuilder((int) Math.min(((long) size) << 3, 1073741824)).append('{');
        boolean z = true;
        for (Map.Entry entry : entrySet()) {
            if (!z) {
                append.append(", ");
            }
            append.append(entry.getKey()).append('=').append(entry.getValue());
            z = false;
        }
        return append.append('}').toString();
    }
}

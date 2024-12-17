package com.dreamers.exoplayercore.repack;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.dreamers.exoplayercore.repack.cf  reason: case insensitive filesystem */
final class C0060cf extends AbstractCollection {
    private Map a;

    C0060cf(Map map) {
        this.a = (Map) C0000a.a((Object) map);
    }

    public final void clear() {
        this.a.clear();
    }

    public final boolean contains(Object obj) {
        return this.a.containsValue(obj);
    }

    public final boolean isEmpty() {
        return this.a.isEmpty();
    }

    public final Iterator iterator() {
        return C0055ca.a(this.a.entrySet().iterator());
    }

    public final boolean remove(Object obj) {
        try {
            return super.remove(obj);
        } catch (UnsupportedOperationException e) {
            for (Map.Entry entry : this.a.entrySet()) {
                if (P.a(obj, entry.getValue())) {
                    this.a.remove(entry.getKey());
                    return true;
                }
            }
            return false;
        }
    }

    public final boolean removeAll(Collection collection) {
        try {
            return super.removeAll((Collection) C0000a.a((Object) collection));
        } catch (UnsupportedOperationException e) {
            HashSet hashSet = new HashSet();
            for (Map.Entry entry : this.a.entrySet()) {
                if (collection.contains(entry.getValue())) {
                    hashSet.add(entry.getKey());
                }
            }
            return this.a.keySet().removeAll(hashSet);
        }
    }

    public final boolean retainAll(Collection collection) {
        try {
            return super.retainAll((Collection) C0000a.a((Object) collection));
        } catch (UnsupportedOperationException e) {
            HashSet hashSet = new HashSet();
            for (Map.Entry entry : this.a.entrySet()) {
                if (collection.contains(entry.getValue())) {
                    hashSet.add(entry.getKey());
                }
            }
            return this.a.keySet().retainAll(hashSet);
        }
    }

    public final int size() {
        return this.a.size();
    }
}

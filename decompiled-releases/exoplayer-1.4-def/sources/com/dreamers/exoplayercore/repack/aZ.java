package com.dreamers.exoplayercore.repack;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

final class aZ extends C0030bc implements NavigableMap {
    private /* synthetic */ aQ c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    aZ(aQ aQVar, NavigableMap navigableMap) {
        super(aQVar, navigableMap);
        this.c = aQVar;
    }

    private Map.Entry a(Iterator it) {
        if (!it.hasNext()) {
            return null;
        }
        Map.Entry entry = (Map.Entry) it.next();
        Collection c2 = this.c.c();
        c2.addAll((Collection) entry.getValue());
        it.remove();
        return C0055ca.a(entry.getKey(), (Object) this.c.a(c2));
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public NavigableSet e() {
        return new C0028ba(this.c, (NavigableMap) super.d());
    }

    public final /* bridge */ /* synthetic */ SortedSet c() {
        return (NavigableSet) super.keySet();
    }

    public final Map.Entry ceilingEntry(Object obj) {
        Map.Entry ceilingEntry = ((NavigableMap) super.d()).ceilingEntry(obj);
        if (ceilingEntry == null) {
            return null;
        }
        return a(ceilingEntry);
    }

    public final Object ceilingKey(Object obj) {
        return ((NavigableMap) super.d()).ceilingKey(obj);
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ SortedMap d() {
        return (NavigableMap) super.d();
    }

    public final NavigableSet descendingKeySet() {
        return descendingMap().navigableKeySet();
    }

    public final NavigableMap descendingMap() {
        return new aZ(this.c, ((NavigableMap) super.d()).descendingMap());
    }

    public final Map.Entry firstEntry() {
        Map.Entry firstEntry = ((NavigableMap) super.d()).firstEntry();
        if (firstEntry == null) {
            return null;
        }
        return a(firstEntry);
    }

    public final Map.Entry floorEntry(Object obj) {
        Map.Entry floorEntry = ((NavigableMap) super.d()).floorEntry(obj);
        if (floorEntry == null) {
            return null;
        }
        return a(floorEntry);
    }

    public final Object floorKey(Object obj) {
        return ((NavigableMap) super.d()).floorKey(obj);
    }

    public final NavigableMap headMap(Object obj, boolean z) {
        return new aZ(this.c, ((NavigableMap) super.d()).headMap(obj, z));
    }

    public final /* bridge */ /* synthetic */ SortedMap headMap(Object obj) {
        return headMap(obj, false);
    }

    public final Map.Entry higherEntry(Object obj) {
        Map.Entry higherEntry = ((NavigableMap) super.d()).higherEntry(obj);
        if (higherEntry == null) {
            return null;
        }
        return a(higherEntry);
    }

    public final Object higherKey(Object obj) {
        return ((NavigableMap) super.d()).higherKey(obj);
    }

    public final /* synthetic */ Set keySet() {
        return (NavigableSet) super.keySet();
    }

    public final Map.Entry lastEntry() {
        Map.Entry lastEntry = ((NavigableMap) super.d()).lastEntry();
        if (lastEntry == null) {
            return null;
        }
        return a(lastEntry);
    }

    public final Map.Entry lowerEntry(Object obj) {
        Map.Entry lowerEntry = ((NavigableMap) super.d()).lowerEntry(obj);
        if (lowerEntry == null) {
            return null;
        }
        return a(lowerEntry);
    }

    public final Object lowerKey(Object obj) {
        return ((NavigableMap) super.d()).lowerKey(obj);
    }

    public final NavigableSet navigableKeySet() {
        return (NavigableSet) super.keySet();
    }

    public final Map.Entry pollFirstEntry() {
        return a(entrySet().iterator());
    }

    public final Map.Entry pollLastEntry() {
        return a(descendingMap().entrySet().iterator());
    }

    public final NavigableMap subMap(Object obj, boolean z, Object obj2, boolean z2) {
        return new aZ(this.c, ((NavigableMap) super.d()).subMap(obj, z, obj2, z2));
    }

    public final /* bridge */ /* synthetic */ SortedMap subMap(Object obj, Object obj2) {
        return subMap(obj, true, obj2, false);
    }

    public final NavigableMap tailMap(Object obj, boolean z) {
        return new aZ(this.c, ((NavigableMap) super.d()).tailMap(obj, z));
    }

    public final /* bridge */ /* synthetic */ SortedMap tailMap(Object obj) {
        return tailMap(obj, true);
    }
}

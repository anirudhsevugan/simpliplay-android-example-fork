package com.dreamers.exoplayercore.repack;

import java.util.Iterator;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.SortedMap;
import java.util.SortedSet;

/* renamed from: com.dreamers.exoplayercore.repack.ba  reason: case insensitive filesystem */
final class C0028ba extends C0031bd implements NavigableSet {
    private /* synthetic */ aQ c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0028ba(aQ aQVar, NavigableMap navigableMap) {
        super(aQVar, navigableMap);
        this.c = aQVar;
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ SortedMap a() {
        return (NavigableMap) super.a();
    }

    public final Object ceiling(Object obj) {
        return ((NavigableMap) super.a()).ceilingKey(obj);
    }

    public final Iterator descendingIterator() {
        return descendingSet().iterator();
    }

    public final NavigableSet descendingSet() {
        return new C0028ba(this.c, ((NavigableMap) super.a()).descendingMap());
    }

    public final Object floor(Object obj) {
        return ((NavigableMap) super.a()).floorKey(obj);
    }

    public final NavigableSet headSet(Object obj, boolean z) {
        return new C0028ba(this.c, ((NavigableMap) super.a()).headMap(obj, z));
    }

    public final /* bridge */ /* synthetic */ SortedSet headSet(Object obj) {
        return headSet(obj, false);
    }

    public final Object higher(Object obj) {
        return ((NavigableMap) super.a()).higherKey(obj);
    }

    public final Object lower(Object obj) {
        return ((NavigableMap) super.a()).lowerKey(obj);
    }

    public final Object pollFirst() {
        return bV.b(iterator());
    }

    public final Object pollLast() {
        return bV.b(descendingIterator());
    }

    public final NavigableSet subSet(Object obj, boolean z, Object obj2, boolean z2) {
        return new C0028ba(this.c, ((NavigableMap) super.a()).subMap(obj, z, obj2, z2));
    }

    public final /* bridge */ /* synthetic */ SortedSet subSet(Object obj, Object obj2) {
        return subSet(obj, true, obj2, false);
    }

    public final NavigableSet tailSet(Object obj, boolean z) {
        return new C0028ba(this.c, ((NavigableMap) super.a()).tailMap(obj, z));
    }

    public final /* bridge */ /* synthetic */ SortedSet tailSet(Object obj) {
        return tailSet(obj, true);
    }
}

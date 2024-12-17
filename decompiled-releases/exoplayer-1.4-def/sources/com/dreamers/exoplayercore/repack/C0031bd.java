package com.dreamers.exoplayercore.repack;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.SortedSet;

/* renamed from: com.dreamers.exoplayercore.repack.bd  reason: case insensitive filesystem */
class C0031bd extends aX implements SortedSet {
    private /* synthetic */ aQ c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0031bd(aQ aQVar, SortedMap sortedMap) {
        super(aQVar, sortedMap);
        this.c = aQVar;
    }

    /* access modifiers changed from: package-private */
    public SortedMap a() {
        return (SortedMap) this.b;
    }

    public Comparator comparator() {
        return a().comparator();
    }

    public Object first() {
        return a().firstKey();
    }

    public SortedSet headSet(Object obj) {
        return new C0031bd(this.c, a().headMap(obj));
    }

    public Object last() {
        return a().lastKey();
    }

    public SortedSet subSet(Object obj, Object obj2) {
        return new C0031bd(this.c, a().subMap(obj, obj2));
    }

    public SortedSet tailSet(Object obj) {
        return new C0031bd(this.c, a().tailMap(obj));
    }
}

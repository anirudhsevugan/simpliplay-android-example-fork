package com.dreamers.exoplayercore.repack;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.SortedSet;

/* renamed from: com.dreamers.exoplayercore.repack.bc  reason: case insensitive filesystem */
class C0030bc extends C0001aT implements SortedMap {
    private SortedSet c;
    private /* synthetic */ aQ d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0030bc(aQ aQVar, SortedMap sortedMap) {
        super(aQVar, sortedMap);
        this.d = aQVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public SortedSet e() {
        return new C0031bd(this.d, d());
    }

    /* renamed from: c */
    public SortedSet keySet() {
        SortedSet sortedSet = this.c;
        if (sortedSet != null) {
            return sortedSet;
        }
        SortedSet b = e();
        this.c = b;
        return b;
    }

    public Comparator comparator() {
        return d().comparator();
    }

    /* access modifiers changed from: package-private */
    public SortedMap d() {
        return (SortedMap) this.a;
    }

    public Object firstKey() {
        return d().firstKey();
    }

    public SortedMap headMap(Object obj) {
        return new C0030bc(this.d, d().headMap(obj));
    }

    public Object lastKey() {
        return d().lastKey();
    }

    public SortedMap subMap(Object obj, Object obj2) {
        return new C0030bc(this.d, d().subMap(obj, obj2));
    }

    public SortedMap tailMap(Object obj) {
        return new C0030bc(this.d, d().tailMap(obj));
    }
}

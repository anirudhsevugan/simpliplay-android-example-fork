package com.dreamers.exoplayercore.repack;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

final class cG extends cJ {
    final /* synthetic */ Set a;
    final /* synthetic */ Set b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    cG(Set set, Set set2) {
        super((byte) 0);
        this.a = set;
        this.b = set2;
    }

    /* renamed from: a */
    public final cM iterator() {
        return new cH(this);
    }

    public final boolean contains(Object obj) {
        return this.a.contains(obj) && this.b.contains(obj);
    }

    public final boolean containsAll(Collection collection) {
        return this.a.containsAll(collection) && this.b.containsAll(collection);
    }

    public final boolean isEmpty() {
        return Collections.disjoint(this.b, this.a);
    }

    public final int size() {
        int i = 0;
        for (Object contains : this.a) {
            if (this.b.contains(contains)) {
                i++;
            }
        }
        return i;
    }
}

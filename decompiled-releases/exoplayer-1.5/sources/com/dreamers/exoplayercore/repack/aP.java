package com.dreamers.exoplayercore.repack;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

abstract class aP extends aQ implements bZ {
    protected aP(Map map) {
        super(map);
    }

    public final /* bridge */ /* synthetic */ Collection a(Object obj) {
        return (List) super.a(obj);
    }

    /* access modifiers changed from: package-private */
    public final Collection a(Object obj, Collection collection) {
        return a(obj, (List) collection, (C0032be) null);
    }

    /* access modifiers changed from: package-private */
    public final Collection a(Collection collection) {
        return Collections.unmodifiableList((List) collection);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract List c();

    public final boolean a(Object obj, Object obj2) {
        return super.a(obj, obj2);
    }

    public final Map b() {
        return super.b();
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

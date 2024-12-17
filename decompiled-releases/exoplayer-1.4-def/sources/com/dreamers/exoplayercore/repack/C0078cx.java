package com.dreamers.exoplayercore.repack;

import java.util.Map;

/* renamed from: com.dreamers.exoplayercore.repack.cx  reason: case insensitive filesystem */
final class C0078cx extends bU {
    private final transient bM a;
    /* access modifiers changed from: private */
    public final transient Object[] b;
    /* access modifiers changed from: private */
    public final transient int c;

    C0078cx(bM bMVar, Object[] objArr, int i) {
        this.a = bMVar;
        this.b = objArr;
        this.c = i;
    }

    static /* synthetic */ int i() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final int a(Object[] objArr, int i) {
        return e().a(objArr, i);
    }

    /* renamed from: a */
    public final cM iterator() {
        return e().listIterator(0);
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            return value != null && value.equals(this.a.get(key));
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean f() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public final bG h() {
        return new C0079cy(this);
    }

    public final int size() {
        return this.c;
    }
}

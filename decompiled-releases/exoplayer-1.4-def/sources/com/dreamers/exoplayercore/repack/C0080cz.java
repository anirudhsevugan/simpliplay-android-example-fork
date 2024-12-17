package com.dreamers.exoplayercore.repack;

/* renamed from: com.dreamers.exoplayercore.repack.cz  reason: case insensitive filesystem */
final class C0080cz extends bU {
    private final transient bM a;
    private final transient bG b;

    C0080cz(bM bMVar, bG bGVar) {
        this.a = bMVar;
        this.b = bGVar;
    }

    /* access modifiers changed from: package-private */
    public final int a(Object[] objArr, int i) {
        return this.b.a(objArr, i);
    }

    /* renamed from: a */
    public final cM iterator() {
        return this.b.listIterator(0);
    }

    public final boolean contains(Object obj) {
        return this.a.get(obj) != null;
    }

    public final bG e() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public final boolean f() {
        return true;
    }

    public final int size() {
        return this.a.size();
    }
}

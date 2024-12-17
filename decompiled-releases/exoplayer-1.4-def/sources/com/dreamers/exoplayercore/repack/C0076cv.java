package com.dreamers.exoplayercore.repack;

/* renamed from: com.dreamers.exoplayercore.repack.cv  reason: case insensitive filesystem */
final class C0076cv extends bG {
    static final bG a = new C0076cv(new Object[0], 0);
    private transient Object[] b;
    private final transient int c;

    C0076cv(Object[] objArr, int i) {
        this.b = objArr;
        this.c = i;
    }

    /* access modifiers changed from: package-private */
    public final int a(Object[] objArr, int i) {
        System.arraycopy(this.b, 0, objArr, i, this.c);
        return i + this.c;
    }

    /* access modifiers changed from: package-private */
    public final Object[] b() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public final int c() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final int d() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public final boolean f() {
        return false;
    }

    public final Object get(int i) {
        C0000a.b(i, this.c);
        return this.b[i];
    }

    public final int size() {
        return this.c;
    }
}

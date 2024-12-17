package com.dreamers.exoplayercore.repack;

final class cA extends bG {
    private final transient Object[] a;
    private final transient int b;
    private final transient int c;

    cA(Object[] objArr, int i, int i2) {
        this.a = objArr;
        this.b = i;
        this.c = i2;
    }

    /* access modifiers changed from: package-private */
    public final boolean f() {
        return true;
    }

    public final Object get(int i) {
        C0000a.b(i, this.c);
        return this.a[(i * 2) + this.b];
    }

    public final int size() {
        return this.c;
    }
}

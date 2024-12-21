package com.dreamers.exoplayercore.repack;

import java.util.Iterator;
import java.util.ListIterator;

final class bJ extends bG {
    private transient int a;
    private transient int b;
    private /* synthetic */ bG c;

    bJ(bG bGVar, int i, int i2) {
        this.c = bGVar;
        this.a = i;
        this.b = i2;
    }

    /* renamed from: a */
    public final bG subList(int i, int i2) {
        C0000a.a(i, i2, this.b);
        bG bGVar = this.c;
        int i3 = this.a;
        return bGVar.subList(i + i3, i2 + i3);
    }

    /* access modifiers changed from: package-private */
    public final Object[] b() {
        return this.c.b();
    }

    /* access modifiers changed from: package-private */
    public final int c() {
        return this.c.c() + this.a;
    }

    /* access modifiers changed from: package-private */
    public final int d() {
        return this.c.c() + this.a + this.b;
    }

    /* access modifiers changed from: package-private */
    public final boolean f() {
        return true;
    }

    public final Object get(int i) {
        C0000a.b(i, this.b);
        return this.c.get(i + this.a);
    }

    public final /* synthetic */ Iterator iterator() {
        return super.a();
    }

    public final /* synthetic */ ListIterator listIterator() {
        return super.h();
    }

    public final /* synthetic */ ListIterator listIterator(int i) {
        return super.listIterator(i);
    }

    public final int size() {
        return this.b;
    }
}

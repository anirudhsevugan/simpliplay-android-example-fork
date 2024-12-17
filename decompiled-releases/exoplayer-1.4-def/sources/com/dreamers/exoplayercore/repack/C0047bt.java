package com.dreamers.exoplayercore.repack;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: com.dreamers.exoplayercore.repack.bt  reason: case insensitive filesystem */
abstract class C0047bt implements Iterator {
    private int a;
    private int b;
    private int c;
    private /* synthetic */ C0042bo d;

    private C0047bt(C0042bo boVar) {
        this.d = boVar;
        this.a = this.d.c;
        this.b = this.d.isEmpty() ? -1 : 0;
        this.c = -1;
    }

    /* synthetic */ C0047bt(C0042bo boVar, byte b2) {
        this(boVar);
    }

    private void a() {
        if (this.d.c != this.a) {
            throw new ConcurrentModificationException();
        }
    }

    /* access modifiers changed from: package-private */
    public abstract Object a(int i);

    public boolean hasNext() {
        return this.b >= 0;
    }

    public Object next() {
        a();
        if (hasNext()) {
            int i = this.b;
            this.c = i;
            Object a2 = a(i);
            C0042bo boVar = this.d;
            int i2 = this.b + 1;
            if (i2 >= boVar.d) {
                i2 = -1;
            }
            this.b = i2;
            return a2;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        a();
        C0000a.c(this.c >= 0);
        this.a++;
        Object unused = this.d.a(this.d.a[this.c], (int) (this.d.f[this.c] >>> 32));
        this.b = C0042bo.b(this.b);
        this.c = -1;
    }
}

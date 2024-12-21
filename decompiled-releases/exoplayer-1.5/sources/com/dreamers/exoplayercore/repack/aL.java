package com.dreamers.exoplayercore.repack;

import java.util.NoSuchElementException;

abstract class aL extends cN {
    private final int a;
    private int b;

    protected aL(int i, int i2) {
        C0000a.c(i2, i);
        this.a = i;
        this.b = i2;
    }

    /* access modifiers changed from: protected */
    public abstract Object a(int i);

    public final boolean hasNext() {
        return this.b < this.a;
    }

    public final boolean hasPrevious() {
        return this.b > 0;
    }

    public final Object next() {
        if (hasNext()) {
            int i = this.b;
            this.b = i + 1;
            return a(i);
        }
        throw new NoSuchElementException();
    }

    public final int nextIndex() {
        return this.b;
    }

    public final Object previous() {
        if (hasPrevious()) {
            int i = this.b - 1;
            this.b = i;
            return a(i);
        }
        throw new NoSuchElementException();
    }

    public final int previousIndex() {
        return this.b - 1;
    }
}

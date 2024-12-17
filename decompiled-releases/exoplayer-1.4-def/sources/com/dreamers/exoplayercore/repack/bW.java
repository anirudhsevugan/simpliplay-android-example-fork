package com.dreamers.exoplayercore.repack;

import java.util.NoSuchElementException;

final class bW extends cM {
    private boolean a;
    private /* synthetic */ Object b;

    bW(Object obj) {
        this.b = obj;
    }

    public final boolean hasNext() {
        return !this.a;
    }

    public final Object next() {
        if (!this.a) {
            this.a = true;
            return this.b;
        }
        throw new NoSuchElementException();
    }
}

package com.dreamers.exoplayercore.repack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: com.dreamers.exoplayercore.repack.au  reason: case insensitive filesystem */
abstract class C0021au implements Iterator {
    private int a = C0023aw.b;
    private Object b;

    protected C0021au() {
    }

    /* access modifiers changed from: protected */
    public abstract Object a();

    /* access modifiers changed from: protected */
    public final Object b() {
        this.a = C0023aw.c;
        return null;
    }

    public final boolean hasNext() {
        C0000a.b(this.a != C0023aw.d);
        int[] iArr = C0022av.a;
        switch (this.a - 1) {
            case 0:
                return true;
            case 2:
                return false;
            default:
                this.a = C0023aw.d;
                this.b = a();
                if (this.a == C0023aw.c) {
                    return false;
                }
                this.a = C0023aw.a;
                return true;
        }
    }

    public final Object next() {
        if (hasNext()) {
            this.a = C0023aw.b;
            Object obj = this.b;
            this.b = null;
            return obj;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

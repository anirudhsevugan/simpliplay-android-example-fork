package com.dreamers.exoplayercore.repack;

import java.util.NoSuchElementException;

public abstract class aM extends cM {
    private int a = aO.b;
    private Object b;

    protected aM() {
    }

    /* access modifiers changed from: protected */
    public abstract Object a();

    /* access modifiers changed from: protected */
    public final Object b() {
        this.a = aO.c;
        return null;
    }

    public final boolean hasNext() {
        C0000a.b(this.a != aO.d);
        int[] iArr = aN.a;
        switch (this.a - 1) {
            case 0:
                return true;
            case 2:
                return false;
            default:
                this.a = aO.d;
                this.b = a();
                if (this.a == aO.c) {
                    return false;
                }
                this.a = aO.a;
                return true;
        }
    }

    public final Object next() {
        if (hasNext()) {
            this.a = aO.b;
            Object obj = this.b;
            this.b = null;
            return obj;
        }
        throw new NoSuchElementException();
    }
}

package com.dreamers.exoplayercore.repack;

import java.io.Serializable;

final class bF extends C0036bi implements Serializable {
    private Object a;
    private Object b;

    bF(Object obj, Object obj2) {
        this.a = obj;
        this.b = obj2;
    }

    public final Object getKey() {
        return this.a;
    }

    public final Object getValue() {
        return this.b;
    }

    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException();
    }
}

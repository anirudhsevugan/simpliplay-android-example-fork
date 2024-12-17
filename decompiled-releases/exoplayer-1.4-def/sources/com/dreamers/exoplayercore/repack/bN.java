package com.dreamers.exoplayercore.repack;

import java.util.Arrays;

public final class bN {
    int a;
    private Object[] b;

    public bN() {
        this(4);
    }

    bN(int i) {
        this.b = new Object[(i * 2)];
        this.a = 0;
    }

    public final bM a() {
        return C0077cw.a(this.a, this.b);
    }

    public final bN a(Object obj, Object obj2) {
        a(this.a + 1);
        C0000a.b(obj, obj2);
        Object[] objArr = this.b;
        int i = this.a;
        objArr[i * 2] = obj;
        objArr[(i * 2) + 1] = obj2;
        this.a = i + 1;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final void a(int i) {
        int i2 = i << 1;
        Object[] objArr = this.b;
        if (i2 > objArr.length) {
            this.b = Arrays.copyOf(objArr, bE.a(objArr.length, i2));
        }
    }
}

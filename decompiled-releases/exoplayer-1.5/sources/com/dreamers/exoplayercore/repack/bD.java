package com.dreamers.exoplayercore.repack;

import java.util.Arrays;

abstract class bD extends bE {
    Object[] a = new Object[4];
    int b = 0;
    boolean c;

    bD() {
        C0000a.a(4, "initialCapacity");
    }

    public bD a(Object obj) {
        C0000a.a(obj);
        int i = this.b + 1;
        Object[] objArr = this.a;
        if (objArr.length < i) {
            this.a = Arrays.copyOf(objArr, a(objArr.length, i));
        } else {
            if (this.c) {
                this.a = (Object[]) objArr.clone();
            }
            Object[] objArr2 = this.a;
            int i2 = this.b;
            this.b = i2 + 1;
            objArr2[i2] = obj;
            return this;
        }
        this.c = false;
        Object[] objArr22 = this.a;
        int i22 = this.b;
        this.b = i22 + 1;
        objArr22[i22] = obj;
        return this;
    }
}

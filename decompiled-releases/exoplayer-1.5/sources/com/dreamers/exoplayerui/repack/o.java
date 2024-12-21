package com.dreamers.exoplayerui.repack;

import java.util.Iterator;

public class o implements Iterable {
    public final int a;
    public final int b;

    static {
        new p((byte) 0);
    }

    public o(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public boolean a() {
        return this.a > this.b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof o)) {
            return false;
        }
        if (a() && ((o) obj).a()) {
            return true;
        }
        o oVar = (o) obj;
        return this.a == oVar.a && this.b == oVar.b;
    }

    public int hashCode() {
        if (a()) {
            return -1;
        }
        return (((this.a * 31) + this.b) * 31) + 1;
    }

    public /* synthetic */ Iterator iterator() {
        return new q(this.a, this.b, 1);
    }

    public String toString() {
        return this.a + ".." + this.b + " step 1";
    }
}

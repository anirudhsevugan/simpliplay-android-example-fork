package com.dreamers.exoplayerui.repack;

public final class r extends o {
    static {
        new s((byte) 0);
        new r(1, 0);
    }

    public r(int i, int i2) {
        super(i, i2);
    }

    public final boolean a() {
        return this.a > this.b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof r)) {
            return false;
        }
        if (a() && ((r) obj).a()) {
            return true;
        }
        r rVar = (r) obj;
        return this.a == rVar.a && this.b == rVar.b;
    }

    public final int hashCode() {
        if (a()) {
            return -1;
        }
        return (this.a * 31) + this.b;
    }

    public final String toString() {
        return this.a + ".." + this.b;
    }
}

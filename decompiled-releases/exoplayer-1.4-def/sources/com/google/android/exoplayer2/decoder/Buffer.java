package com.google.android.exoplayer2.decoder;

import gnu.expr.Declaration;

public abstract class Buffer {
    public int a;

    public void a() {
        this.a = 0;
    }

    public final void a_(int i) {
        this.a = i | this.a;
    }

    /* access modifiers changed from: protected */
    public final boolean b(int i) {
        return (this.a & i) == i;
    }

    public final boolean c() {
        return b(4);
    }

    public final boolean d() {
        return b(1);
    }

    public final boolean d_() {
        return b(Integer.MIN_VALUE);
    }

    public final boolean e() {
        return b(Declaration.IS_DYNAMIC);
    }
}

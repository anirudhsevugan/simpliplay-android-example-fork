package com.dreamers.exoplayercore.repack;

import android.graphics.Rect;
import android.view.View;

public abstract class D {
    protected final U a;
    int b;
    final Rect c;

    private D(U u) {
        this.b = Integer.MIN_VALUE;
        this.c = new Rect();
        this.a = u;
    }

    /* synthetic */ D(U u, byte b2) {
        this(u);
    }

    public final int a() {
        if (Integer.MIN_VALUE == this.b) {
            return 0;
        }
        return e() - this.b;
    }

    public abstract int a(View view);

    public abstract void a(int i);

    public abstract int b();

    public abstract int b(View view);

    public abstract int c();

    public abstract int c(View view);

    public abstract int d();

    public abstract int d(View view);

    public abstract int e();

    public abstract int e(View view);

    public abstract int f();

    public abstract int f(View view);

    public abstract int g();
}

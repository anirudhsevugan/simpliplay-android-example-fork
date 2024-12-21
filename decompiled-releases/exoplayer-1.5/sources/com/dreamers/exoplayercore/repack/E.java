package com.dreamers.exoplayercore.repack;

import android.view.View;

final class E extends D {
    E(U u) {
        super(u, (byte) 0);
    }

    public final int a(View view) {
        return U.j(view) - ((Y) view.getLayoutParams()).topMargin;
    }

    public final void a(int i) {
        U u = this.a;
        if (u.b != null) {
            F f = u.b;
            int a = f.g.a();
            for (int i2 = 0; i2 < a; i2++) {
                f.g.b(i2).offsetTopAndBottom(i);
            }
        }
    }

    public final int b() {
        return this.a.k();
    }

    public final int b(View view) {
        return U.l(view) + ((Y) view.getLayoutParams()).bottomMargin;
    }

    public final int c() {
        return this.a.k - this.a.m();
    }

    public final int c(View view) {
        this.a.a(view, this.c);
        return this.c.bottom;
    }

    public final int d() {
        return this.a.k;
    }

    public final int d(View view) {
        this.a.a(view, this.c);
        return this.c.top;
    }

    public final int e() {
        return (this.a.k - this.a.k()) - this.a.m();
    }

    public final int e(View view) {
        Y y = (Y) view.getLayoutParams();
        return U.h(view) + y.topMargin + y.bottomMargin;
    }

    public final int f() {
        return this.a.m();
    }

    public final int f(View view) {
        Y y = (Y) view.getLayoutParams();
        return U.g(view) + y.leftMargin + y.rightMargin;
    }

    public final int g() {
        return this.a.i;
    }
}

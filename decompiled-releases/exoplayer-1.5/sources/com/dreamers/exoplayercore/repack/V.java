package com.dreamers.exoplayercore.repack;

import android.view.View;

final class V implements C0017ap {
    private /* synthetic */ U a;

    V(U u) {
        this.a = u;
    }

    public final int a() {
        return this.a.j();
    }

    public final int a(View view) {
        return U.i(view) - ((Y) view.getLayoutParams()).leftMargin;
    }

    public final View a(int i) {
        return this.a.b(i);
    }

    public final int b() {
        return this.a.j - this.a.l();
    }

    public final int b(View view) {
        return U.k(view) + ((Y) view.getLayoutParams()).rightMargin;
    }
}

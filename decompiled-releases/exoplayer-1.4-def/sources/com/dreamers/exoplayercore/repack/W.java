package com.dreamers.exoplayercore.repack;

import android.view.View;

final class W implements C0017ap {
    private /* synthetic */ U a;

    W(U u) {
        this.a = u;
    }

    public final int a() {
        return this.a.k();
    }

    public final int a(View view) {
        return U.j(view) - ((Y) view.getLayoutParams()).topMargin;
    }

    public final View a(int i) {
        return this.a.b(i);
    }

    public final int b() {
        return this.a.k - this.a.m();
    }

    public final int b(View view) {
        return U.l(view) + ((Y) view.getLayoutParams()).bottomMargin;
    }
}

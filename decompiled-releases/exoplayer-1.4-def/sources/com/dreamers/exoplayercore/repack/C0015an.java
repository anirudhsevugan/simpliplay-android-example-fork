package com.dreamers.exoplayercore.repack;

import android.view.View;

/* renamed from: com.dreamers.exoplayercore.repack.an  reason: case insensitive filesystem */
final class C0015an {
    private C0017ap a;
    private C0016ao b = new C0016ao();

    C0015an(C0017ap apVar) {
        this.a = apVar;
    }

    /* access modifiers changed from: package-private */
    public final View a(int i, int i2, int i3, int i4) {
        int a2 = this.a.a();
        int b2 = this.a.b();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View a3 = this.a.a(i);
            this.b.a(a2, b2, this.a.a(a3), this.a.b(a3));
            if (i3 != 0) {
                this.b.a = 0;
                this.b.a(i3);
                if (this.b.a()) {
                    return a3;
                }
            }
            if (i4 != 0) {
                this.b.a = 0;
                this.b.a(i4);
                if (this.b.a()) {
                    view = a3;
                }
            }
            i += i5;
        }
        return view;
    }
}

package com.dreamers.exoplayercore.repack;

import android.view.View;

/* renamed from: com.dreamers.exoplayercore.repack.w  reason: case insensitive filesystem */
final class C0100w {
    D a;
    int b;
    int c;
    boolean d;
    boolean e;

    C0100w() {
        a();
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        this.b = -1;
        this.c = Integer.MIN_VALUE;
        this.d = false;
        this.e = false;
    }

    public final void a(View view, int i) {
        int a2 = this.a.a();
        if (a2 >= 0) {
            b(view, i);
            return;
        }
        this.b = i;
        if (this.d) {
            int c2 = (this.a.c() - a2) - this.a.b(view);
            this.c = this.a.c() - c2;
            if (c2 > 0) {
                int e2 = this.c - this.a.e(view);
                int b2 = this.a.b();
                int min = e2 - (b2 + Math.min(this.a.a(view) - b2, 0));
                if (min < 0) {
                    this.c += Math.min(c2, -min);
                    return;
                }
                return;
            }
            return;
        }
        int a3 = this.a.a(view);
        int b3 = a3 - this.a.b();
        this.c = a3;
        if (b3 > 0) {
            int c3 = (this.a.c() - Math.min(0, (this.a.c() - a2) - this.a.b(view))) - (a3 + this.a.e(view));
            if (c3 < 0) {
                this.c -= Math.min(b3, -c3);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void b() {
        this.c = this.d ? this.a.c() : this.a.b();
    }

    public final void b(View view, int i) {
        this.c = this.d ? this.a.b(view) + this.a.a() : this.a.a(view);
        this.b = i;
    }

    public final String toString() {
        return "AnchorInfo{mPosition=" + this.b + ", mCoordinate=" + this.c + ", mLayoutFromEnd=" + this.d + ", mValid=" + this.e + '}';
    }
}

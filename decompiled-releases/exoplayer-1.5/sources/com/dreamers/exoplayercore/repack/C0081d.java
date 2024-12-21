package com.dreamers.exoplayercore.repack;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.dreamers.exoplayercore.repack.d  reason: case insensitive filesystem */
final class C0081d {
    final C0083f a;
    final C0082e b = new C0082e();
    final List c = new ArrayList();

    C0081d(C0083f fVar) {
        this.a = fVar;
    }

    /* access modifiers changed from: package-private */
    public final int a() {
        return this.a.a() - this.c.size();
    }

    /* access modifiers changed from: package-private */
    public final int a(int i) {
        if (i < 0) {
            return -1;
        }
        int a2 = this.a.a();
        int i2 = i;
        while (i2 < a2) {
            int e = i - (i2 - this.b.e(i2));
            if (e == 0) {
                while (this.b.c(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += e;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public final void a(View view) {
        this.c.add(view);
        this.a.c(view);
    }

    /* access modifiers changed from: package-private */
    public final void a(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        int a2 = i < 0 ? this.a.a() : a(i);
        this.b.a(a2, z);
        if (z) {
            a(view);
        }
        this.a.a(view, a2, layoutParams);
    }

    /* access modifiers changed from: package-private */
    public final void a(View view, int i, boolean z) {
        int a2 = i < 0 ? this.a.a() : a(i);
        this.b.a(a2, z);
        if (z) {
            a(view);
        }
        this.a.a(view, a2);
    }

    /* access modifiers changed from: package-private */
    public final int b() {
        return this.a.a();
    }

    /* access modifiers changed from: package-private */
    public final View b(int i) {
        return this.a.b(a(i));
    }

    /* access modifiers changed from: package-private */
    public final boolean b(View view) {
        if (!this.c.remove(view)) {
            return false;
        }
        this.a.d(view);
        return true;
    }

    /* access modifiers changed from: package-private */
    public final int c(View view) {
        int a2 = this.a.a(view);
        if (a2 != -1 && !this.b.c(a2)) {
            return a2 - this.b.e(a2);
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public final View c(int i) {
        return this.a.b(i);
    }

    /* access modifiers changed from: package-private */
    public final void d(int i) {
        int a2 = a(i);
        this.b.d(a2);
        this.a.c(a2);
    }

    /* access modifiers changed from: package-private */
    public final boolean d(View view) {
        return this.c.contains(view);
    }

    public final String toString() {
        return this.b.toString() + ", hidden list:" + this.c.size();
    }
}

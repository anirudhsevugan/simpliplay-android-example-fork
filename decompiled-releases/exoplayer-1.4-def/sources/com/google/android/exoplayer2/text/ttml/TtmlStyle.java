package com.google.android.exoplayer2.text.ttml;

import android.text.Layout;

final class TtmlStyle {
    String a;
    int b;
    boolean c;
    int d;
    boolean e;
    int f = -1;
    int g = -1;
    int h = -1;
    int i = -1;
    int j = -1;
    float k;
    String l;
    int m = -1;
    int n = -1;
    Layout.Alignment o;
    Layout.Alignment p;
    int q = -1;
    TextEmphasis r;
    float s = Float.MAX_VALUE;

    public final int a() {
        int i2 = this.h;
        if (i2 == -1 && this.i == -1) {
            return -1;
        }
        int i3 = 0;
        int i4 = i2 == 1 ? 1 : 0;
        if (this.i == 1) {
            i3 = 2;
        }
        return i4 | i3;
    }

    public final TtmlStyle a(int i2) {
        this.b = i2;
        this.c = true;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final TtmlStyle a(TtmlStyle ttmlStyle) {
        int i2;
        Layout.Alignment alignment;
        Layout.Alignment alignment2;
        String str;
        if (ttmlStyle != null) {
            if (!this.c && ttmlStyle.c) {
                a(ttmlStyle.b);
            }
            if (this.h == -1) {
                this.h = ttmlStyle.h;
            }
            if (this.i == -1) {
                this.i = ttmlStyle.i;
            }
            if (this.a == null && (str = ttmlStyle.a) != null) {
                this.a = str;
            }
            if (this.f == -1) {
                this.f = ttmlStyle.f;
            }
            if (this.g == -1) {
                this.g = ttmlStyle.g;
            }
            if (this.n == -1) {
                this.n = ttmlStyle.n;
            }
            if (this.o == null && (alignment2 = ttmlStyle.o) != null) {
                this.o = alignment2;
            }
            if (this.p == null && (alignment = ttmlStyle.p) != null) {
                this.p = alignment;
            }
            if (this.q == -1) {
                this.q = ttmlStyle.q;
            }
            if (this.j == -1) {
                this.j = ttmlStyle.j;
                this.k = ttmlStyle.k;
            }
            if (this.r == null) {
                this.r = ttmlStyle.r;
            }
            if (this.s == Float.MAX_VALUE) {
                this.s = ttmlStyle.s;
            }
            if (!this.e && ttmlStyle.e) {
                b(ttmlStyle.d);
            }
            if (this.m == -1 && (i2 = ttmlStyle.m) != -1) {
                this.m = i2;
            }
        }
        return this;
    }

    public final TtmlStyle a(boolean z) {
        this.f = z ? 1 : 0;
        return this;
    }

    public final TtmlStyle b(int i2) {
        this.d = i2;
        this.e = true;
        return this;
    }

    public final TtmlStyle b(boolean z) {
        this.g = z ? 1 : 0;
        return this;
    }

    public final TtmlStyle c(boolean z) {
        this.q = z ? 1 : 0;
        return this;
    }
}

package com.dreamers.exoplayercore.repack;

/* renamed from: com.dreamers.exoplayercore.repack.ao  reason: case insensitive filesystem */
final class C0016ao {
    int a = 0;
    private int b;
    private int c;
    private int d;
    private int e;

    C0016ao() {
    }

    private static int a(int i, int i2) {
        if (i > i2) {
            return 1;
        }
        return i == i2 ? 2 : 4;
    }

    /* access modifiers changed from: package-private */
    public final void a(int i) {
        this.a = i | this.a;
    }

    /* access modifiers changed from: package-private */
    public final void a(int i, int i2, int i3, int i4) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
    }

    /* access modifiers changed from: package-private */
    public final boolean a() {
        int i = this.a;
        if ((i & 7) != 0 && (i & a(this.d, this.b)) == 0) {
            return false;
        }
        int i2 = this.a;
        if ((i2 & 112) != 0 && (i2 & (a(this.d, this.c) << 4)) == 0) {
            return false;
        }
        int i3 = this.a;
        if ((i3 & 1792) != 0 && (i3 & (a(this.e, this.b) << 8)) == 0) {
            return false;
        }
        int i4 = this.a;
        return (i4 & 28672) == 0 || (i4 & (a(this.e, this.c) << 12)) != 0;
    }
}

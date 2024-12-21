package com.dreamers.exoplayercore.repack;

import java.util.Arrays;

/* renamed from: com.dreamers.exoplayercore.repack.t  reason: case insensitive filesystem */
final class C0097t implements X {
    int a;
    int b;
    int[] c;
    int d;

    C0097t() {
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        int[] iArr = this.c;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        this.d = 0;
    }

    public final void a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("Layout positions must be non-negative");
        } else if (i2 >= 0) {
            int i3 = this.d << 1;
            int[] iArr = this.c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i3 >= iArr.length) {
                int[] iArr3 = new int[(i3 << 1)];
                this.c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.c;
            iArr4[i3] = i;
            iArr4[i3 + 1] = i2;
            this.d++;
        } else {
            throw new IllegalArgumentException("Pixel distance must be non-negative");
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(F f, boolean z) {
        this.d = 0;
        int[] iArr = this.c;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        U u = f.l;
        if (f.k != null && u != null && u.e) {
            if (z) {
                if (!f.f.d()) {
                    u.a(f.k.getItemCount(), (X) this);
                }
            } else if (!f.i()) {
                u.a(this.a, this.b, f.u, this);
            }
            if (this.d > u.f) {
                u.f = this.d;
                u.g = z;
                f.e.b();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean a(int i) {
        if (this.c != null) {
            int i2 = this.d << 1;
            for (int i3 = 0; i3 < i2; i3 += 2) {
                if (this.c[i3] == i) {
                    return true;
                }
            }
        }
        return false;
    }
}

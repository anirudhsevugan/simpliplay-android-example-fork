package com.google.android.exoplayer2.util;

import com.google.appinventor.components.runtime.util.Ev3Constants;

public final class ParsableNalUnitBitArray {
    private byte[] a;
    private int b;
    private int c;
    private int d;

    public ParsableNalUnitBitArray(byte[] bArr, int i, int i2) {
        a(bArr, i, i2);
    }

    private boolean d(int i) {
        if (2 > i || i >= this.b) {
            return false;
        }
        byte[] bArr = this.a;
        return bArr[i] == 3 && bArr[i + -2] == 0 && bArr[i - 1] == 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r2.b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void g() {
        /*
            r2 = this;
            int r0 = r2.c
            if (r0 < 0) goto L_0x0010
            int r1 = r2.b
            if (r0 < r1) goto L_0x000e
            if (r0 != r1) goto L_0x0010
            int r0 = r2.d
            if (r0 != 0) goto L_0x0010
        L_0x000e:
            r0 = 1
            goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.ParsableNalUnitBitArray.g():void");
    }

    public final void a() {
        int i = 1;
        int i2 = this.d + 1;
        this.d = i2;
        if (i2 == 8) {
            this.d = 0;
            int i3 = this.c;
            if (d(i3 + 1)) {
                i = 2;
            }
            this.c = i3 + i;
        }
        g();
    }

    public final void a(int i) {
        int i2 = this.c;
        int i3 = i / 8;
        int i4 = i2 + i3;
        this.c = i4;
        int i5 = this.d + (i - (i3 << 3));
        this.d = i5;
        if (i5 > 7) {
            this.c = i4 + 1;
            this.d = i5 - 8;
        }
        while (true) {
            i2++;
            if (i2 > this.c) {
                g();
                return;
            } else if (d(i2)) {
                this.c++;
                i2 += 2;
            }
        }
    }

    public final void a(byte[] bArr, int i, int i2) {
        this.a = bArr;
        this.c = i;
        this.b = i2;
        this.d = 0;
        g();
    }

    public final boolean b() {
        boolean z = (this.a[this.c] & (128 >> this.d)) != 0;
        a();
        return z;
    }

    public final boolean b(int i) {
        int i2 = this.c;
        int i3 = i / 8;
        int i4 = i2 + i3;
        int i5 = (this.d + i) - (i3 << 3);
        if (i5 > 7) {
            i4++;
            i5 -= 8;
        }
        while (true) {
            i2++;
            if (i2 > i4 || i4 >= this.b) {
                int i6 = this.b;
            } else if (d(i2)) {
                i4++;
                i2 += 2;
            }
        }
        int i62 = this.b;
        if (i4 >= i62) {
            return i4 == i62 && i5 == 0;
        }
        return true;
    }

    public final int c(int i) {
        int i2;
        int i3;
        this.d += i;
        int i4 = 0;
        while (true) {
            i2 = this.d;
            i3 = 2;
            if (i2 <= 8) {
                break;
            }
            int i5 = i2 - 8;
            this.d = i5;
            byte[] bArr = this.a;
            int i6 = this.c;
            i4 |= (bArr[i6] & Ev3Constants.Opcode.TST) << i5;
            if (!d(i6 + 1)) {
                i3 = 1;
            }
            this.c = i6 + i3;
        }
        byte[] bArr2 = this.a;
        int i7 = this.c;
        int i8 = (-1 >>> (32 - i)) & (i4 | ((bArr2[i7] & Ev3Constants.Opcode.TST) >> (8 - i2)));
        if (i2 == 8) {
            this.d = 0;
            if (!d(i7 + 1)) {
                i3 = 1;
            }
            this.c = i7 + i3;
        }
        g();
        return i8;
    }

    public final boolean c() {
        int i = this.c;
        int i2 = this.d;
        int i3 = 0;
        while (this.c < this.b && !b()) {
            i3++;
        }
        boolean z = this.c == this.b;
        this.c = i;
        this.d = i2;
        return !z && b((i3 << 1) + 1);
    }

    public final int d() {
        return f();
    }

    public final int e() {
        int f = f();
        return (f % 2 == 0 ? -1 : 1) * ((f + 1) / 2);
    }

    public final int f() {
        int i = 0;
        int i2 = 0;
        while (!b()) {
            i2++;
        }
        int i3 = (1 << i2) - 1;
        if (i2 > 0) {
            i = c(i2);
        }
        return i3 + i;
    }
}

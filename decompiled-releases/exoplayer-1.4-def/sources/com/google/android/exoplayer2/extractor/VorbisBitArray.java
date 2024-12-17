package com.google.android.exoplayer2.extractor;

import com.google.appinventor.components.runtime.util.Ev3Constants;

public final class VorbisBitArray {
    int a;
    int b;
    private final byte[] c;
    private final int d;

    public VorbisBitArray(byte[] bArr) {
        this.c = bArr;
        this.d = bArr.length;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r2.d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b() {
        /*
            r2 = this;
            int r0 = r2.a
            if (r0 < 0) goto L_0x0010
            int r1 = r2.d
            if (r0 < r1) goto L_0x000e
            if (r0 != r1) goto L_0x0010
            int r0 = r2.b
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.VorbisBitArray.b():void");
    }

    public final int a(int i) {
        int i2 = this.a;
        int min = Math.min(i, 8 - this.b);
        int i3 = i2 + 1;
        int i4 = ((this.c[i2] & Ev3Constants.Opcode.TST) >> this.b) & (255 >> (8 - min));
        while (min < i) {
            i4 |= (this.c[i3] & Ev3Constants.Opcode.TST) << min;
            min += 8;
            i3++;
        }
        int i5 = i4 & (-1 >>> (32 - i));
        b(i);
        return i5;
    }

    public final boolean a() {
        boolean z = (((this.c[this.a] & Ev3Constants.Opcode.TST) >> this.b) & 1) == 1;
        b(1);
        return z;
    }

    public final void b(int i) {
        int i2 = i / 8;
        int i3 = this.a + i2;
        this.a = i3;
        int i4 = this.b + (i - (i2 << 3));
        this.b = i4;
        if (i4 > 7) {
            this.a = i3 + 1;
            this.b = i4 - 8;
        }
        b();
    }
}

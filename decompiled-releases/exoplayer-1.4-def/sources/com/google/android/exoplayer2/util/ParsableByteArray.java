package com.google.android.exoplayer2.util;

import com.dreamers.exoplayercore.repack.aC;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.nio.charset.Charset;
import java.util.Arrays;

public final class ParsableByteArray {
    public byte[] a;
    public int b;
    public int c;

    public ParsableByteArray() {
        this.a = Util.f;
    }

    public ParsableByteArray(int i) {
        this.a = new byte[i];
        this.c = i;
    }

    public ParsableByteArray(byte[] bArr) {
        this.a = bArr;
        this.c = bArr.length;
    }

    public ParsableByteArray(byte[] bArr, int i) {
        this.a = bArr;
        this.c = i;
    }

    public final int a() {
        return this.c - this.b;
    }

    public final String a(int i, Charset charset) {
        String str = new String(this.a, this.b, i, charset);
        this.b += i;
        return str;
    }

    public final void a(int i) {
        byte[] bArr = this.a;
        if (bArr.length < i) {
            bArr = new byte[i];
        }
        a(bArr, i);
    }

    public final void a(ParsableBitArray parsableBitArray, int i) {
        a(parsableBitArray.a, 0, i);
        parsableBitArray.a(0);
    }

    public final void a(byte[] bArr, int i) {
        this.a = bArr;
        this.c = i;
        this.b = 0;
    }

    public final void a(byte[] bArr, int i, int i2) {
        System.arraycopy(this.a, this.b, bArr, i, i2);
        this.b += i2;
    }

    public final int b() {
        return this.a[this.b] & Ev3Constants.Opcode.TST;
    }

    public final void b(int i) {
        byte[] bArr = this.a;
        if (i > bArr.length) {
            this.a = Arrays.copyOf(bArr, i);
        }
    }

    public final int c() {
        byte[] bArr = this.a;
        int i = this.b;
        this.b = i + 1;
        return bArr[i] & Ev3Constants.Opcode.TST;
    }

    public final void c(int i) {
        Assertions.a(i >= 0 && i <= this.a.length);
        this.c = i;
    }

    public final int d() {
        byte[] bArr = this.a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        this.b = i2 + 1;
        return (bArr[i2] & Ev3Constants.Opcode.TST) | ((bArr[i] & Ev3Constants.Opcode.TST) << 8);
    }

    public final void d(int i) {
        Assertions.a(i >= 0 && i <= this.c);
        this.b = i;
    }

    public final int e() {
        byte[] bArr = this.a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        byte b2 = bArr[i] & Ev3Constants.Opcode.TST;
        this.b = i2 + 1;
        return ((bArr[i2] & Ev3Constants.Opcode.TST) << 8) | b2;
    }

    public final void e(int i) {
        d(this.b + i);
    }

    public final String f(int i) {
        return a(i, aC.c);
    }

    public final short f() {
        byte[] bArr = this.a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        byte b2 = bArr[i] & Ev3Constants.Opcode.TST;
        this.b = i2 + 1;
        return (short) (((bArr[i2] & Ev3Constants.Opcode.TST) << 8) | b2);
    }

    public final int g() {
        byte[] bArr = this.a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        int i3 = i2 + 1;
        this.b = i3;
        byte b2 = ((bArr[i] & Ev3Constants.Opcode.TST) << 16) | ((bArr[i2] & Ev3Constants.Opcode.TST) << 8);
        this.b = i3 + 1;
        return (bArr[i3] & Ev3Constants.Opcode.TST) | b2;
    }

    public final String g(int i) {
        if (i == 0) {
            return "";
        }
        int i2 = this.b;
        int i3 = (i2 + i) - 1;
        String a2 = Util.a(this.a, i2, (i3 >= this.c || this.a[i3] != 0) ? i : i - 1);
        this.b += i;
        return a2;
    }

    public final long h() {
        byte[] bArr = this.a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        int i3 = i2 + 1;
        this.b = i3;
        int i4 = i3 + 1;
        this.b = i4;
        this.b = i4 + 1;
        return (((long) bArr[i4]) & 255) | ((((long) bArr[i]) & 255) << 24) | ((((long) bArr[i2]) & 255) << 16) | ((((long) bArr[i3]) & 255) << 8);
    }

    public final long i() {
        byte[] bArr = this.a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        int i3 = i2 + 1;
        this.b = i3;
        int i4 = i3 + 1;
        this.b = i4;
        this.b = i4 + 1;
        return ((((long) bArr[i4]) & 255) << 24) | (((long) bArr[i]) & 255) | ((((long) bArr[i2]) & 255) << 8) | ((((long) bArr[i3]) & 255) << 16);
    }

    public final int j() {
        byte[] bArr = this.a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        int i3 = i2 + 1;
        this.b = i3;
        byte b2 = ((bArr[i] & Ev3Constants.Opcode.TST) << 24) | ((bArr[i2] & Ev3Constants.Opcode.TST) << 16);
        int i4 = i3 + 1;
        this.b = i4;
        byte b3 = b2 | ((bArr[i3] & Ev3Constants.Opcode.TST) << 8);
        this.b = i4 + 1;
        return (bArr[i4] & Ev3Constants.Opcode.TST) | b3;
    }

    public final int k() {
        byte[] bArr = this.a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        byte b2 = bArr[i] & Ev3Constants.Opcode.TST;
        int i3 = i2 + 1;
        this.b = i3;
        byte b3 = b2 | ((bArr[i2] & Ev3Constants.Opcode.TST) << 8);
        int i4 = i3 + 1;
        this.b = i4;
        byte b4 = b3 | ((bArr[i3] & Ev3Constants.Opcode.TST) << 16);
        this.b = i4 + 1;
        return ((bArr[i4] & Ev3Constants.Opcode.TST) << 24) | b4;
    }

    public final long l() {
        byte[] bArr = this.a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        int i3 = i2 + 1;
        this.b = i3;
        int i4 = i3 + 1;
        this.b = i4;
        int i5 = i4 + 1;
        this.b = i5;
        int i6 = i5 + 1;
        this.b = i6;
        int i7 = i6 + 1;
        this.b = i7;
        int i8 = i7 + 1;
        this.b = i8;
        this.b = i8 + 1;
        return (((long) bArr[i8]) & 255) | ((((long) bArr[i]) & 255) << 56) | ((((long) bArr[i2]) & 255) << 48) | ((((long) bArr[i3]) & 255) << 40) | ((((long) bArr[i4]) & 255) << 32) | ((((long) bArr[i5]) & 255) << 24) | ((((long) bArr[i6]) & 255) << 16) | ((((long) bArr[i7]) & 255) << 8);
    }

    public final long m() {
        byte[] bArr = this.a;
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        int i3 = i2 + 1;
        this.b = i3;
        int i4 = i3 + 1;
        this.b = i4;
        int i5 = i4 + 1;
        this.b = i5;
        int i6 = i5 + 1;
        this.b = i6;
        int i7 = i6 + 1;
        this.b = i7;
        int i8 = i7 + 1;
        this.b = i8;
        this.b = i8 + 1;
        return ((((long) bArr[i8]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i2]) & 255) << 8) | ((((long) bArr[i3]) & 255) << 16) | ((((long) bArr[i4]) & 255) << 24) | ((((long) bArr[i5]) & 255) << 32) | ((((long) bArr[i6]) & 255) << 40) | ((((long) bArr[i7]) & 255) << 48);
    }

    public final int n() {
        return (c() << 21) | (c() << 14) | (c() << 7) | c();
    }

    public final int o() {
        int j = j();
        if (j >= 0) {
            return j;
        }
        throw new IllegalStateException(new StringBuilder(29).append("Top bit not zero: ").append(j).toString());
    }

    public final int p() {
        int k = k();
        if (k >= 0) {
            return k;
        }
        throw new IllegalStateException(new StringBuilder(29).append("Top bit not zero: ").append(k).toString());
    }

    public final long q() {
        long l = l();
        if (l >= 0) {
            return l;
        }
        throw new IllegalStateException(new StringBuilder(38).append("Top bit not zero: ").append(l).toString());
    }

    public final String r() {
        if (a() == 0) {
            return null;
        }
        int i = this.b;
        while (i < this.c && this.a[i] != 0) {
            i++;
        }
        byte[] bArr = this.a;
        int i2 = this.b;
        String a2 = Util.a(bArr, i2, i - i2);
        this.b = i;
        if (i < this.c) {
            this.b = i + 1;
        }
        return a2;
    }

    public final String s() {
        if (a() == 0) {
            return null;
        }
        int i = this.b;
        while (i < this.c && !Util.a((int) this.a[i])) {
            i++;
        }
        int i2 = this.b;
        if (i - i2 >= 3) {
            byte[] bArr = this.a;
            if (bArr[i2] == -17 && bArr[i2 + 1] == -69 && bArr[i2 + 2] == -65) {
                this.b = i2 + 3;
            }
        }
        byte[] bArr2 = this.a;
        int i3 = this.b;
        String a2 = Util.a(bArr2, i3, i - i3);
        this.b = i;
        int i4 = this.c;
        if (i == i4) {
            return a2;
        }
        byte[] bArr3 = this.a;
        if (bArr3[i] == 13) {
            int i5 = i + 1;
            this.b = i5;
            if (i5 == i4) {
                return a2;
            }
        }
        int i6 = this.b;
        if (bArr3[i6] == 10) {
            this.b = i6 + 1;
        }
        return a2;
    }

    public final long t() {
        int i;
        int i2;
        long j = (long) this.a[this.b];
        int i3 = 7;
        while (true) {
            i = 1;
            if (i3 < 0) {
                break;
            }
            int i4 = 1 << i3;
            if ((((long) i4) & j) != 0) {
                i3--;
            } else if (i3 < 6) {
                j &= (long) (i4 - 1);
                i2 = 7 - i3;
            } else if (i3 == 7) {
                i2 = 1;
            }
        }
        i2 = 0;
        if (i2 != 0) {
            while (i < i2) {
                byte b2 = this.a[this.b + i];
                if ((b2 & Ev3Constants.Opcode.FILE) == 128) {
                    j = (j << 6) | ((long) (b2 & Ev3Constants.Opcode.MOVEF_F));
                    i++;
                } else {
                    throw new NumberFormatException(new StringBuilder(62).append("Invalid UTF-8 sequence continuation byte: ").append(j).toString());
                }
            }
            this.b += i2;
            return j;
        }
        throw new NumberFormatException(new StringBuilder(55).append("Invalid UTF-8 sequence first byte: ").append(j).toString());
    }
}

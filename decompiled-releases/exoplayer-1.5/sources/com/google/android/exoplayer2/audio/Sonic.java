package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.util.Assertions;
import java.util.Arrays;

final class Sonic {
    final int a;
    final float b;
    final float c;
    final float d;
    final int e;
    short[] f;
    int g;
    short[] h;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int q;
    private final int r;
    private final int s;
    private final int t;
    private final short[] u;
    private short[] v;

    public Sonic(int i2, int i3, float f2, float f3, int i4) {
        this.r = i2;
        this.a = i3;
        this.b = f2;
        this.c = f3;
        this.d = ((float) i2) / ((float) i4);
        this.s = i2 / 400;
        int i5 = i2 / 65;
        this.t = i5;
        int i6 = i5 * 2;
        this.e = i6;
        this.u = new short[i6];
        this.f = new short[(i6 * i3)];
        this.h = new short[(i6 * i3)];
        this.v = new short[(i6 * i3)];
    }

    private int a(short[] sArr, int i2, int i3, int i4) {
        int i5 = i2 * this.a;
        int i6 = 255;
        int i7 = 1;
        int i8 = 0;
        int i9 = 0;
        while (i3 <= i4) {
            int i10 = 0;
            for (int i11 = 0; i11 < i3; i11++) {
                i10 += Math.abs(sArr[i5 + i11] - sArr[(i5 + i3) + i11]);
            }
            if (i10 * i8 < i7 * i3) {
                i8 = i3;
                i7 = i10;
            }
            if (i10 * i6 > i9 * i3) {
                i6 = i3;
                i9 = i10;
            }
            i3++;
        }
        this.p = i7 / i8;
        this.q = i9 / i6;
        return i8;
    }

    private static void a(int i2, int i3, short[] sArr, int i4, short[] sArr2, int i5, short[] sArr3, int i6) {
        for (int i7 = 0; i7 < i3; i7++) {
            int i8 = (i4 * i3) + i7;
            int i9 = (i6 * i3) + i7;
            int i10 = (i5 * i3) + i7;
            for (int i11 = 0; i11 < i2; i11++) {
                sArr[i8] = (short) (((sArr2[i10] * (i2 - i11)) + (sArr3[i9] * i11)) / i2);
                i8 += i3;
                i10 += i3;
                i9 += i3;
            }
        }
    }

    private void b(short[] sArr, int i2, int i3) {
        short[] a2 = a(this.h, this.i, i3);
        this.h = a2;
        int i4 = this.a;
        System.arraycopy(sArr, i2 * i4, a2, this.i * i4, i4 * i3);
        this.i += i3;
    }

    private void c(short[] sArr, int i2, int i3) {
        int i4 = this.e / i3;
        int i5 = this.a;
        int i6 = i3 * i5;
        int i7 = i2 * i5;
        for (int i8 = 0; i8 < i4; i8++) {
            int i9 = 0;
            for (int i10 = 0; i10 < i6; i10++) {
                i9 += sArr[(i8 * i6) + i7 + i10];
            }
            this.u[i8] = (short) (i9 / i6);
        }
    }

    public final int a() {
        return (this.i * this.a) << 1;
    }

    /* access modifiers changed from: package-private */
    public final short[] a(short[] sArr, int i2, int i3) {
        int length = sArr.length;
        int i4 = this.a;
        int i5 = length / i4;
        return i2 + i3 <= i5 ? sArr : Arrays.copyOf(sArr, (((i5 * 3) / 2) + i3) * i4);
    }

    /* access modifiers changed from: package-private */
    public final void b() {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = this.i;
        float f2 = this.b;
        float f3 = this.c;
        float f4 = f2 / f3;
        float f5 = this.d * f3;
        double d2 = (double) f4;
        float f6 = 1.0f;
        int i9 = 1;
        if (d2 > 1.00001d || d2 < 0.99999d) {
            int i10 = this.g;
            if (i10 >= this.e) {
                int i11 = 0;
                while (true) {
                    int i12 = this.m;
                    if (i12 > 0) {
                        int min = Math.min(this.e, i12);
                        b(this.f, i11, min);
                        this.m -= min;
                        i11 += min;
                    } else {
                        short[] sArr = this.f;
                        int i13 = this.r;
                        int i14 = i13 > 4000 ? i13 / 4000 : 1;
                        if (this.a == i9 && i14 == i9) {
                            i5 = a(sArr, i11, this.s, this.t);
                        } else {
                            c(sArr, i11, i14);
                            int a2 = a(this.u, 0, this.s / i14, this.t / i14);
                            if (i14 != i9) {
                                int i15 = a2 * i14;
                                int i16 = i14 << 2;
                                int i17 = i15 - i16;
                                int i18 = i15 + i16;
                                int i19 = this.s;
                                if (i17 < i19) {
                                    i17 = i19;
                                }
                                int i20 = this.t;
                                if (i18 > i20) {
                                    i18 = i20;
                                }
                                if (this.a == i9) {
                                    i5 = a(sArr, i11, i17, i18);
                                } else {
                                    c(sArr, i11, i9);
                                    i5 = a(this.u, 0, i17, i18);
                                }
                            } else {
                                i5 = a2;
                            }
                        }
                        int i21 = this.p;
                        int i22 = i21 != 0 && this.n != 0 && this.q <= i21 * 3 && (i21 << 1) > this.o * 3 ? this.n : i5;
                        this.o = i21;
                        this.n = i5;
                        if (d2 > 1.0d) {
                            short[] sArr2 = this.f;
                            if (f4 >= 2.0f) {
                                i7 = (int) (((float) i22) / (f4 - f6));
                            } else {
                                this.m = (int) ((((float) i22) * (2.0f - f4)) / (f4 - f6));
                                i7 = i22;
                            }
                            short[] a3 = a(this.h, this.i, i7);
                            this.h = a3;
                            int i23 = i7;
                            short[] sArr3 = sArr2;
                            a(i7, this.a, a3, this.i, sArr3, i11, sArr3, i11 + i22);
                            this.i += i23;
                            i11 += i22 + i23;
                        } else {
                            int i24 = i22;
                            short[] sArr4 = this.f;
                            if (f4 < 0.5f) {
                                i6 = (int) ((((float) i24) * f4) / (f6 - f4));
                            } else {
                                this.m = (int) ((((float) i24) * ((2.0f * f4) - f6)) / (f6 - f4));
                                i6 = i24;
                            }
                            int i25 = i24 + i6;
                            short[] a4 = a(this.h, this.i, i25);
                            this.h = a4;
                            int i26 = this.a;
                            System.arraycopy(sArr4, i11 * i26, a4, this.i * i26, i26 * i24);
                            a(i6, this.a, this.h, this.i + i24, sArr4, i11 + i24, sArr4, i11);
                            this.i += i25;
                            i11 += i6;
                        }
                    }
                    if (this.e + i11 > i10) {
                        break;
                    }
                    f6 = 1.0f;
                    i9 = 1;
                }
                int i27 = this.g - i11;
                short[] sArr5 = this.f;
                int i28 = this.a;
                System.arraycopy(sArr5, i11 * i28, sArr5, 0, i28 * i27);
                this.g = i27;
            }
        } else {
            b(this.f, 0, this.g);
            this.g = 0;
        }
        if (f5 != 1.0f && this.i != i8) {
            int i29 = this.r;
            int i30 = (int) (((float) i29) / f5);
            while (true) {
                if (i30 <= 16384 && i29 <= 16384) {
                    break;
                }
                i30 /= 2;
                i29 /= 2;
            }
            int i31 = this.i - i8;
            short[] a5 = a(this.v, this.j, i31);
            this.v = a5;
            short[] sArr6 = this.h;
            int i32 = this.a;
            System.arraycopy(sArr6, i8 * i32, a5, this.j * i32, i32 * i31);
            this.i = i8;
            this.j += i31;
            int i33 = 0;
            while (true) {
                i2 = this.j;
                if (i33 >= i2 - 1) {
                    break;
                }
                while (true) {
                    i3 = this.k;
                    int i34 = (i3 + 1) * i30;
                    i4 = this.l;
                    if (i34 <= i4 * i29) {
                        break;
                    }
                    this.h = a(this.h, this.i, 1);
                    int i35 = 0;
                    while (true) {
                        int i36 = this.a;
                        if (i35 >= i36) {
                            break;
                        }
                        short[] sArr7 = this.v;
                        int i37 = (i33 * i36) + i35;
                        short s2 = sArr7[i37];
                        short s3 = sArr7[i37 + i36];
                        int i38 = this.k;
                        int i39 = i38 * i30;
                        int i40 = (i38 + 1) * i30;
                        int i41 = i40 - (this.l * i29);
                        int i42 = i40 - i39;
                        this.h[(this.i * i36) + i35] = (short) (((s2 * i41) + ((i42 - i41) * s3)) / i42);
                        i35++;
                    }
                    this.l++;
                    this.i++;
                }
                int i43 = i3 + 1;
                this.k = i43;
                if (i43 == i29) {
                    this.k = 0;
                    Assertions.b(i4 == i30);
                    this.l = 0;
                }
                i33++;
            }
            int i44 = i2 - 1;
            if (i44 != 0) {
                short[] sArr8 = this.v;
                int i45 = this.a;
                System.arraycopy(sArr8, i44 * i45, sArr8, 0, (i2 - i44) * i45);
                this.j -= i44;
            }
        }
    }
}

package com.google.android.exoplayer2.util;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class NalUnitUtil {
    public static final byte[] a = {0, 0, 0, 1};
    public static final float[] b = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    private static final Object c = new Object();
    private static int[] d = new int[10];

    public final class PpsData {
        public final int a;
        public final int b;
        public final boolean c;

        public PpsData(int i, int i2, boolean z) {
            this.a = i;
            this.b = i2;
            this.c = z;
        }
    }

    public final class SpsData {
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;
        public final float g;
        public final boolean h;
        public final boolean i;
        public final int j;
        public final int k;
        public final int l;
        public final boolean m;

        public SpsData(int i2, int i3, int i4, int i5, int i6, int i7, float f2, boolean z, boolean z2, int i8, int i9, int i10, boolean z3) {
            this.a = i2;
            this.b = i3;
            this.c = i4;
            this.d = i5;
            this.e = i6;
            this.f = i7;
            this.g = f2;
            this.h = z;
            this.i = z2;
            this.j = i8;
            this.k = i9;
            this.l = i10;
            this.m = z3;
        }
    }

    public static int a(byte[] bArr, int i) {
        int i2;
        synchronized (c) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < i) {
                while (true) {
                    if (i3 < i - 2) {
                        if (bArr[i3] == 0 && bArr[i3 + 1] == 0 && bArr[i3 + 2] == 3) {
                            break;
                        }
                        i3++;
                    } else {
                        i3 = i;
                        break;
                    }
                }
                if (i3 < i) {
                    int[] iArr = d;
                    if (iArr.length <= i4) {
                        d = Arrays.copyOf(iArr, iArr.length << 1);
                    }
                    d[i4] = i3;
                    i3 += 3;
                    i4++;
                }
            }
            i2 = i - i4;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < i4; i7++) {
                int i8 = d[i7] - i6;
                System.arraycopy(bArr, i6, bArr, i5, i8);
                int i9 = i5 + i8;
                int i10 = i9 + 1;
                bArr[i9] = 0;
                i5 = i10 + 1;
                bArr[i10] = 0;
                i6 += i8 + 3;
            }
            System.arraycopy(bArr, i6, bArr, i5, i2 - i5);
        }
        return i2;
    }

    public static int a(byte[] bArr, int i, int i2, boolean[] zArr) {
        int i3 = i2 - i;
        boolean z = false;
        Assertions.b(i3 >= 0);
        if (i3 == 0) {
            return i2;
        }
        if (zArr[0]) {
            a(zArr);
            return i - 3;
        } else if (i3 > 1 && zArr[1] && bArr[i] == 1) {
            a(zArr);
            return i - 2;
        } else if (i3 <= 2 || !zArr[2] || bArr[i] != 0 || bArr[i + 1] != 1) {
            int i4 = i2 - 1;
            int i5 = i + 2;
            while (i5 < i4) {
                byte b2 = bArr[i5];
                if ((b2 & 254) == 0) {
                    int i6 = i5 - 2;
                    if (bArr[i6] == 0 && bArr[i5 - 1] == 0 && b2 == 1) {
                        a(zArr);
                        return i6;
                    }
                    i5 -= 2;
                }
                i5 += 3;
            }
            zArr[0] = i3 <= 2 ? !(i3 != 2 ? !zArr[1] || bArr[i4] != 1 : !(zArr[2] && bArr[i2 + -2] == 0 && bArr[i4] == 1)) : bArr[i2 + -3] == 0 && bArr[i2 + -2] == 0 && bArr[i4] == 1;
            zArr[1] = i3 <= 1 ? !(!zArr[2] || bArr[i4] != 0) : bArr[i2 + -2] == 0 && bArr[i4] == 0;
            if (bArr[i4] == 0) {
                z = true;
            }
            zArr[2] = z;
            return i2;
        } else {
            a(zArr);
            return i - 1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0159  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x016d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.exoplayer2.util.NalUnitUtil.SpsData a(byte[] r21, int r22, int r23) {
        /*
            com.google.android.exoplayer2.util.ParsableNalUnitBitArray r0 = new com.google.android.exoplayer2.util.ParsableNalUnitBitArray
            r1 = r21
            r2 = r22
            r3 = r23
            r0.<init>(r1, r2, r3)
            r1 = 8
            r0.a(r1)
            int r3 = r0.c(r1)
            int r4 = r0.c(r1)
            int r5 = r0.c(r1)
            int r6 = r0.f()
            r2 = 100
            r8 = 3
            r9 = 1
            if (r3 == r2) goto L_0x004e
            r2 = 110(0x6e, float:1.54E-43)
            if (r3 == r2) goto L_0x004e
            r2 = 122(0x7a, float:1.71E-43)
            if (r3 == r2) goto L_0x004e
            r2 = 244(0xf4, float:3.42E-43)
            if (r3 == r2) goto L_0x004e
            r2 = 44
            if (r3 == r2) goto L_0x004e
            r2 = 83
            if (r3 == r2) goto L_0x004e
            r2 = 86
            if (r3 == r2) goto L_0x004e
            r2 = 118(0x76, float:1.65E-43)
            if (r3 == r2) goto L_0x004e
            r2 = 128(0x80, float:1.794E-43)
            if (r3 == r2) goto L_0x004e
            r2 = 138(0x8a, float:1.93E-43)
            if (r3 != r2) goto L_0x004b
            goto L_0x004e
        L_0x004b:
            r2 = 1
            r10 = 0
            goto L_0x00a2
        L_0x004e:
            int r2 = r0.f()
            if (r2 != r8) goto L_0x0059
            boolean r11 = r0.b()
            goto L_0x005a
        L_0x0059:
            r11 = 0
        L_0x005a:
            r0.f()
            r0.f()
            r0.a()
            boolean r12 = r0.b()
            if (r12 == 0) goto L_0x00a1
            if (r2 == r8) goto L_0x006e
            r12 = 8
            goto L_0x0070
        L_0x006e:
            r12 = 12
        L_0x0070:
            r13 = 0
        L_0x0071:
            if (r13 >= r12) goto L_0x00a1
            boolean r14 = r0.b()
            if (r14 == 0) goto L_0x009e
            r14 = 6
            if (r13 >= r14) goto L_0x007f
            r14 = 16
            goto L_0x0081
        L_0x007f:
            r14 = 64
        L_0x0081:
            r15 = 0
            r16 = 8
            r17 = 8
        L_0x0086:
            if (r15 >= r14) goto L_0x009e
            if (r16 == 0) goto L_0x0096
            int r16 = r0.e()
            int r10 = r17 + r16
            int r10 = r10 + 256
            int r10 = r10 % 256
            r16 = r10
        L_0x0096:
            if (r16 != 0) goto L_0x0099
            goto L_0x009b
        L_0x0099:
            r17 = r16
        L_0x009b:
            int r15 = r15 + 1
            goto L_0x0086
        L_0x009e:
            int r13 = r13 + 1
            goto L_0x0071
        L_0x00a1:
            r10 = r11
        L_0x00a2:
            int r11 = r0.f()
            int r12 = r11 + 4
            int r13 = r0.f()
            if (r13 != 0) goto L_0x00b8
            int r11 = r0.f()
            int r11 = r11 + 4
            r16 = r2
            r14 = r11
            goto L_0x00dd
        L_0x00b8:
            if (r13 != r9) goto L_0x00da
            boolean r11 = r0.b()
            r0.e()
            r0.e()
            int r14 = r0.f()
            long r14 = (long) r14
            r16 = r2
            r7 = 0
        L_0x00cc:
            long r1 = (long) r7
            int r17 = (r1 > r14 ? 1 : (r1 == r14 ? 0 : -1))
            if (r17 >= 0) goto L_0x00d7
            r0.f()
            int r7 = r7 + 1
            goto L_0x00cc
        L_0x00d7:
            r15 = r11
            r14 = 0
            goto L_0x00de
        L_0x00da:
            r16 = r2
            r14 = 0
        L_0x00dd:
            r15 = 0
        L_0x00de:
            r0.f()
            r0.a()
            int r1 = r0.f()
            int r1 = r1 + r9
            int r2 = r0.f()
            int r2 = r2 + r9
            boolean r11 = r0.b()
            int r7 = 2 - r11
            int r7 = r7 * r2
            if (r11 != 0) goto L_0x00fb
            r0.a()
        L_0x00fb:
            r0.a()
            int r1 = r1 << 4
            int r2 = r7 << 4
            boolean r7 = r0.b()
            if (r7 == 0) goto L_0x013f
            int r7 = r0.f()
            int r17 = r0.f()
            int r18 = r0.f()
            int r19 = r0.f()
            if (r16 != 0) goto L_0x011d
            int r8 = 2 - r11
            goto L_0x0134
        L_0x011d:
            r20 = 2
            r9 = r16
            if (r9 != r8) goto L_0x0126
            r21 = 1
            goto L_0x0128
        L_0x0126:
            r21 = 2
        L_0x0128:
            r8 = 1
            if (r9 != r8) goto L_0x012d
            r9 = 2
            goto L_0x012e
        L_0x012d:
            r9 = 1
        L_0x012e:
            int r8 = 2 - r11
            int r8 = r8 * r9
            r9 = r21
        L_0x0134:
            int r7 = r7 + r17
            int r7 = r7 * r9
            int r1 = r1 - r7
            int r18 = r18 + r19
            int r18 = r18 * r8
            int r2 = r2 - r18
        L_0x013f:
            r7 = r1
            r8 = r2
            boolean r1 = r0.b()
            r2 = 1065353216(0x3f800000, float:1.0)
            if (r1 == 0) goto L_0x0191
            boolean r1 = r0.b()
            if (r1 == 0) goto L_0x0191
            r1 = 8
            int r1 = r0.c(r1)
            r9 = 255(0xff, float:3.57E-43)
            if (r1 != r9) goto L_0x016d
            r9 = 16
            int r1 = r0.c(r9)
            int r0 = r0.c(r9)
            if (r1 == 0) goto L_0x016b
            if (r0 == 0) goto L_0x016b
            float r1 = (float) r1
            float r0 = (float) r0
            float r2 = r1 / r0
        L_0x016b:
            r9 = r2
            goto L_0x0193
        L_0x016d:
            r0 = 17
            if (r1 >= r0) goto L_0x0177
            float[] r0 = b
            r0 = r0[r1]
            r9 = r0
            goto L_0x0193
        L_0x0177:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r9 = 46
            r0.<init>(r9)
            java.lang.String r9 = "Unexpected aspect_ratio_idc value: "
            java.lang.StringBuilder r0 = r0.append(r9)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "NalUnitUtil"
            com.google.android.exoplayer2.util.Log.c(r1, r0)
        L_0x0191:
            r9 = 1065353216(0x3f800000, float:1.0)
        L_0x0193:
            com.google.android.exoplayer2.util.NalUnitUtil$SpsData r0 = new com.google.android.exoplayer2.util.NalUnitUtil$SpsData
            r2 = r0
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.NalUnitUtil.a(byte[], int, int):com.google.android.exoplayer2.util.NalUnitUtil$SpsData");
    }

    public static void a(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i + 1;
            if (i3 < position) {
                byte b2 = byteBuffer.get(i) & Ev3Constants.Opcode.TST;
                if (i2 == 3) {
                    if (b2 == 1 && (byteBuffer.get(i3) & 31) == 7) {
                        ByteBuffer duplicate = byteBuffer.duplicate();
                        duplicate.position(i - 3);
                        duplicate.limit(position);
                        byteBuffer.position(0);
                        byteBuffer.put(duplicate);
                        return;
                    }
                } else if (b2 == 0) {
                    i2++;
                }
                if (b2 != 0) {
                    i2 = 0;
                }
                i = i3;
            } else {
                byteBuffer.clear();
                return;
            }
        }
    }

    public static void a(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    public static boolean a(String str, byte b2) {
        return ("video/avc".equals(str) && (b2 & 31) == 6) || ("video/hevc".equals(str) && ((b2 & Ev3Constants.Opcode.MEMORY_WRITE) >> 1) == 39);
    }

    public static int b(byte[] bArr, int i) {
        return bArr[i + 3] & 31;
    }

    public static int c(byte[] bArr, int i) {
        return (bArr[i + 3] & Ev3Constants.Opcode.MEMORY_WRITE) >> 1;
    }

    public static PpsData d(byte[] bArr, int i) {
        ParsableNalUnitBitArray parsableNalUnitBitArray = new ParsableNalUnitBitArray(bArr, 3, i);
        parsableNalUnitBitArray.a(8);
        int f = parsableNalUnitBitArray.f();
        int f2 = parsableNalUnitBitArray.f();
        parsableNalUnitBitArray.a();
        return new PpsData(f, f2, parsableNalUnitBitArray.b());
    }
}

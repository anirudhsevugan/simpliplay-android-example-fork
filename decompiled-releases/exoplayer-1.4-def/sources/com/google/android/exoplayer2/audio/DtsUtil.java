package com.google.android.exoplayer2.audio;

import androidx.core.view.MotionEventCompat;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import java.util.Arrays;

public final class DtsUtil {
    private static final int[] a = {1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
    private static final int[] b = {-1, 8000, 16000, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, 48000, -1, -1};
    private static final int[] c = {64, 112, 128, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE, 224, 256, 384, 448, 512, 640, 768, 896, 1024, 1152, 1280, 1536, 1920, 2048, 2304, 2560, 2688, 2816, 2823, 2944, 3072, 3840, 4096, 6144, 7680};

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0031, code lost:
        r2 = r2.get(r0) & com.google.appinventor.components.runtime.util.Ev3Constants.Opcode.MOVEF_8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0044, code lost:
        r2 = r2.get(r0) & 252;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0051, code lost:
        return (((r2 >> 2) | r1) + 1) << 5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(java.nio.ByteBuffer r2) {
        /*
            int r0 = r2.position()
            byte r1 = r2.get(r0)
            switch(r1) {
                case -2: goto L_0x0038;
                case -1: goto L_0x0025;
                case 31: goto L_0x0018;
                default: goto L_0x000b;
            }
        L_0x000b:
            int r1 = r0 + 4
            byte r1 = r2.get(r1)
            r1 = r1 & 1
            int r1 = r1 << 6
            int r0 = r0 + 5
            goto L_0x0044
        L_0x0018:
            int r1 = r0 + 5
            byte r1 = r2.get(r1)
            r1 = r1 & 7
            int r1 = r1 << 4
            int r0 = r0 + 6
            goto L_0x0031
        L_0x0025:
            int r1 = r0 + 4
            byte r1 = r2.get(r1)
            r1 = r1 & 7
            int r1 = r1 << 4
            int r0 = r0 + 7
        L_0x0031:
            byte r2 = r2.get(r0)
            r2 = r2 & 60
            goto L_0x004a
        L_0x0038:
            int r1 = r0 + 5
            byte r1 = r2.get(r1)
            r1 = r1 & 1
            int r1 = r1 << 6
            int r0 = r0 + 4
        L_0x0044:
            byte r2 = r2.get(r0)
            r2 = r2 & 252(0xfc, float:3.53E-43)
        L_0x004a:
            int r2 = r2 >> 2
            r2 = r2 | r1
            int r2 = r2 + 1
            int r2 = r2 << 5
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.DtsUtil.a(java.nio.ByteBuffer):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001f, code lost:
        r5 = r5 & com.google.appinventor.components.runtime.util.Ev3Constants.Opcode.MOVEF_8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0029, code lost:
        r5 = r5 & 252;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0031, code lost:
        return (((r5 >> 2) | r0) + 1) << 5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(byte[] r5) {
        /*
            r0 = 0
            byte r0 = r5[r0]
            r1 = 7
            r2 = 6
            r3 = 5
            r4 = 4
            switch(r0) {
                case -2: goto L_0x0022;
                case -1: goto L_0x0019;
                case 31: goto L_0x0012;
                default: goto L_0x000a;
            }
        L_0x000a:
            byte r0 = r5[r4]
            r0 = r0 & 1
            int r0 = r0 << r2
            byte r5 = r5[r3]
            goto L_0x0029
        L_0x0012:
            byte r0 = r5[r3]
            r0 = r0 & r1
            int r0 = r0 << r4
            byte r5 = r5[r2]
            goto L_0x001f
        L_0x0019:
            byte r0 = r5[r4]
            r0 = r0 & r1
            int r0 = r0 << r4
            byte r5 = r5[r1]
        L_0x001f:
            r5 = r5 & 60
            goto L_0x002b
        L_0x0022:
            byte r0 = r5[r3]
            r0 = r0 & 1
            int r0 = r0 << r2
            byte r5 = r5[r4]
        L_0x0029:
            r5 = r5 & 252(0xfc, float:3.53E-43)
        L_0x002b:
            int r5 = r5 >> 2
            r5 = r5 | r0
            int r5 = r5 + 1
            int r5 = r5 << r3
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.DtsUtil.a(byte[]):int");
    }

    public static Format a(byte[] bArr, String str, String str2) {
        ParsableBitArray parsableBitArray;
        byte[] bArr2 = bArr;
        int i = -1;
        if (bArr2[0] == Byte.MAX_VALUE) {
            parsableBitArray = new ParsableBitArray(bArr2);
        } else {
            byte[] copyOf = Arrays.copyOf(bArr2, bArr2.length);
            byte b2 = copyOf[0];
            if (b2 == -2 || b2 == -1) {
                for (int i2 = 0; i2 < copyOf.length - 1; i2 += 2) {
                    byte b3 = copyOf[i2];
                    int i3 = i2 + 1;
                    copyOf[i2] = copyOf[i3];
                    copyOf[i3] = b3;
                }
            }
            parsableBitArray = new ParsableBitArray(copyOf);
            if (copyOf[0] == 31) {
                ParsableBitArray parsableBitArray2 = new ParsableBitArray(copyOf);
                while (parsableBitArray2.a() >= 16) {
                    parsableBitArray2.b(2);
                    int c2 = parsableBitArray2.c(14) & 16383;
                    int min = Math.min(8 - parsableBitArray.c, 14);
                    int i4 = (8 - parsableBitArray.c) - min;
                    parsableBitArray.a[parsableBitArray.b] = (byte) (parsableBitArray.a[parsableBitArray.b] & ((MotionEventCompat.ACTION_POINTER_INDEX_MASK >> parsableBitArray.c) | ((1 << i4) - 1)));
                    int i5 = 14 - min;
                    parsableBitArray.a[parsableBitArray.b] = (byte) (((c2 >>> i5) << i4) | parsableBitArray.a[parsableBitArray.b]);
                    int i6 = parsableBitArray.b + 1;
                    while (i5 > 8) {
                        parsableBitArray.a[i6] = (byte) (c2 >>> (i5 - 8));
                        i5 -= 8;
                        i6++;
                    }
                    int i7 = 8 - i5;
                    byte[] bArr3 = parsableBitArray.a;
                    bArr3[i6] = (byte) (bArr3[i6] & ((1 << i7) - 1));
                    int i8 = ((1 << i5) - 1) & c2;
                    byte[] bArr4 = parsableBitArray.a;
                    bArr4[i6] = (byte) ((i8 << i7) | bArr4[i6]);
                    parsableBitArray.b(14);
                    parsableBitArray.g();
                }
            }
            parsableBitArray.a(copyOf, copyOf.length);
        }
        parsableBitArray.b(60);
        int i9 = a[parsableBitArray.c(6)];
        int i10 = b[parsableBitArray.c(4)];
        int c3 = parsableBitArray.c(5);
        if (c3 < 29) {
            i = (c[c3] * 1000) / 2;
        }
        parsableBitArray.b(10);
        int i11 = parsableBitArray.c(2) > 0 ? 1 : 0;
        Format.Builder builder = new Format.Builder();
        builder.a = str;
        builder.k = "audio/vnd.dts";
        builder.f = i;
        builder.x = i9 + i11;
        builder.y = i10;
        builder.n = null;
        builder.c = str2;
        return builder.a();
    }

    public static boolean a(int i) {
        return i == 2147385345 || i == -25230976 || i == 536864768 || i == -14745368;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        return (r6 << 4) / 14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x003b, code lost:
        r6 = (((r6 & com.google.appinventor.components.runtime.util.Ev3Constants.Opcode.MOVEF_8) >> 2) | r0) + 1;
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0051, code lost:
        r6 = (((r6 & 240) >> 4) | r1) + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0056, code lost:
        if (r0 == false) goto L_?;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int b(byte[] r6) {
        /*
            r0 = 0
            byte r1 = r6[r0]
            r2 = 6
            r3 = 7
            r4 = 1
            r5 = 4
            switch(r1) {
                case -2: goto L_0x0043;
                case -1: goto L_0x002b;
                case 31: goto L_0x001a;
                default: goto L_0x000a;
            }
        L_0x000a:
            r1 = 5
            byte r1 = r6[r1]
            r1 = r1 & 3
            int r1 = r1 << 12
            byte r2 = r6[r2]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << r5
            r1 = r1 | r2
            byte r6 = r6[r3]
            goto L_0x0051
        L_0x001a:
            byte r0 = r6[r2]
            r0 = r0 & 3
            int r0 = r0 << 12
            byte r1 = r6[r3]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << r5
            r0 = r0 | r1
            r1 = 8
            byte r6 = r6[r1]
            goto L_0x003b
        L_0x002b:
            byte r0 = r6[r3]
            r0 = r0 & 3
            int r0 = r0 << 12
            byte r1 = r6[r2]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << r5
            r0 = r0 | r1
            r1 = 9
            byte r6 = r6[r1]
        L_0x003b:
            r6 = r6 & 60
            int r6 = r6 >> 2
            r6 = r6 | r0
            int r6 = r6 + r4
            r0 = 1
            goto L_0x0056
        L_0x0043:
            byte r1 = r6[r5]
            r1 = r1 & 3
            int r1 = r1 << 12
            byte r3 = r6[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << r5
            r1 = r1 | r3
            byte r6 = r6[r2]
        L_0x0051:
            r6 = r6 & 240(0xf0, float:3.36E-43)
            int r6 = r6 >> r5
            r6 = r6 | r1
            int r6 = r6 + r4
        L_0x0056:
            if (r0 == 0) goto L_0x005b
            int r6 = r6 << r5
            int r6 = r6 / 14
        L_0x005b:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.DtsUtil.b(byte[]):int");
    }
}

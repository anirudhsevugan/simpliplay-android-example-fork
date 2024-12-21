package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.nio.ByteBuffer;

public final class Ac4Util {
    private static final int[] a = {2002, 2000, 1920, ErrorMessages.ERROR_CANNOT_SAVE_IMAGE, 1600, 1001, 1000, 960, 800, 800, 480, 400, 400, 2048};

    public final class SyncFrameInfo {
        public final int a;
        public final int b;
        public final int c;

        private SyncFrameInfo(int i, int i2, int i3) {
            this.a = i;
            this.b = i2;
            this.c = i3;
        }

        /* synthetic */ SyncFrameInfo(int i, int i2, int i3, byte b2) {
            this(i, i2, i3);
        }
    }

    public static int a(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[16];
        int position = byteBuffer.position();
        byteBuffer.get(bArr);
        byteBuffer.position(position);
        return a(new ParsableBitArray(bArr)).c;
    }

    public static int a(byte[] bArr, int i) {
        int i2 = 7;
        if (bArr.length < 7) {
            return -1;
        }
        byte b = ((bArr[2] & Ev3Constants.Opcode.TST) << 8) | (bArr[3] & Ev3Constants.Opcode.TST);
        if (b == 65535) {
            b = ((bArr[4] & Ev3Constants.Opcode.TST) << 16) | ((bArr[5] & Ev3Constants.Opcode.TST) << 8) | (bArr[6] & Ev3Constants.Opcode.TST);
        } else {
            i2 = 4;
        }
        if (i == 44097) {
            i2 += 2;
        }
        return b + i2;
    }

    public static Format a(ParsableByteArray parsableByteArray, String str, String str2, DrmInitData drmInitData) {
        parsableByteArray.e(1);
        int i = ((parsableByteArray.c() & 32) >> 5) == 1 ? 48000 : 44100;
        Format.Builder builder = new Format.Builder();
        builder.a = str;
        builder.k = "audio/ac4";
        builder.x = 2;
        builder.y = i;
        builder.n = drmInitData;
        builder.c = str2;
        return builder.a();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0082, code lost:
        if (r8 != 11) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0087, code lost:
        if (r8 != 11) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008c, code lost:
        if (r8 != 8) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008e, code lost:
        r8 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0091, code lost:
        r8 = r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.exoplayer2.audio.Ac4Util.SyncFrameInfo a(com.google.android.exoplayer2.util.ParsableBitArray r8) {
        /*
            r0 = 16
            int r1 = r8.c(r0)
            int r0 = r8.c(r0)
            r2 = 65535(0xffff, float:9.1834E-41)
            r3 = 4
            if (r0 != r2) goto L_0x0018
            r0 = 24
            int r0 = r8.c(r0)
            r2 = 7
            goto L_0x0019
        L_0x0018:
            r2 = 4
        L_0x0019:
            int r0 = r0 + r2
            r2 = 44097(0xac41, float:6.1793E-41)
            if (r1 != r2) goto L_0x0021
            int r0 = r0 + 2
        L_0x0021:
            r1 = 2
            int r2 = r8.c(r1)
            r4 = 3
            if (r2 != r4) goto L_0x0032
        L_0x0029:
            r8.c(r1)
            boolean r2 = r8.e()
            if (r2 != 0) goto L_0x0029
        L_0x0032:
            r2 = 10
            int r2 = r8.c(r2)
            boolean r5 = r8.e()
            if (r5 == 0) goto L_0x0047
            int r5 = r8.c(r4)
            if (r5 <= 0) goto L_0x0047
            r8.b(r1)
        L_0x0047:
            boolean r1 = r8.e()
            r5 = 48000(0xbb80, float:6.7262E-41)
            r6 = 44100(0xac44, float:6.1797E-41)
            if (r1 == 0) goto L_0x0057
            r1 = 48000(0xbb80, float:6.7262E-41)
            goto L_0x005a
        L_0x0057:
            r1 = 44100(0xac44, float:6.1797E-41)
        L_0x005a:
            int r8 = r8.c(r3)
            r3 = 0
            if (r1 != r6) goto L_0x006a
            r6 = 13
            if (r8 != r6) goto L_0x006a
            int[] r2 = a
            r8 = r2[r8]
            goto L_0x0094
        L_0x006a:
            if (r1 != r5) goto L_0x0093
            r5 = 14
            if (r8 >= r5) goto L_0x0093
            int[] r5 = a
            r5 = r5[r8]
            int r2 = r2 % 5
            r6 = 11
            r7 = 8
            switch(r2) {
                case 1: goto L_0x008a;
                case 2: goto L_0x0085;
                case 3: goto L_0x008a;
                case 4: goto L_0x007e;
                default: goto L_0x007d;
            }
        L_0x007d:
            goto L_0x0091
        L_0x007e:
            if (r8 == r4) goto L_0x008e
            if (r8 == r7) goto L_0x008e
            if (r8 != r6) goto L_0x0091
            goto L_0x008e
        L_0x0085:
            if (r8 == r7) goto L_0x008e
            if (r8 != r6) goto L_0x0091
            goto L_0x008e
        L_0x008a:
            if (r8 == r4) goto L_0x008e
            if (r8 != r7) goto L_0x0091
        L_0x008e:
            int r8 = r5 + 1
            goto L_0x0094
        L_0x0091:
            r8 = r5
            goto L_0x0094
        L_0x0093:
            r8 = 0
        L_0x0094:
            com.google.android.exoplayer2.audio.Ac4Util$SyncFrameInfo r2 = new com.google.android.exoplayer2.audio.Ac4Util$SyncFrameInfo
            r2.<init>(r1, r0, r8, r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.Ac4Util.a(com.google.android.exoplayer2.util.ParsableBitArray):com.google.android.exoplayer2.audio.Ac4Util$SyncFrameInfo");
    }

    public static void a(int i, ParsableByteArray parsableByteArray) {
        parsableByteArray.a(7);
        byte[] bArr = parsableByteArray.a;
        bArr[0] = Ev3Constants.Opcode.OUTPUT_STEP_POWER;
        bArr[1] = Ev3Constants.Opcode.JR;
        bArr[2] = -1;
        bArr[3] = -1;
        bArr[4] = (byte) (i >> 16);
        bArr[5] = (byte) (i >> 8);
        bArr[6] = (byte) i;
    }
}

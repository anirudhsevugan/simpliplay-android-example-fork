package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.util.ParsableByteArray;

public final class TsUtil {
    public static int a(byte[] bArr, int i, int i2) {
        while (i < i2 && bArr[i] != 71) {
            i++;
        }
        return i;
    }

    public static long a(ParsableByteArray parsableByteArray, int i, int i2) {
        parsableByteArray.d(i);
        if (parsableByteArray.a() < 5) {
            return -9223372036854775807L;
        }
        int j = parsableByteArray.j();
        if ((8388608 & j) != 0 || ((2096896 & j) >> 8) != i2) {
            return -9223372036854775807L;
        }
        if (((j & 32) != 0) && parsableByteArray.c() >= 7 && parsableByteArray.a() >= 7) {
            if ((parsableByteArray.c() & 16) == 16) {
                byte[] bArr = new byte[6];
                parsableByteArray.a(bArr, 0, 6);
                return ((((long) bArr[0]) & 255) << 25) | ((((long) bArr[1]) & 255) << 17) | ((((long) bArr[2]) & 255) << 9) | ((((long) bArr[3]) & 255) << 1) | ((((long) bArr[4]) & 255) >> 7);
            }
        }
        return -9223372036854775807L;
    }
}

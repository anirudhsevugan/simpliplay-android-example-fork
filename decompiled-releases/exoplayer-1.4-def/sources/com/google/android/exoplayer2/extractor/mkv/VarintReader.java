package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.appinventor.components.runtime.util.Ev3Constants;

final class VarintReader {
    private static final long[] b = {128, 64, 32, 16, 8, 4, 2, 1};
    int a;
    private final byte[] c = new byte[8];
    private int d;

    public static int a(int i) {
        int i2 = 0;
        while (i2 < 8) {
            int i3 = ((b[i2] & ((long) i)) > 0 ? 1 : ((b[i2] & ((long) i)) == 0 ? 0 : -1));
            i2++;
            if (i3 != 0) {
                return i2;
            }
        }
        return -1;
    }

    public static long a(byte[] bArr, int i, boolean z) {
        long j = ((long) bArr[0]) & 255;
        if (z) {
            j &= b[i - 1] ^ -1;
        }
        for (int i2 = 1; i2 < i; i2++) {
            j = (j << 8) | (((long) bArr[i2]) & 255);
        }
        return j;
    }

    public final long a(ExtractorInput extractorInput, boolean z, boolean z2, int i) {
        if (this.d == 0) {
            if (!extractorInput.a(this.c, 0, 1, z)) {
                return -1;
            }
            int a2 = a(this.c[0] & Ev3Constants.Opcode.TST);
            this.a = a2;
            if (a2 != -1) {
                this.d = 1;
            } else {
                throw new IllegalStateException("No valid varint length mask found");
            }
        }
        int i2 = this.a;
        if (i2 > i) {
            this.d = 0;
            return -2;
        }
        if (i2 != 1) {
            extractorInput.b(this.c, 1, i2 - 1);
        }
        this.d = 0;
        return a(this.c, this.a, z2);
    }

    public final void a() {
        this.d = 0;
        this.a = 0;
    }
}

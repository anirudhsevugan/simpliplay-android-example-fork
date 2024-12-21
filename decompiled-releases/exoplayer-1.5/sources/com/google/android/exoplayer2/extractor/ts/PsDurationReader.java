package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.util.Ev3Constants;

final class PsDurationReader {
    final TimestampAdjuster a = new TimestampAdjuster(0);
    final ParsableByteArray b = new ParsableByteArray();
    boolean c;
    boolean d;
    boolean e;
    long f = -9223372036854775807L;
    long g = -9223372036854775807L;
    long h = -9223372036854775807L;

    PsDurationReader() {
    }

    static int a(byte[] bArr, int i) {
        return (bArr[i + 3] & Ev3Constants.Opcode.TST) | ((bArr[i] & Ev3Constants.Opcode.TST) << 24) | ((bArr[i + 1] & Ev3Constants.Opcode.TST) << 16) | ((bArr[i + 2] & Ev3Constants.Opcode.TST) << 8);
    }

    public static long a(ParsableByteArray parsableByteArray) {
        int i = parsableByteArray.b;
        if (parsableByteArray.a() < 9) {
            return -9223372036854775807L;
        }
        byte[] bArr = new byte[9];
        boolean z = false;
        parsableByteArray.a(bArr, 0, 9);
        parsableByteArray.d(i);
        byte b2 = bArr[0];
        if ((b2 & Ev3Constants.Opcode.ARRAY_APPEND) == 68 && (bArr[2] & 4) == 4 && (bArr[4] & 4) == 4 && (bArr[5] & 1) == 1 && (bArr[8] & 3) == 3) {
            z = true;
        }
        if (!z) {
            return -9223372036854775807L;
        }
        byte b3 = bArr[2];
        return ((((long) bArr[4]) & 248) >> 3) | (((((long) b2) & 56) >> 3) << 30) | ((((long) b2) & 3) << 28) | ((((long) bArr[1]) & 255) << 20) | (((((long) b3) & 248) >> 3) << 15) | ((((long) b3) & 3) << 13) | ((((long) bArr[3]) & 255) << 5);
    }

    /* access modifiers changed from: package-private */
    public final int a(ExtractorInput extractorInput) {
        this.b.a(Util.f, 0);
        this.c = true;
        extractorInput.a();
        return 0;
    }
}

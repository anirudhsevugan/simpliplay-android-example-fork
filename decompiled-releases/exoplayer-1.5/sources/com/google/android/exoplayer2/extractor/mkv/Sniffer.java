package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.appinventor.components.runtime.util.Ev3Constants;

final class Sniffer {
    final ParsableByteArray a = new ParsableByteArray(8);
    int b;

    /* access modifiers changed from: package-private */
    public final long a(ExtractorInput extractorInput) {
        int i = 0;
        extractorInput.d(this.a.a, 0, 1);
        byte b2 = this.a.a[0] & Ev3Constants.Opcode.TST;
        if (b2 == 0) {
            return Long.MIN_VALUE;
        }
        int i2 = 128;
        int i3 = 0;
        while ((b2 & i2) == 0) {
            i2 >>= 1;
            i3++;
        }
        int i4 = b2 & (i2 ^ -1);
        extractorInput.d(this.a.a, 1, i3);
        while (i < i3) {
            i++;
            i4 = (this.a.a[i] & Ev3Constants.Opcode.TST) + (i4 << 8);
        }
        this.b += i3 + 1;
        return (long) i4;
    }
}

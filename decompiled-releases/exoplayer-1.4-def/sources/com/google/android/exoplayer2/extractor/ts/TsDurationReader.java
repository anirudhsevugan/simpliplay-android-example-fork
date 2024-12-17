package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;

final class TsDurationReader {
    final int a;
    final TimestampAdjuster b = new TimestampAdjuster(0);
    final ParsableByteArray c = new ParsableByteArray();
    boolean d;
    boolean e;
    boolean f;
    long g = -9223372036854775807L;
    long h = -9223372036854775807L;
    long i = -9223372036854775807L;

    TsDurationReader(int i2) {
        this.a = i2;
    }

    /* access modifiers changed from: package-private */
    public final int a(ExtractorInput extractorInput) {
        this.c.a(Util.f, 0);
        this.d = true;
        extractorInput.a();
        return 0;
    }
}

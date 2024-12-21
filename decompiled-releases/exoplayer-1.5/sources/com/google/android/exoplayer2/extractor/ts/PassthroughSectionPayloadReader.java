package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;

public final class PassthroughSectionPayloadReader implements SectionPayloadReader {
    private Format a;
    private TimestampAdjuster b;
    private TrackOutput c;

    public PassthroughSectionPayloadReader(String str) {
        Format.Builder builder = new Format.Builder();
        builder.k = str;
        this.a = builder.a();
    }

    public final void a(ParsableByteArray parsableByteArray) {
        Assertions.a((Object) this.b);
        Util.a((Object) this.c);
        long c2 = this.b.c();
        if (c2 != -9223372036854775807L) {
            if (c2 != this.a.p) {
                Format.Builder a2 = this.a.a();
                a2.o = c2;
                Format a3 = a2.a();
                this.a = a3;
                this.c.a(a3);
            }
            int a4 = parsableByteArray.a();
            this.c.b(parsableByteArray, a4);
            this.c.a(this.b.b(), 1, a4, 0, (TrackOutput.CryptoData) null);
        }
    }

    public final void a(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        this.b = timestampAdjuster;
        trackIdGenerator.a();
        TrackOutput a2 = extractorOutput.a(trackIdGenerator.b(), 5);
        this.c = a2;
        a2.a(this.a);
    }
}

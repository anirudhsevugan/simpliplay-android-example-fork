package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.CeaUtil;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.List;

public final class SeiReader {
    private final List a;
    private final TrackOutput[] b;

    public SeiReader(List list) {
        this.a = list;
        this.b = new TrackOutput[list.size()];
    }

    public final void a(long j, ParsableByteArray parsableByteArray) {
        CeaUtil.a(j, parsableByteArray, this.b);
    }

    public final void a(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        for (int i = 0; i < this.b.length; i++) {
            trackIdGenerator.a();
            TrackOutput a2 = extractorOutput.a(trackIdGenerator.b(), 3);
            Format format = (Format) this.a.get(i);
            String str = format.l;
            boolean z = "application/cea-608".equals(str) || "application/cea-708".equals(str);
            String valueOf = String.valueOf(str);
            Assertions.a(z, (Object) valueOf.length() != 0 ? "Invalid closed caption mime type provided: ".concat(valueOf) : new String("Invalid closed caption mime type provided: "));
            String c = format.a != null ? format.a : trackIdGenerator.c();
            Format.Builder builder = new Format.Builder();
            builder.a = c;
            builder.k = str;
            builder.d = format.d;
            builder.c = format.c;
            builder.C = format.B;
            builder.m = format.n;
            a2.a(builder.a());
            this.b[i] = a2;
        }
    }
}

package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;

public final class Id3Reader implements ElementaryStreamReader {
    private final ParsableByteArray a = new ParsableByteArray(10);
    private TrackOutput b;
    private boolean c;
    private long d;
    private int e;
    private int f;

    public final void a() {
        this.c = false;
    }

    public final void a(long j, int i) {
        if ((i & 4) != 0) {
            this.c = true;
            this.d = j;
            this.e = 0;
            this.f = 0;
        }
    }

    public final void a(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.a();
        TrackOutput a2 = extractorOutput.a(trackIdGenerator.b(), 5);
        this.b = a2;
        Format.Builder builder = new Format.Builder();
        builder.a = trackIdGenerator.c();
        builder.k = "application/id3";
        a2.a(builder.a());
    }

    public final void a(ParsableByteArray parsableByteArray) {
        Assertions.a((Object) this.b);
        if (this.c) {
            int a2 = parsableByteArray.a();
            int i = this.f;
            if (i < 10) {
                int min = Math.min(a2, 10 - i);
                System.arraycopy(parsableByteArray.a, parsableByteArray.b, this.a.a, this.f, min);
                if (this.f + min == 10) {
                    this.a.d(0);
                    if (73 == this.a.c() && 68 == this.a.c() && 51 == this.a.c()) {
                        this.a.e(3);
                        this.e = this.a.n() + 10;
                    } else {
                        Log.c("Id3Reader", "Discarding invalid ID3 tag");
                        this.c = false;
                        return;
                    }
                }
            }
            int min2 = Math.min(a2, this.e - this.f);
            this.b.b(parsableByteArray, min2);
            this.f += min2;
        }
    }

    public final void b() {
        int i;
        Assertions.a((Object) this.b);
        if (this.c && (i = this.e) != 0 && this.f == i) {
            this.b.a(this.d, 1, i, 0, (TrackOutput.CryptoData) null);
            this.c = false;
        }
    }
}
